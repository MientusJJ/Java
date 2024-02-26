package WarHandling;

import ServletContainer.HttpServer;
import jakarta.servlet.http.HttpServlet;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class WorkonPacked {
    private JarFile zFile;
    public WorkonPacked(String s) throws IOException {
        zFile = new JarFile(s);
    }
    public  Enumeration<JarEntry> getAll()
    {
//        Enumeration<? extends ZipEntry> entries = zFile.entries();
//        while (entries.hasMoreElements()) {
//            ZipEntry entry = entries.nextElement();
//            System.out.println("Plik wewnątrz WAR: " + entry.getName());
//        }
        return zFile.entries();
    }
    public void addSerwlets(HttpServer httpServer) throws MalformedURLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Enumeration<JarEntry> entries = getAll();
        File tempFile = new File(this.zFile.getName());
        URL[] urls = {tempFile.toURI().toURL()};
        ClassLoader classLoader = new URLClassLoader(urls);
        while(entries.hasMoreElements())
        {
            JarEntry jarEntry = entries.nextElement();
            if(!jarEntry.isDirectory() && jarEntry.getName().endsWith(".class"))
            {
                String className = jarEntry.getName().replace('/', '.').replace(".class", "");
                Class<?> clazz = classLoader.loadClass(className);
                HttpServlet httpServlet = (HttpServlet) clazz.newInstance();
                if(httpServlet != null)
                {
                    httpServer.addServlet(httpServlet);
                    System.out.println("Dodano klasę: " + clazz.getName());
                }

                System.out.println("Załadowano klasę: " + clazz.getName());
            }
        }
    }
}
