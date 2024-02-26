package WarHandling;

import java.io.*;

public class Commands {
    final String wZip = "wsl unzip ";
    final String wRM = "wsl rm -rf ";
    final String mnt = " /mnt/";
    final String d = " -d ";
    final String war = ".war";
    public void unpackWar(String input ) throws IOException {
        String cmd = prepare(input);
        String command = wZip + cmd + war + d  + cmd;
        Runtime.getRuntime().exec(command);
        deleteFile(input);
    }
    public void deleteFile(String input) throws IOException {
        String cmd = prepare(input);
        String command = wRM + cmd;
        Runtime.getRuntime().exec(command);
    }
    private String prepare(String input)
    {
        input = input.replace("\\","/").replace(":","").toLowerCase();
        return  mnt + input;
    }
    private static void deleteDirectory(File directory) throws IOException {
        if (directory.exists()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        deleteDirectory(file);
                    } else {
                        file.delete();
                    }
                }
            }
            directory.delete();
        }
    }
}
