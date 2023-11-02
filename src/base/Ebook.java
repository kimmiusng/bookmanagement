package base;

import java.time.LocalDate;

public class Ebook extends Book{
    String fileSize;

    public Ebook(){}

    public Ebook(Long id, String name, String author, Long isbn, LocalDate publishedDate, String fileSize){
        super(id, name, author, isbn, publishedDate);
        this.fileSize = fileSize;
    }
    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    @Override
    public String toString() {
        return super.toString() + "\t"
                + fileSize + "mb";
    }

    @Override
    public int hashCode() {
        return "Ebook".hashCode() + super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Ebook && super.equals(obj);
    }
}

