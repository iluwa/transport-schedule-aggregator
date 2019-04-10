package com.github.iluwa.transportscheduleaggregator.utils;

import java.io.*;
import java.util.Scanner;

/**
 * One instance of class used to work with one file
 */
public class StringToFile {
    private final String filePath;

    public StringToFile(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Save input String to filepath
     * @param strToWrite
     * @param appendMode - if true, opens file in append mode
     * @throws IOException
     */
    public void toFile(String strToWrite, boolean appendMode) throws IOException {
        try (PrintWriter printWriter = new PrintWriter(new FileWriter(filePath, appendMode))) {
            printWriter.println(strToWrite);
        }
    }

    /**
     * Extract String from filepath
     * @return
     * @throws IOException
     */
    public String fromFile() throws IOException {
        try (Scanner sc = new Scanner(new FileReader(filePath))) {
            StringBuilder sb = new StringBuilder();
            while (sc.hasNextLine()) {
                sb.append(sc.nextLine());
            }
            return sb.toString();
        }
    }
}
