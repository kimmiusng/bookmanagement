package base.BookListDataType;

import base.Book.Book;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class BookListArrayList implements BookListDataType{

    private ArrayList<Book> bookList = new ArrayList<>();

    @Override
    public void add(Book book) {
        bookList.add(book);
    }

    @Override
    public Set<Long> keySet() {
        Set<Long> keySet = new HashSet<>();
        for(Book book : bookList){
            keySet.add(book.getId());
        }
        return keySet;
    }

    @Override
    public Book get(long key) {
        for(Book book : bookList){
            if(book.getId() == key){
                return book;
            }
        }
        return null;
    }

    @Override
    public void remove(Book book) {
        bookList.remove(book);
    }
}
