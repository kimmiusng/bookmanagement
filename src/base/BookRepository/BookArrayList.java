package base.BookRepository;

import base.Book;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class BookArrayList implements BookRepository{
    private ArrayList<Book> bookList = new ArrayList<>();
    @Override
    public void addBook(Book book) {bookList.add(book);}

    @Override
    public void removeBook(Book book) {bookList.remove(book);}

    @Override
    public Book getBook(Long id) {
        return bookList.stream().filter(book -> book.getId().equals(id)).toArray(Book[]::new)[0];
    }

    @Override
    public List<Book> getBooks() {return bookList;}

    @Override
    public List<Book> getBooks(Predicate<Book> predicate) {
        Book[] books = bookList.stream().filter(predicate).toArray(Book[]::new);
        return new ArrayList<>(List.of(books));
    }

    @Override
    public List<Book> getBooks(Comparator<Book> comparator) {
        List<Book> bookList1 = new ArrayList<>(bookList);
        bookList1.sort(comparator);
        return bookList1;
    }
}