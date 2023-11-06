package base.BookRepository;

import base.Book.Book;

import java.util.*;
import java.util.function.Predicate;

public class BookHashMap implements BookRepository {
    private HashMap<Long, Book> bookList = new HashMap<>();

    @Override
    public void addBook(Book book){
        bookList.put(book.getId(),book);
    }

    @Override
    public void removeBook(Book book){
        bookList.remove(book.getId());
    }

    @Override
    public Book getBook(Long id) {
        return bookList.get(id);
    }

    @Override
    public List<Book> getBooks() {
        return new ArrayList<>(bookList.values());
    }

    @Override
    public List<Book> getBooks(Predicate<Book> predicate) {
        Book[] books = bookList.values().stream().filter(predicate).toArray(Book[]::new);
        return new ArrayList<>(List.of(books));
    }

    @Override
    public List<Book> getBooks(Comparator<Book> comparator) {
        List<Book> bookList1 = new ArrayList<>(bookList.values());
        bookList1.sort(comparator);
        return bookList1;
    }
}