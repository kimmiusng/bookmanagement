package base;

import base.BM.BM5;
import base.BM.BookManager;

public class Execute {
    static BookManager bm = new BM5();

    public static void main(String[] args) {
        bm.init();
        bm.interactWithUser();
    }
}