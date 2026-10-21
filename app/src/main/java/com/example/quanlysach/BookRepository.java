package com.example.quanlysach;

import java.util.ArrayList;

public class BookRepository {
    private static ArrayList<Book> bookList;

    public static ArrayList<Book> getBookList() {
        if (bookList == null) {
            bookList = new ArrayList<>();
            bookList.add(new Book("1", "Lập trình Android cơ bản", "Nguyễn Văn A", "Công nghệ", "Đang đọc", 4.0f, "Sách dành cho người mới bắt đầu"));
            bookList.add(new Book("2", "Đắc nhân tâm", "Dale Carnegie", "Kỹ năng sống", "Đã đọc", 5.0f, "Học cách đối nhân xử thế"));
            bookList.add(new Book("3", "Dế Mèn phiêu lưu ký", "Tô Hoài", "Văn học", "Chưa đọc", 0.0f, "Tác phẩm văn học kinh điển"));
        }
        return bookList;
    }

    public static void addBook(Book book) {
        getBookList().add(book);
    }

    public static Book getBookById(String id) {
        for (Book book : getBookList()) {
            if (book.getId().equals(id)) {
                return book;
            }
        }
        return null;
    }

    public static void deleteBook(String id) {
        ArrayList<Book> list = getBookList();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId().equals(id)) {
                list.remove(i);
                break;
            }
        }
    }

    public static int getTotalCount() {
        return getBookList().size();
    }

    public static int getReadCount() {
        int count = 0;
        for (Book b : getBookList()) {
            if ("Đã đọc".equalsIgnoreCase(b.getStatus())) {
                count++;
            }
        }
        return count;
    }

    public static int getUnreadCount() {
        return getTotalCount() - getReadCount();
    }
}
