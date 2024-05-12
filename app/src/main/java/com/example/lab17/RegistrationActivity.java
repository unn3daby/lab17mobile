package com.example.lab17;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class RegistrationActivity extends AppCompatActivity {

    private EditText fullnameEditText, emailEditText, usernameEditText, passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        fullnameEditText = findViewById(R.id.fullname_edit_text);
        emailEditText = findViewById(R.id.email_edit_text);
        usernameEditText = findViewById(R.id.username_edit_text);
        passwordEditText = findViewById(R.id.password_edit_text);

        Button registerButton = findViewById(R.id.register_button);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Получаем значения из полей ввода
                String fullname = fullnameEditText.getText().toString().trim();
                String email = emailEditText.getText().toString().trim();
                String username = usernameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                // Проверяем, что все поля заполнены
                if (!fullname.isEmpty() && !email.isEmpty() && !username.isEmpty() && !password.isEmpty()) {
                    // Отправляем данные на сервер
                    sendRegistrationData(fullname, email, username, password);
                } else {
                    Toast.makeText(RegistrationActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private class SendDataTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String url = params[0];
            String postData = params[1];
            try {
                HttpRequest httpRequest = new HttpRequest();
                return httpRequest.sendPostRequest(url, postData);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            if (result != null) {

                // Обработка результата от сервера
                    Toast.makeText(RegistrationActivity.this, "Registration successful!", Toast.LENGTH_SHORT).show();
                    // Здесь можно добавить переход на другую активити или выполнить другие действия
                    Toast.makeText(RegistrationActivity.this, "Registration successful!", Toast.LENGTH_SHORT).show();
                    // Переход на активити логина
                    Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                    startActivity(intent);
            } else {
                Toast.makeText(RegistrationActivity.this, "Error occurred", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void sendRegistrationData(String fullname, String email, String username, String password) {
        String url = "http://10.0.2.2:8080/signup.php";
        String postData = "fullname=" + fullname + "&email=" + email + "&username=" + username + "&password=" + password;

        new SendDataTask().execute(url, postData);
    }
}


