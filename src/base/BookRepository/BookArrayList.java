package base.BookRepository;

import base.Book.Book;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class BookArrayList implements BookRepository{
    private ArrayList<Book> bookList = new ArrayList<>();
    @Override
    public void addBook(Book book) {bookList.add(book);}

    @Override
    public void removeBook(Book book) {
        try{bookList.remove(book);}
        catch (NullPointerException e){
            System.out.println("Id를 찾을 수 없습니다. 다시 입력해주십시오.");
        }
    }

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