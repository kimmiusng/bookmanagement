package base.BookRepository;
import base.Book;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
public interface BookRepository {
    void addBook(Book book);
    void removeBook(Book book);
    Book getBook(Long id);
    List<Book> getBooks();
    List<Book> getBooks(Predicate<Book> predicate);
    List<Book> getBooks(Comparator<Book> comparator);
}
