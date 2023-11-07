package base.BookRepository;

import base.Book.Book;

import java.io.Serializable;
import java.util.*;
import java.util.function.Predicate;

public class BookHashMap implements BookRepository, Serializable{
    private HashMap<Long, Book> bookList = new HashMap<>();

    @Override
    public void addBook(Book book){
        bookList.put(book.getId(),book);
    }

    @Override
    public void removeBook(Book book){
        try{bookList.remove(book.getId());}
        catch (NullPointerException e){
            System.out.println("Id를 찾을 수 없습니다. 다시 입력해주십시오.");
        }
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