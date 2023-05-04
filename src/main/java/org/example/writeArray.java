package org.example;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class writeArray {
    public static void writeToFile(char[][] array, String filePath) {
        try {
            File file = new File(filePath);
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);

            for (char[] row : array) {
                for (char c : row) {
                    bw.write(c);
                }
                bw.newLine();
            }

            bw.close();
            fw.close();

            System.out.println("Array saved to file: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        char[][] parkingLotArray = {
                { 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R' },

                { 'R', 'C', 'C', 'C', 'C', 'R', 'C', 'C', 'C', 'R' },
                { 'R', 'C', 'C', 'C', 'C', 'R', 'C', 'C', 'C', 'R' },

                { 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R' },

                { 'R', 'E', 'E', 'E', 'E', 'R', 'E', 'E', 'E', 'R' },
                { 'R', 'E', 'E', 'E', 'C', 'R', 'E', 'E', 'E', 'R' },

                { 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R' },

                { 'R', 'E', 'E', 'E', 'E', 'R', 'E', 'E', 'E', 'R' },
                { 'R', 'E', 'E', 'E', 'E', 'R', 'E', 'E', 'E', 'R' },
                { 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R' }
        };
        writeToFile(parkingLotArray,"parkinglotMap/map1.txt");

    }
}
