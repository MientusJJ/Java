package com.example.zj;

import BookServlet.AddBookServlet;
import BookServlet.BookManager;
import BookServlet.BooksServlet;
import ServletAnnotation.SecondServlet;
import ServletAnnotation.commonServlet;
import ServletContainer.HttpServer;
import WarHandling.UnpackWar;
import WarHandling.WorkonPacked;
import jakarta.servlet.http.HttpServlet;

import java.io.IOException;
import java.util.zip.ZipEntry;

public class Main extends HttpServlet {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UnpackWar cmJar = new UnpackWar("C:\\test\\mywar.war");
        cmJar.extract();
        HttpServer server = new HttpServer();
        cmJar.addClasses(server);
        cmJar.deleteDirectory("C:\\test\\mysecondwar");
        WorkonPacked workonPacked = new WorkonPacked("C:\\test\\myjar.jar");
        workonPacked.addSerwlets(server);
        BookManager bm = new BookManager();
        BooksServlet booksServlet = new BooksServlet(bm);
        AddBookServlet addBookServlet = new AddBookServlet(bm);
        booksServlet.getBookManager().addBook("Hobbit", "J.R.R. Tolkien", "Fantasy");
        booksServlet.getBookManager().addBook("Dune", "Frank Herbert", "Science Fiction");
        booksServlet.getBookManager().addBook("1984", "George Orwell", "Dystopian");
        server.addServlet("/books", booksServlet);
        server.addServlet("/addbook",addBookServlet);
        server.addServlet(new commonServlet());
        server.addServlet(new SecondServlet());
        server.listen(8080);
    }
}
