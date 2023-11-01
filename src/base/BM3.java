package base;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

public class BM3 extends BookManager {
    private ArrayList<Book> bookList = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    @Override
    void init() {
        bookList.add(new Book(1L, "돈의 속성(300쇄 리커버에디션)", "김승호", Long.parseLong("9791188331796"), LocalDate.parse("2020-06-15")));
        bookList.add(new Ebook(2L,"K 배터리 레볼루션", "박순혁", Long.parseLong("9791191521221"), LocalDate.parse("2024-02-20"),"45"));
        bookList.add(new AudioBook(3L, "위기의 역사", "오건영", Long.parseLong("9791169850360"), LocalDate.parse("2023-07-19"),"567","KOR",3500));
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
                    printBook();
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
        //System.out.println("등록 메서드 실행");
        // 1. 콘솔화면을 통해 사용자로부터 도서정보를 입력을 받는다.
        // id, 제목, 저자, isbn, 출판일 (5가지) (v)
        // 위의 정보로 책 객체를 생성한다. (v)
        // 2. 도서를 등록한다.
        // 사서를 통해 도서 저장 요청
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

        // book을 저장소에 저장
        Book book;
        if(Integer.parseInt(bookType) == 1) {
            book = new Book(Long.parseLong(bookInfo[0]),
                    bookInfo[1],
                    bookInfo[2],
                    Long.parseLong(bookInfo[3]),
                    LocalDate.parse(bookInfo[4]));
            bookList.add(book);
        } else if(Integer.parseInt(bookType) == 2){
            book = new Ebook(Long.parseLong(bookInfo[0]),
                    bookInfo[1],
                    bookInfo[2],
                    Long.parseLong(bookInfo[3]),
                    LocalDate.parse(bookInfo[4]),
                    bookInfo[5]);
            bookList.add(book);
        } else {
            book = new AudioBook(Long.parseLong(bookInfo[0]),
                    bookInfo[1],
                    bookInfo[2],
                    Long.parseLong(bookInfo[3]),
                    LocalDate.parse(bookInfo[4]),
                    bookInfo[5],
                    bookInfo[6],
                    Integer.parseInt(bookInfo[7]));
            bookList.add(book);
        }
    }

    void printBook() {
        while (true) {
            System.out.println("■■■■■■ 도서 조회 메뉴 ■■■■■■");
            System.out.println("(1) 전체 조회");
            System.out.println("(2) 책 제목으로 조회");
            System.out.println("(3) 도서 이름 사전순 조회");
            System.out.println("(4) 출간일 기간으로 조회");
            System.out.println("(5) 출간일순 조회");
            System.out.println("(q) 메뉴 나가기");
            System.out.print("선택 >> ");
            String userInput = sc.nextLine();

            switch (userInput) {
                case "1":
                    printAllBook();
                    return;
                case "2":
                    printBookName();
                    return;
                case "3":
                    printSortBookName();
                    return;
                case "4":
                    printBookPublishDate();
                    return;
                case "5":
                    printSortBookPublishDate();
                    return;
                case "q":
                    return;
                default:
                    System.out.println("Error! 잘못된 입력입니다. 다시 입력해주세요.");
            }
        }
    }

    void printBookForm(Book book){
        System.out.print("[");
        if(book instanceof Ebook){
            System.out.print("Ebook, ");
        } else if(book instanceof  AudioBook){
            System.out.print("AudioBook, ");
        } else{
            System.out.print("Book, ");
        }
        System.out.print(book.getId());
        System.out.print(", ");
        System.out.print(book.getName());
        System.out.print(", ");
        System.out.print(book.getAuthor());
        System.out.print(", ");
        System.out.print(book.getIsbn());
        System.out.print(", ");
        System.out.print(book.getPublishedDate());
        if(book instanceof Ebook){
            System.out.print(", ");
            System.out.print(((Ebook)book).getFileSize() + "mb");
        } else if(book instanceof AudioBook){
            System.out.print(", ");
            System.out.print(((AudioBook)book).getFileSize() + "mb");
            System.out.print(", ");
            System.out.print(((AudioBook)book).getLanguage());
            System.out.print(", ");
            System.out.print(((AudioBook)book).getPlayTime() + "초");
        }
        System.out.print("]");
        System.out.println();
    }

    @Override
    void printAllBook() {
        for (Book book : bookList) {
            printBookForm(book);
        }

    }

    void printBookName(){
        String name = "";
        do {System.out.print("검색할 도서의 이름을 입력하세요.: ");
        name = sc.nextLine();} while (name.isEmpty());
        int count = 0;
        for(Book book : bookList){
            String check = book.getName().replaceAll(name,"");
            if(!book.getName().equals(check)){
                printBookForm(book);
                count += 1;
            }
        }
        if(count == 0){
            System.out.println("해당 도서는 존재하지 않습니다.");
        } else{
            System.out.println("총 검색결과 : " + count + "건");
        }
    }

    void printSortBookName(){
        ArrayList<String> names = new ArrayList<>();
        for(Book book : bookList){
            names.add(book.getName());
        }
        Collections.sort(names);
        for(String name : names){
            for(Book book : bookList){
                if(name.equals(book.getName())) {
                    printBookForm(book);
                    break;
                }
            }
        }
    }

    void printBookPublishDate(){
        String start;
        String end;
        System.out.println("검색할 출간일의 기간을 입력하세요.");
        do {System.out.print("(YYYY-MM-DD형식으로 입력)부터 : ");
            start = sc.nextLine();} while (!isLocalDate(start));
        do {System.out.print("(YYYY-MM-DD형식으로 입력)까지 : ");
            end = sc.nextLine();} while (!isLocalDate(end));
        int count = 0;
        for(Book book : bookList){
            if(book.getPublishedDate().isAfter(LocalDate.parse(start)) && book.getPublishedDate().isBefore(LocalDate.parse(end))){
                printBookForm(book);
                count += 1;
            }
        }
        if(count == 0){
            System.out.println("해당 도서는 존재하지 않습니다.");
        } else{
            System.out.println("총 검색결과 : " + count + "건");
        }
    }

    void printSortBookPublishDate(){
        ArrayList<LocalDate> publishDates = new ArrayList<>();
        for(Book book : bookList){
            publishDates.add(book.getPublishedDate());
        }
        Collections.sort(publishDates);
        for(LocalDate publishDate : publishDates){
            for(Book book : bookList){
                if(publishDate.equals(book.getPublishedDate())) {
                    printBookForm(book);
                    break;
                }
            }
        }
    }

    @Override
    void updateBook() {
        System.out.println("수정 메서드 실행");
        // 1. 수정할 도서를 찾는다. (사서는 알 수 있다.) (v)
        // 있으면 수정 가능
        // 없으면 수정 불가
        // 2. 수정할 도서가 있을 때:
        // 새로운 입력 값 : 사용자로부터 입력받는다.
        // 도서 정보(필드)를 바꾼다.
        String id = "";
        do {System.out.print("수정할 도서번호를 입력해주세요: ");
        id = sc.nextLine();} while (!isLong(id));
        Book book = findBook(Long.parseLong(id));

        // 책이 존재하지 않을 때
        if (book == null) {
            System.out.println("입력하신 책을 찾을 수 없습니다.");
            return;
        }
        // 책이 존재할 때
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
        // 1. 삭제할 도서를 찾는다.
        // 없으면 삭제 불가
        // 있으면 삭제 가능
        // 2. 삭제할 도서가 있다면
        // 사서한테 도서 삭제 요청
        String id = "";
        do {System.out.print("삭제할 도서번호를 입력해주세요: ");
        id = sc.nextLine();} while (!isLong(id));

        Book book = findBook(Long.parseLong(id));
        if (book == null) {
            System.out.println("입력하신 책을 찾을 수 없습니다.");
        }
        bookList.remove(book);
    }

    public Book findBook(long id) {
        for (Book book : bookList) {
            if (id == book.getId()) {
                return book;
            }
        }
        // bookList를 다 돌았는데 해당 id의 도서를 못찾았다.
        return null;
    }


    //do {System.out.print("id: ");
    //        bookInfo[0] = sc.nextLine();} while (!isLong(bookInfo[0]));
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

}
