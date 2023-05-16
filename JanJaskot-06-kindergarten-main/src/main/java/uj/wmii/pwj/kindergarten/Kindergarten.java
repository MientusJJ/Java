package uj.wmii.pwj.kindergarten;

import javax.swing.plaf.basic.BasicTreeUI;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Kindergarten {

    static private int g_numOfChilds=0;
    static Fork[] tab_Fork;
    static Feed[] tab_feed;
    private static class OneChild
    {
        int m_time;
        String m_name;
        OneChild(int p_time,String p_name)
        {
            this.m_name=p_name;
            this.m_time=p_time;
        }

    }
    static int left(int i)
    {
        return ((i-1+g_numOfChilds)%g_numOfChilds);
    }
    static int right(int i)
    {
        return ((i+1)%g_numOfChilds);
    }
    static class Fork
    {
        public Semaphore mutex=new Semaphore(1);
        void takeFork()
        {
            mutex.acquireUninterruptibly();
        }
        void releaseFork()
        {
            mutex.release();
        }
        boolean CanTake()
        {
            return mutex.availablePermits()>0;
        }
    }
    public static class Feed extends Thread
    {
        private Child m_child;
        public int m_indx;
        public Fork m_leftFork;
        public Fork m_rightFork;
        public Feed(Child p_child,int p_indx,Fork p_leftFork,Fork p_rightFork)
        {
            this.m_child=p_child;
            this.m_indx=p_indx;
            this.m_leftFork=p_leftFork;
            this.m_rightFork=p_rightFork;
        }
        public void run()
        {
            long start = System.currentTimeMillis();
            long end=start;
            while(end-start<10100)
            {
                m_leftFork.takeFork();
                m_rightFork.takeFork();
                m_child.eat();
                m_leftFork.releaseFork();
                m_rightFork.releaseFork();
                try {
                    Thread.sleep(m_child.hungerSpeed());
                }
                catch (Exception e)
                {
                }
                end=System.currentTimeMillis();
            }

        }
    }

    public static void main(String[] args) throws IOException {
        init();
        final var fileName = args[0];
        System.out.println("File name: " + fileName);
        List<OneChild> in=new ArrayList<OneChild>();
        File input= new File(fileName);
        Scanner myScanner= new Scanner(input);
        g_numOfChilds=Integer.parseInt(myScanner.nextLine());
        while(myScanner.hasNextLine())
        {
            String l_str=myScanner.nextLine();
            String[] l_tab=l_str.split(" ");
            int l_time=Integer.parseInt((l_tab[1]));
            OneChild l_oneChild= new OneChild(l_time,l_tab[0]);
            in.add(l_oneChild);
        }
        tab_Fork= new Fork[g_numOfChilds];
        for(int i=0;i<g_numOfChilds;i++)
        {
            tab_Fork[i]=new Fork();
        }
        tab_feed=new Feed[g_numOfChilds];
        for(int i=0;i<g_numOfChilds;i++)
        {
            tab_feed[i]=new Feed(new ChildImpl(in.get(i).m_name,in.get(i).m_time),i,tab_Fork[left(i)],tab_Fork[right(i)]);
            tab_feed[i].start();
        }


    }


    private static void init() throws IOException {
        Files.deleteIfExists(Path.of("out.txt"));
        System.setErr(new PrintStream(new FileOutputStream("out.txt")));
        new Thread(Kindergarten::runKindergarden).start();
    }

    private static void runKindergarden() {
        try {
            Thread.sleep(10100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            List<String> errLines = Files.readAllLines(Path.of("out.txt"));
            System.out.println("Children cries count: " + errLines.size());
            errLines.forEach(System.out::println);
            System.exit(errLines.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
