package base.BM;


import java.io.FileNotFoundException;
import java.io.IOException;

public abstract class BookManager {
    public abstract void init();
    public abstract void interactWithUser();
    abstract void addBook();
    abstract void printAllBook();
    abstract void updateBook();
    abstract void removeBook();
}