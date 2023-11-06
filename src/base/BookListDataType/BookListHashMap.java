package base.BookListDataType;

import base.Book.Book;

import java.util.HashMap;
import java.util.Set;

public class BookListHashMap implements BookListDataType{

    private HashMap<Long, Book> bookList = new HashMap<>();

    public BookListHashMap(){}
    @Override
    public void add(Book book) {
        bookList.put(book.getId(),book);
    }

    @Override
    public Set<Long> keySet() {
        return bookList.keySet();
    }


    @Override
    public Book get(long key) {
        return bookList.get(key);
    }

    @Override
    public void remove(Book book) {

    }
}
