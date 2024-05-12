package com.example.lab17;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class UserInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        // Получаем данные из предыдущей активити
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String username = extras.getString("username");

            TextView usernameTextView = findViewById(R.id.username_text_view);
            usernameTextView.setText("Username: " + username);
        }
    }
}

