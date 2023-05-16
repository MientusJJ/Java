package uj.wmii.pwj.gvt;

import javax.swing.*;
import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

public class Gvt {

    private final ExitHandler exitHandler;
    static final String myDir="C:\\Users\\janek\\IdeaProjects\\Java003\\04-gvt\\src\\uj\\wmii\\pwj";
    static final int toCutMyVersion=68;
    static final String versions="C:\\Users\\janek\\IdeaProjects\\Java003\\04-gvt\\src\\uj\\wmii\\pwj\\versions";
    static final String gvtFile="C:\\Users\\janek\\IdeaProjects\\Java003\\04-gvt\\src\\uj\\wmii\\pwj\\.gvt";
    private int currentVersion=-1;
    public Gvt(ExitHandler exitHandler) {
        this.exitHandler = exitHandler;
        currentVersion=getVersion();
    }

    public static void main(String... args) {
        Gvt gvt = new Gvt(new ExitHandler());
        gvt.mainInternal(args);
    }
    static boolean checkIfGVTexists()
    {
        File file=new File(gvtFile);
        return !file.exists();
    }
    void handleException(Exception e)
    {
        e.printStackTrace();
        exitHandler.exit(-3,"Underlying system problem. See ERR for details.");
    }
    static void handleGVT(ExitHandler e)
    {
        if(checkIfGVTexists())
        {
            e.exit(-2,"Current directory is not initialized. Please use \"init\" command to initialize.");
        }
        return;
    }
    static private int getVersion()
    {
        File file= new File(versions);
        int ret=-1;
        if(file.exists())
        {
            File[] dirList=file.listFiles();
            for(int i=0;i<dirList.length;i++)
            {
                String s = dirList[i].toString().substring(toCutMyVersion, dirList[0].toString().length() - 4);
                int indx=Integer.parseInt(s);
                if(indx>ret)
                {
                    ret=indx;
                }
            }
        }
        return ret;
    }

    void mainInternal(String... args)
    {
       String[] l_commands=args;
       if(l_commands.length==0)
       {
           exitHandler.exit(1,"Please specify command.");
       }
       else if(l_commands[0].equals("init"))
       {
               try
               {
                   if(checkIfGVTexists())
                   {
                       File file=new File(gvtFile);
                       file.createNewFile();
                   }
                   else
                   {
                       exitHandler.exit(10,"Current directory is already initialized.");
                   }

               }
               catch (Exception e)
               {
                   currentVersion=-1;
               }
               currentVersion=0;
               try
               {
                   File file=new File(versions);
                   file.mkdirs();
                   File version0=new File(versions+"\\0.txt");
                   version0.createNewFile();
                   FileWriter myWriter = new FileWriter(versions+"\\"+currentVersion+".txt");
                   myWriter.write(".gvt created");
                   myWriter.close();
               }
               catch (Exception e)
               {

               }
               exitHandler.exit(0,"Current directory initialized successfully.");
       }
       else if(l_commands[0].equals("add"))
       {
           handleGVT(exitHandler);
           if(l_commands.length<2 || l_commands[1].length()<5 || !l_commands[1].substring(l_commands[1].length()-4,l_commands[1].length()).equals(".txt"))
           {
               exitHandler.exit(20,"Please specify file to add.");
           }
           File file=new File(l_commands[1]);
           if(!file.exists())
           {
               exitHandler.exit(21,"File not found. File: "+ l_commands[1]);
           }
           File fileExists=new File(myDir+"\\"+l_commands[1]);
           if(fileExists.exists())
           {
               exitHandler.exit(22,"File already added. File: "+ l_commands[1]);
           }
           try
           {
               fileExists.createNewFile();
               FileChannel sourceChannel = new FileInputStream(l_commands[1]).getChannel();
               FileChannel destChannel = new FileOutputStream(fileExists).getChannel();
               destChannel.transferFrom(sourceChannel,0,sourceChannel.size());
               currentVersion++;
               File newVersion= new File(versions+"\\"+currentVersion+".txt");
               newVersion.createNewFile();
               FileWriter myWriter = new FileWriter(versions+"\\"+currentVersion+".txt");
               myWriter.write("Added file: "+ l_commands[1]+"\n");

               if(l_commands.length>3)
               {
                   if(l_commands[2].equals("-m"))
                   {
                       myWriter.write(l_commands[3]);
                   }
               }
               myWriter.close();
               exitHandler.exit(0,"File added successfully. File: "+ l_commands[1]);
           }
           catch (Exception e)
           {
               System.err.println("File cannot be added. See ERR for details. File: "+l_commands[1]);
               e.printStackTrace();
               exitHandler.exit(22,"File cannot be added. See ERR for details. File: "+l_commands[1]);
           }
       }
       else if(l_commands[0].equals("detach"))
       {
           handleGVT(exitHandler);
           if(l_commands.length<2 || l_commands[1].length()<5 || !l_commands[1].substring(l_commands[1].length()-4,l_commands[1].length()).equals(".txt"))
           {
               exitHandler.exit(30,"Please specify file to detach.");
           }
           File fileinGVT=new File(myDir+"\\"+l_commands[1]);
           if(!fileinGVT.exists())
           {
               exitHandler.exit(0,"File is not added to gvt. File: " + l_commands[1]);
           }
           try
           {
               currentVersion++;
               File newVersion= new File(versions+"\\"+currentVersion+".txt");
               newVersion.createNewFile();
               FileWriter myWriter = new FileWriter(versions+"\\"+currentVersion+".txt");
               myWriter.write("Detached file: "+ l_commands[1]+"\n");

               if(l_commands.length>3)
               {
                   if(l_commands[2].equals("-m"))
                   {
                       myWriter.write(l_commands[3]);
                   }
               }
               myWriter.close();
               exitHandler.exit(0,"File detached successfully. File: " + l_commands[1]);
           }
           catch(Exception e)
           {
               e.printStackTrace();
               exitHandler.exit(31,"File cannot be detached, see ERR for details. File: "+l_commands[1]);
           }
       }
       else if(l_commands[0].equals("commit"))
       {
           handleGVT(exitHandler);
           if(l_commands.length<2 || l_commands[1].length()<5 || !l_commands[1].substring(l_commands[1].length()-4,l_commands[1].length()).equals(".txt"))
           {
               exitHandler.exit(50,"Please specify file to commit.");
           }
           File file= new File(l_commands[1]);
           if(!file.exists())
           {
               exitHandler.exit(51,"File not found. File: " + l_commands[1]);
           }
           File fileinGVT=new File(myDir+"\\"+l_commands[1]);
           if(!fileinGVT.exists())
           {
               exitHandler.exit(0,"File is not added to gvt. File: " + l_commands[1]);
           }
           try
           {
               currentVersion++;
               File newVersion= new File(versions+"\\"+currentVersion+".txt");
               newVersion.createNewFile();
               FileWriter myWriter = new FileWriter(versions+"\\"+currentVersion+".txt");
               myWriter.write("Commited file: "+ l_commands[1]+"\n");

               if(l_commands.length>3)
               {
                   if(l_commands[2].equals("-m"))
                   {
                       myWriter.write(l_commands[3]);
                   }
               }
               myWriter.close();
               exitHandler.exit(0,"File committed successfully. File: " + l_commands[1]);
           }
           catch(Exception e)
           {
               e.printStackTrace();
               exitHandler.exit(52,"File cannot be committed, see ERR for details. File: " + l_commands[1]);
           }
       }
       else if(l_commands[0].equals("checkout"))
       {
           handleGVT(exitHandler);
           int indx=-1;
           if(l_commands.length<2 ||l_commands.length>3 )
           {
               exitHandler.exit(40,"Invalid version number");
           }
           try
           {
                indx=Integer.parseInt(l_commands[1]);
                if(indx<0 || indx > currentVersion)
                {
                    exitHandler.exit(40,"Invalid version number: "+indx );
                }
           }
           catch(Exception e)
           {
               exitHandler.exit(40,"Invalid version number: "+l_commands[1] );
           }
           File file= new File(versions);
           int ret=-1;
           try {
               if (file.exists()) {
                   File[] dirList = file.listFiles();
                   for (int i=0;i< dirList.length;i++)
                   {
                       String s = dirList[i].toString().substring(toCutMyVersion, dirList[0].toString().length() - 4);
                       int num=Integer.parseInt(s);
                       if(num>indx)
                       {
                           dirList[i].delete();
                       }
                   }
               }
               exitHandler.exit(0,"Version "+indx+" checked out successfully.");
           }
           catch(Exception e)
           {
               this.handleException(e);
           }
       }
       else if(l_commands[0].equals("history"))
       {
           handleGVT(exitHandler);
           int indx=currentVersion;
           if(l_commands.length == 3 && l_commands[1].equals("-last"))
           {
               try
               {
                   indx=Integer.parseInt(l_commands[2]);
               }
               catch(Exception e)
               {
                   indx=0;
               }
           }
           File file= new File(versions);
           if(file.exists())
           {
               File[] dirList=file.listFiles();
               for(int i=0;i<dirList.length;i++)
               {
                   String s = dirList[i].toString().substring(toCutMyVersion, dirList[0].toString().length() - 4);
                   int num=Integer.parseInt(s);
                   if(currentVersion-num<indx)
                   {
                       File toRead= new File(versions+"\\"+num+".txt");
                       try
                       {
                           Scanner reader=new Scanner(toRead);
                           if(reader.hasNextLine())
                           {
                               System.out.println("Version: "+num);
                               System.out.println(reader.nextLine());
                           }
                           reader.close();
                       } catch (FileNotFoundException e) {
                          handleException(e);
                       }
                   }
               }
           }
       }
       else if(l_commands[0].equals("version"))
       {
           handleGVT(exitHandler);
           int indx=currentVersion;
           if(l_commands.length>1)
           {
               try
               {
                    indx=Integer.parseInt(l_commands[1]);
               }
               catch(Exception e)
               {
                   exitHandler.exit(60,"Invalid version number: "+ l_commands[1]);
               }
           }
           try {
               File file = new File(versions + "\\" + indx + ".txt");
               Scanner reader = new Scanner(file);
               System.out.println("Version: "+indx);
               while(reader.hasNextLine())
               {
                   System.out.println(reader.nextLine());
               }
               reader.close();
           }
           catch (FileNotFoundException e)
           {
                this.handleException(e);
           }
       }
       else
       {
           exitHandler.exit(1,"Unknown command " + l_commands[0]);
       }
    }

}
