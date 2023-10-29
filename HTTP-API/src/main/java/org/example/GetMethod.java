package org.example;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GetMethod {
    static void run() {
        String getUrl = "https://api.thingspeak.com/channels/1529099/feeds.json?results=2";
        try {
            URL url = new URL(getUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                JSONParser parser = new JSONParser();
                JSONObject responseData = (JSONObject) parser.parse(response.toString());
                JSONArray feeds = (JSONArray) responseData.get("feeds");

                // Lấy dữ liệu cho các trường field1 (temperature) và field2 (humidity)
                for (Object feed : feeds) {
                    JSONObject data = (JSONObject) feed;
                    String temperature = (String) data.get("field1");
                    String humidity = (String) data.get("field2");
                    String timestamp = (String) data.get("created_at");

                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
                    Date date = dateFormat.parse(timestamp);
                    SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                    String formattedTimestamp = outputDateFormat.format(date);
                    System.out.println("  Thời gian: " + formattedTimestamp);
                    System.out.println("  Nhiệt độ: " + temperature + "°C");
                    System.out.println("  Độ ẩm: " + humidity + "%");
                    System.out.println();
                }
            } else {
                System.out.println("Lấy dữ liệu từ ThingSpeak không thành công.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
