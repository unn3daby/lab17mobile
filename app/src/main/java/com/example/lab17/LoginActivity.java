package com.example.lab17;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText, passwordEditText;
    private Button loginButton;
    private String gusername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Находим элементы пользовательского интерфейса
        usernameEditText = findViewById(R.id.username_edit_text);
        passwordEditText = findViewById(R.id.password_edit_text);
        loginButton = findViewById(R.id.login_button);

        // Устанавливаем обработчик клика на кнопку входа
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Получаем введенные данные из полей
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                // Выполняем асинхронную задачу для отправки данных на сервер
                new SendLoginDataTask().execute(username, password);

                gusername = username;
            }
        });
    }

    // Внутренний класс для выполнения асинхронной задачи отправки данных на сервер
    private class SendLoginDataTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            // Отправляем POST запрос на сервер для аутентификации
            try {
                String username = params[0];
                String password = params[1];
                String postData = "username=" + username + "&password=" + password;
                String response = HttpRequest.sendPostRequest("http://10.0.2.2:8080/login.php", postData);
                return response;
            } catch (Exception e) {
                return "Error: " + e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            // Обрабатываем результат после выполнения запроса
            if (result.equals("Login Success")) {
                // Если вход успешен, отображаем сообщение об успешном входе

                Toast.makeText(LoginActivity.this, "Вход выполнен успешно", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, UserInfoActivity.class);
                intent.putExtra("username", gusername);
                startActivity(intent);
            } else {
                // Если вход неуспешен, отображаем сообщение об ошибке
                Toast.makeText(LoginActivity.this, "Ошибка входа: " + result, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
