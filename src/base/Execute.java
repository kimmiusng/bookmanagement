package base;

import base.BM.*;
import base.BM.BookManager;
public class Execute {
    static BookManager bm = new BM5();

    public static void main(String[] args) {


        bm.init();
        bm.interactWithUser();
    }
}