package WarHandling;

import ServletContainer.HttpServer;
import jakarta.servlet.http.HttpServlet;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class UnpackWar extends ZipFile {
    private String dictionary;
    public UnpackWar(String name) throws IOException {
        super(name);
    }
    public void extract() throws IOException {
        byte[] buffer = new byte[1024];
        dictionary = prepareName(this.getName());
        File destDir = new File(dictionary);
        if (!destDir.exists()) {
            destDir.mkdir();
        }
        Enumeration<? extends ZipEntry> entries = this.entries();
        while (entries.hasMoreElements()) {
            ZipEntry zipEntry = entries.nextElement();
            String entryName = zipEntry.getName();
            String entryPath = dictionary + File.separator + entryName;
            if (zipEntry.isDirectory()) {
                File dir = new File(entryPath);
                dir.mkdirs();
            } else {
                BufferedInputStream bis = new BufferedInputStream(this.getInputStream(zipEntry));
                FileOutputStream fos = new FileOutputStream(entryPath);
                BufferedOutputStream bos = new BufferedOutputStream(fos);
                int len;
                while ((len = bis.read(buffer)) > 0) {
                    bos.write(buffer, 0, len);
                }
                bos.close();
                bis.close();
            }
        }
    }
    public String prepareName(String input)
    {
        if(input.endsWith(".war") || input.endsWith(".zip"))
        {
            return input.substring(0,input.length()-4);
        }
        else return input;
    }
    public void addClasses(HttpServer httpServer) throws MalformedURLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        File folder = new File(dictionary);
        File[] files = folder.listFiles();
        for (File file : files) {
            // Sprawdź, czy to plik .class
            if (file.isFile() && file.getName().endsWith(".class")) {
                // Utwórz URL dla pliku .class
                URL url = file.toURI().toURL();
                URLClassLoader classLoader = new URLClassLoader(new URL[]{url});
                Class<?> loadedClass = classLoader.loadClass(file.getName().replace(".class", ""));
                HttpServlet httpServlet = (HttpServlet) loadedClass.newInstance();
                if(httpServlet != null)
                {
                    httpServer.addServlet(httpServlet);
                }
                // Wypisz załadowany plik
                System.out.println("Dodano plik: " + file.getName());
            }
        }
    }
    public void deleteDirectory(String directory) throws IOException {
        File file = new File(directory);
        deleteDirectory(file);
    }
    public  void deleteDirectory(File directory) throws IOException {
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
