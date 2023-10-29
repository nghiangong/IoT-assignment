package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        while (true) {
            showFunction();
            System.out.print("\n-Chọn chức năng: ");

            Scanner sn = new Scanner(System.in);
            int selection = sn.nextInt();
            switch (selection) {
                case 1:
                    UpdateMethod1.run();
                    break;
                case 2:
                    UpdateMethod2.run();
                    break;
                case 3:
                    GetMethod.run();
                    break;
                default:
                    System.exit(0);
            }
            for (int i = 1; i <= 100; i++) System.out.print("-");
            System.out.println();
        }
    }

    static void showFunction() {
        System.out.println("-- Chức năng: --");
        System.out.println("   1: Gửi dữ liệu được đóng gói trong url.");
        System.out.println("   2: Gửi dữ liệu được đóng gói trong body request.");
        System.out.println("   3: Lấy dữ liệu từ server.");
        System.out.println("   0: Thoát.");
    }

}
