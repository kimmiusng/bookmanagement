package base;

import java.time.LocalDate;

public class Execute {
    static BookManager bm = new BM4();

    public static void main(String[] args) {
        bm.init();
        //bm.interactWithUser();
        ((BM4)bm).printSortBookName();
    }
}