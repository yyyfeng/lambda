package com.example.lambda.protice;

import org.springframework.stereotype.Controller;

import java.util.Scanner;

@Controller
public class ScannerDemo {

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        String str = scanner.nextLine();
        System.out.println("test:"+str);
    }
}
