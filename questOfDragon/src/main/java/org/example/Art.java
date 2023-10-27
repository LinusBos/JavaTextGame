package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Art {
    // Prints the ASCII art.
    public void printDragon() {
        File dragon = new File("src/main/resources/dragonASCII");
        Scanner sc;
        try {
            sc = new Scanner(dragon);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while(sc.hasNextLine()){
            System.out.println(sc.nextLine());
        }
    }
}

