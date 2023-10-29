package org.example;

import org.json.simple.JSONObject;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class UpdateMethod2 {
    static void run() {
        System.out.println("Gửi dữ liệu được đóng gói trong body request");
        String updateUrl = "https://api.thingspeak.com/update?api_key=T7H40F0X82VGW7L5";
        Scanner sn = new Scanner(System.in);
        System.out.print("   Nhập nhiệt độ: ");
        int field1 = sn.nextInt();
        System.out.print("   Nhập độ ẩm: ");
        int field2 = sn.nextInt();
        try {
            JSONObject jsonData = new JSONObject();
            jsonData.put("field1", field1);
            jsonData.put("field2", field2);

            URL url = new URL(updateUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            System.out.println(url.toString());

            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            try (OutputStream os = connection.getOutputStream();
                 OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8")) {
                osw.write(jsonData.toString());
                osw.flush();
            }

            int responseCode = connection.getResponseCode();
            if (responseCode == 200)
                System.out.println("* Gửi dữ liệu thành công!");
            else System.out.println("* Gửi dữ liệu không thành công!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
