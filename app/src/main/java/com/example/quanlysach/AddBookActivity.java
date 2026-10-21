package com.example.quanlysach;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddBookActivity extends AppCompatActivity {
    private EditText etTitle, etAuthor, etNote;
    private Spinner spGenre;
    private RadioGroup rgStatus;
    private RatingBar rbRating;
    private Button btnSave;
    private TextView tvHeaderBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        tvHeaderBack = findViewById(R.id.tvHeaderBack);
        if (tvHeaderBack != null) {
            tvHeaderBack.setOnClickListener(v -> finish());
        }

        etTitle = findViewById(R.id.etTitle);
        etAuthor = findViewById(R.id.etAuthor);
        etNote = findViewById(R.id.etNote);
        spGenre = findViewById(R.id.spGenre);
        rgStatus = findViewById(R.id.rgStatus);
        rbRating = findViewById(R.id.rbRatingAdd);
        btnSave = findViewById(R.id.btnSave);

        String[] genres = {"Văn học", "Khoa học", "Công nghệ", "Kỹ năng sống", "Khác"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, genres);
        spGenre.setAdapter(adapter);

        btnSave.setOnClickListener(v -> {
            String title = etTitle.getText().toString().trim();
            String author = etAuthor.getText().toString().trim();
            String note = etNote.getText().toString().trim();

            if (title.isEmpty()) {
                etTitle.setError("Tên sách không được để trống");
                return;
            }
            if (author.isEmpty()) {
                etAuthor.setError("Tác giả không được để trống");
                return;
            }

            String genre = spGenre.getSelectedItem().toString();
            String status = "Chưa đọc";
            int selectedStatusId = rgStatus.getCheckedRadioButtonId();
            if (selectedStatusId == R.id.rbReading) status = "Đang đọc";
            else if (selectedStatusId == R.id.rbRead) status = "Đã đọc";

            float rating = rbRating.getRating();
            String id = String.valueOf(System.currentTimeMillis());

            Book newBook = new Book(id, title, author, genre, status, rating, note);
            BookRepository.addBook(newBook);

            Toast.makeText(this, "Đã thêm sách thành công!", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
