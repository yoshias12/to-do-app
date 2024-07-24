package com.enigmacamp.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.Scanner;


public class Helper {
    Scanner scanner = new Scanner(System.in);


    public Integer inputInt(String string) {
        try {
            System.out.printf("%s : ", string);
            Integer number = Integer.parseInt(scanner.nextLine());
            if (number < 0){
                System.out.println("Input tidak boleh kosong dan tidak boleh kurang dari 0...");
                return inputInt(string);
            }
            return number;
        } catch (NumberFormatException e) {
            System.out.println("Input harus angka....");
            return inputInt(string);
        }
    }


    public String inputString(String inputan) {

        while (true) {
            System.out.printf("%s : ", inputan);
            String input = scanner.nextLine();

            if (input.isBlank() || input.isEmpty()) {
                System.out.println("Input tidak boleh kosong...");
                continue;
            }
            return input;
        }
    }

    public String inputStringU(String inputan) {

        while (true) {
            System.out.printf("%s : ", inputan);
            String input = scanner.nextLine();

            return input;
        }
    }
}
