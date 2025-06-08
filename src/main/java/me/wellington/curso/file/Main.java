package me.wellington.curso.file;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public void init() throws Exception {
        var file = new File("C:\\Users\\Wellington\\Desktop\\arquivo.txt");
        var scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            System.out.println(scanner.nextLine());
        }

        scanner.close();
    }

    public static void main(String[] args) {
        try {
            new Main().init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
