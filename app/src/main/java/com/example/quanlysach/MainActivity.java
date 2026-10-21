package com.example.quanlysach;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    private TextView tvTotalCount, tvReadCount, tvUnreadCount;
    private RecyclerView rvBooks;
    private Button btnAddBook;
    private BookAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTotalCount = findViewById(R.id.tvTotalCount);
        tvReadCount = findViewById(R.id.tvReadCount);
        tvUnreadCount = findViewById(R.id.tvUnreadCount);
        rvBooks = findViewById(R.id.rvBooks);
        btnAddBook = findViewById(R.id.btnAddBook);

        rvBooks.setLayoutManager(new LinearLayoutManager(this));

        btnAddBook.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddBookActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        tvTotalCount.setText(String.valueOf(BookRepository.getTotalCount()));
        tvReadCount.setText(String.valueOf(BookRepository.getReadCount()));
        tvUnreadCount.setText(String.valueOf(BookRepository.getUnreadCount()));

        adapter = new BookAdapter(this, BookRepository.getBookList(), book -> {
            Intent intent = new Intent(MainActivity.this, BookDetailActivity.class);
            intent.putExtra("BOOK_ID", book.getId());
            startActivity(intent);
        });
        rvBooks.setAdapter(adapter);
    }
}
