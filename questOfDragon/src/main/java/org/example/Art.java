package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Art {
    public void printDragon() throws FileNotFoundException {
        File dragon = new File("src/main/resources/dragonASCII");
        Scanner sc = new Scanner(dragon);
        while(sc.hasNextLine()){
            String print = sc.nextLine();
            System.out.println(print);
        }
    }
}

