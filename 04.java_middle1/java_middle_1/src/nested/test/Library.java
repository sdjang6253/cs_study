package nested.test;

public class Library {

    private Book[] books;
    private int count;
    private int idx;

    public Library(int count) {
        this.count = count;
        this.books = new Book[count];
        this.idx = 0;
    }

    public void addBook(String title, String author){
        Book book = new Book(title, author);
        if(idx == count ){
            System.out.println("도서관 저장 공간이 부족합니다.");
            return;
        }
        books[idx++] = book;
    }

    public void showBooks(){
        System.out.println("== 책 목록 출력 ==");
        for (int i = 0; i < idx; i++) {
            System.out.println(books[i]);
        }
    }

    private static class Book{
        private final String title;
        private final String author;

        public Book(String title, String author) {
            this.title = title;
            this.author = author;
        }

        @Override
        public String toString() {
            return "도서 제목 : "+ title + " , 저자 : " + author ;
        }
    }
}
