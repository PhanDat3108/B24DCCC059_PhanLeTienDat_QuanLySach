package com.example.quanlysach;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class BookDetailActivity extends AppCompatActivity {
    private TextView tvTitle, tvAuthorGenre, tvStatus, tvNote;
    private RatingBar rbRating;
    private Button btnMarkRead, btnBack, btnDelete;
    private TextView tvDetailHeaderBack;
    private Book currentBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        tvDetailHeaderBack = findViewById(R.id.tvDetailHeaderBack);
        if (tvDetailHeaderBack != null) {
            tvDetailHeaderBack.setOnClickListener(v -> finish());
        }

        tvTitle = findViewById(R.id.tvDetailTitle);
        tvAuthorGenre = findViewById(R.id.tvDetailAuthorGenre);
        tvStatus = findViewById(R.id.tvDetailStatus);
        tvNote = findViewById(R.id.tvDetailNote);
        rbRating = findViewById(R.id.rbDetailRating);
        btnMarkRead = findViewById(R.id.btnMarkRead);
        btnBack = findViewById(R.id.btnBack);
        btnDelete = findViewById(R.id.btnDelete);

        String bookId = getIntent().getStringExtra("BOOK_ID");
        currentBook = BookRepository.getBookById(bookId);

        if (currentBook != null) {
            displayBookDetails();
        } else {
            finish();
        }

        btnMarkRead.setOnClickListener(v -> {
            currentBook.setStatus("Đã đọc");
            displayBookDetails();
            Toast.makeText(this, "Đã cập nhật tình trạng thành Đã đọc!", Toast.LENGTH_SHORT).show();
        });

        rbRating.setOnRatingBarChangeListener((ratingBar, rating, fromUser) -> {
            if (fromUser && currentBook != null) {
                currentBook.setRating(rating);
            }
        });

        btnBack.setOnClickListener(v -> finish());

        btnDelete.setOnClickListener(v -> {
            BookRepository.deleteBook(currentBook.getId());
            Toast.makeText(this, "Đã xóa sách khỏi thư viện!", Toast.LENGTH_SHORT).show();
            finish();
        });
    }

    private void displayBookDetails() {
        tvTitle.setText(currentBook.getTitle());
        tvAuthorGenre.setText(currentBook.getAuthor() + " · " + currentBook.getGenre());
        tvStatus.setText(currentBook.getStatus());
        rbRating.setRating(currentBook.getRating());
        
        String note = currentBook.getNote();
        tvNote.setText(note == null || note.isEmpty() ? "(Không có ghi chú)" : note);

        if ("Đã đọc".equalsIgnoreCase(currentBook.getStatus())) {
            btnMarkRead.setEnabled(false);
            btnMarkRead.setAlpha(0.5f);
        } else {
            btnMarkRead.setEnabled(true);
            btnMarkRead.setAlpha(1.0f);
        }
    }
}
