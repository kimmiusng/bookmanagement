package base;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.*;
import base.BookRepository.*;

public class BM5 extends BookManager{
    private BookRepository bookList = new BookHashMap();

    static Scanner sc = new Scanner(System.in);
    @Override
    void init() {
        bookList.addBook(new Book(1L, "돈의 속성(300쇄 리커버에디션)", "김승호", Long.parseLong("9791188331796"), LocalDate.parse("2020-06-15")));
        bookList.addBook(new Ebook(8L, "돈의 속성(300쇄 리커버에디션)", "김승호", Long.parseLong("9791188331796"), LocalDate.parse("2020-06-15"),"45"));
        bookList.addBook(new Ebook(81L, "돈의 속성(300쇄 리커버에디션)", "김승호", Long.parseLong("9791188331796"), LocalDate.parse("2020-06-15"),"45"));
        bookList.addBook(new Book(7L, "돈의 속성(300쇄 리커버에디션)", "김승호", Long.parseLong("9791188331796"), LocalDate.parse("2020-06-15")));
        bookList.addBook(new Ebook(2L,"K 배터리 레볼루션", "박순혁", Long.parseLong("9791191521221"), LocalDate.parse("2024-02-20"),"45"));
        bookList.addBook(new AudioBook(3L, "위기의 역사", "오건영", Long.parseLong("9791169850360"), LocalDate.parse("2023-07-19"),"567","KOR",3500));
        bookList.addBook(new Ebook(3L, "위기의 역사", "오건영", Long.parseLong("9791169850360"), LocalDate.parse("2023-07-19"),"75"));
    }

    @Override
    void interactWithUser() {
        while (true) {
            System.out.println("■■■■■■ 도서 관리 프로그램 ■■■■■■");
            System.out.println("(1) 도서 조회");
            System.out.println("(2) 도서 등록");
            System.out.println("(3) 도서 수정");
            System.out.println("(4) 도서 삭제");
            System.out.println("(q) 프로그램 종료");
            System.out.print("선택 >> ");
            String userInput = sc.nextLine();

            switch (userInput) {
                case "1":
                    // 조회
                    printAllBook();
                    break;
                case "2":
                    // 등록
                    addBook();
                    break;
                case "3":
                    // 수정
                    updateBook();
                    break;
                case "4":
                    // 삭제
                    removeBook();
                    break;
                case "q":
                    // 메소드를 종료
                    System.out.println("프로그램 종료!");
                    return;
            }

        }

    }

    @Override
    void addBook() {
        String[] bookInfo = new String[8];
        System.out.print("등록할 책의 종류를 선택해주세요.\n(1)Book\n(2)Ebook\n(3)AudioBook\n>> ");
        String bookType = sc.nextLine();
        if(!bookType.equals("1") && !bookType.equals("2") && !bookType.equals("3")){
            System.out.println("Error : 잘못된 입력입니다.");
            return;
        }

        do {System.out.print("id: ");
            bookInfo[0] = sc.nextLine();} while (!isLong(bookInfo[0]));
        System.out.print("제목: ");
        bookInfo[1] = sc.nextLine();
        System.out.print("저자: ");
        bookInfo[2] = sc.nextLine();
        do {System.out.print("isbn: ");
            bookInfo[3] = sc.nextLine();} while (!isLong(bookInfo[3]));
        do {System.out.print("출판일(YYYY-MM-DD): ");
            bookInfo[4] = sc.nextLine();} while (!isLocalDate(bookInfo[4]));
        if(Integer.parseInt(bookType) == 2){
            System.out.print("파일크기(mb) : ");
            bookInfo[5] = sc.nextLine();
        } else if(Integer.parseInt(bookType) == 3){
            System.out.print("파일크기(mb) : ");
            bookInfo[5] = sc.nextLine();
            System.out.print("재생언어 : ");
            bookInfo[6] = sc.nextLine();
            do {System.out.print("재생시간(초) : ");
                bookInfo[7] = sc.nextLine();} while (!isLong(bookInfo[7]));
        }

        Book book;
        if(Integer.parseInt(bookType) == 1) {
            book = new Book(Long.parseLong(bookInfo[0]),
                    bookInfo[1],
                    bookInfo[2],
                    Long.parseLong(bookInfo[3]),
                    LocalDate.parse(bookInfo[4]));
            bookList.addBook(book);
        } else if(Integer.parseInt(bookType) == 2){
            book = new Ebook(Long.parseLong(bookInfo[0]),
                    bookInfo[1],
                    bookInfo[2],
                    Long.parseLong(bookInfo[3]),
                    LocalDate.parse(bookInfo[4]),
                    bookInfo[5]);
            bookList.addBook(book);
        } else {
            book = new AudioBook(Long.parseLong(bookInfo[0]),
                    bookInfo[1],
                    bookInfo[2],
                    Long.parseLong(bookInfo[3]),
                    LocalDate.parse(bookInfo[4]),
                    bookInfo[5],
                    bookInfo[6],
                    Integer.parseInt(bookInfo[7]));
            bookList.addBook(book);
        }
    }

    @Override
    void printAllBook() {
        while (true) {
            System.out.println("■■■■■■ 도서 조회 메뉴 ■■■■■■");
            System.out.println("(1) 전체 조회");
            System.out.println("(2) 책 제목으로 조회");
            System.out.println("(3) 도서 이름 사전순 조회");
            System.out.println("(4) 출간일 기간으로 조회");
            System.out.println("(5) 출간일순 조회");
            System.out.println("(6) 중복 책 찾기");
            System.out.println("(q) 메뉴 나가기");
            System.out.print("선택 >> ");
            String userInput = sc.nextLine();

            switch (userInput) {
                case "1":
                    printing(printBook());
                    return;
                case "2":
                    printing(printBookName());
                    return;
                case "3":
                    printing(printSortBookName());
                    return;
                case "4":
                    printing(printBookPublishDate());
                    return;
                case "5":
                    printing(printSortBookPublishDate());
                    return;
                case "6":
                    printSameBook();
                    return;
                case "q":
                    return;
                default:
                    System.out.println("Error! 잘못된 입력입니다. 다시 입력해주세요.");
            }
        }

    }

    @Override
    void updateBook() {
        System.out.println("수정 메서드 실행");
        String id;
        do {System.out.print("수정할 도서번호를 입력해주세요: ");
            id = sc.nextLine();} while (!isLong(id));
        Book book = bookList.getBook(Long.parseLong(id));

        if (book == null) {
            System.out.println("입력하신 책을 찾을 수 없습니다.");
            return;
        }

        String[] bookInfo = new String[8];
        bookInfo[0] = id;
        System.out.println("[수정 정보를 입력해주세요]");
        System.out.print("제목: ");
        bookInfo[1] = sc.nextLine();
        System.out.print("저자: ");
        bookInfo[2] = sc.nextLine();
        do {System.out.print("isbn: ");
            bookInfo[3] = sc.nextLine();} while (!isLong(bookInfo[3]));
        do {System.out.print("출판일(YYYY-MM-DD): ");
            bookInfo[4] = sc.nextLine();} while (!isLocalDate(bookInfo[4]));
        if(book instanceof Ebook){
            System.out.print("파일크기(mb) : ");
            bookInfo[5] = sc.nextLine();
        } else if(book instanceof AudioBook){
            System.out.print("파일크기(mb) : ");
            bookInfo[5] = sc.nextLine();
            System.out.print("재생언어 : ");
            bookInfo[6] = sc.nextLine();
            do {System.out.print("재생시간(초) : ");
                bookInfo[7] = sc.nextLine();} while (!isLong(bookInfo[7]));
        }

        book.setName(bookInfo[1]);
        book.setAuthor(bookInfo[2]);
        book.setIsbn(Long.parseLong(bookInfo[3]));
        book.setPublishedDate(LocalDate.parse(bookInfo[4]));
        if(book instanceof Ebook){
            ((Ebook)book).setFileSize(bookInfo[5]);
        } else if(book instanceof AudioBook){
            ((AudioBook)book).setFileSize(bookInfo[5]);
            ((AudioBook)book).setLanguage(bookInfo[6]);
            ((AudioBook)book).setPlayTime(Integer.parseInt(bookInfo[7]));
        }

    }

    @Override
    void removeBook() {
        System.out.println("삭제 메서드 실행");
        String id;
        do {System.out.print("삭제할 도서번호를 입력해주세요: ");
            id = sc.nextLine();} while (!isLong(id));

        Book book = bookList.getBook(Long.parseLong(id));
        if (book == null) {
            System.out.println("입력하신 책을 찾을 수 없습니다.");
        }
        bookList.removeBook(book);

    }
    public Boolean isLong(String str){
        try{
            Long.parseLong(str);
            return true;
        } catch (NumberFormatException e){
            System.out.println("Error! 입력값이 숫자가 아닙니다. 다시 입력해주세요.");
            return false;
        }
    }

    public Boolean isLocalDate(String str){
        try{
            LocalDate.parse(str);
            return true;
        } catch (DateTimeException e){
            System.out.println("Error! 주어진 날짜 형식이 아닙니다. 다시 입력해주세요.");
            return false;
        }
    }

    public void printing(List<Book> printBookList){
        if(printBookList.isEmpty()){
            System.out.println("조회할 수 있는 도서가 없습니다.");
            return;
        }
        for(Book book : printBookList){
            System.out.println(book.toString());
        }
    }

    public List<Book> printBook(){
        return bookList.getBooks();
    }

    public List<Book> printBookName(){
        String name;
        do {System.out.print("검색할 도서의 이름을 입력하세요.: ");
            name = sc.nextLine();} while (name.isEmpty());
        String finalName = name;
        return bookList.getBooks(book -> book.getName().contains(finalName));
    }

    public List<Book> printSortBookName(){
        return bookList.getBooks((book1,book2) -> Integer.compare(book1.getName().compareTo(book2.getName()), 0));
    }

    public List<Book> printBookPublishDate(){
        String start;
        String end;
        System.out.println("검색할 출간일의 기간을 입력하세요.");
        do {System.out.print("(YYYY-MM-DD형식으로 입력)부터 : ");
            start = sc.nextLine();} while (!isLocalDate(start));
        do {System.out.print("(YYYY-MM-DD형식으로 입력)까지 : ");
            end = sc.nextLine();} while (!isLocalDate(end));
        LocalDate finalStrat = LocalDate.parse(start);
        LocalDate finalEnd = LocalDate.parse(end);
        return bookList.getBooks(book -> (book.getPublishedDate().isAfter(finalStrat))
                                        && book.getPublishedDate().isBefore(finalEnd));
    }

    public List<Book> printSortBookPublishDate(){
        return bookList.getBooks((book1,book2) -> Integer.compare(book1.getPublishedDate().compareTo(book2.getPublishedDate()), 0));
    }

    public void printSameBook(){
        int count = 0;
        int i = 0;
        List<Book> bookList1 = new ArrayList<>(bookList.getBooks());
        while(bookList1.size() > i) {
            Book book1 = bookList1.get(i);
            if(bookList.getBooks(book -> book.hashCode() == book1.hashCode()).size() != 1){
                System.out.println("-----------------------------------------------");
                for(Book book2 : bookList.getBooks(book -> book.hashCode() == book1.hashCode())){
                    System.out.println(book2.toString());
                    bookList1.remove(book2);
                }
                count += 1;
            }
            i++;
        }
        System.out.println("-----------------------------------------------");
        System.out.println("중복된 책의 수 : " + count);
    }
}
