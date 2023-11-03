package base.BookListDataType;

import base.Book;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public interface BookListDataType {
    public void add(Book book);
    public Set<Long> keySet();
    public Book get(long key);
    public void remove(Book book);
}
