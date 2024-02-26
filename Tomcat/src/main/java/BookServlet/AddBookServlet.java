package BookServlet;


import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AddBookServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private BookManager bookManager;
    public BookManager getBookManager() {
        return bookManager;
    }

    public AddBookServlet(BookManager bookManager) {
        this.bookManager = bookManager;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        response.getWriter().println("Book adding...");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String genre = request.getParameter("genre");
        // Dodaj inne atrybuty książki według potrzeb

        if(title != null && author != null && genre != null) {
           Book newBook = new Book(title, author,genre); // Zakładając, że masz taki konstruktor w klasie Book
            bookManager.addBook(newBook);

            response.getWriter().println("Książka dodana pomyślnie!");
        } else {
            response.getWriter().println("Proszę podać wszystkie wymagane dane.");
        }
    }
}
