package org.example;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class UpdateMethod1 {
    static void run() {
        System.out.println("  Gửi dữ liệu được đóng gói trong url");
        String baseUrl = "https://api.thingspeak.com/update?api_key=T7H40F0X82VGW7L5";
        Scanner sn = new Scanner(System.in);
        System.out.print("   Nhập nhiệt độ: ");
        int field1 = sn.nextInt();
        System.out.print("   Nhập độ ẩm: ");
        int field2 = sn.nextInt();

        try {
            String updateUrl = baseUrl + "&field1=" + field1 + "&field2=" + field2;
            URL url = new URL(updateUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            System.out.println(url.toString());

            int responseCode = connection.getResponseCode();
            if (responseCode == 200)
                System.out.println("* Gửi dữ liệu thành công!");
            else System.out.println("* Gửi dữ liệu không thành công!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
