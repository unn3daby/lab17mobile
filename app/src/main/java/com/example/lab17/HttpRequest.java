package com.example.lab17;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class HttpRequest {

    public static String sendPostRequest(String url, String postData) {
        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // Устанавливаем метод запроса и Content-Type
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            // Включаем вывод данных в запрос
            con.setDoOutput(true);

            // Записываем данные в тело запроса
            OutputStream os = con.getOutputStream();
            os.write(postData.getBytes());
            os.flush();
            os.close();

            // Получаем ответ от сервера
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Возвращаем ответ от сервера
            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null; // или можно выбросить исключение или вернуть пустую строку в зависимости от логики вашего приложения
        }
    }
}

