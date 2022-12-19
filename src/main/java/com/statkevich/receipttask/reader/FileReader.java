package com.statkevich.receipttask.reader;

import com.statkevich.receipttask.dto.InputValuesDto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReader implements Reader {

    private final ConsoleReader consoleReader;

    public FileReader(ConsoleReader consoleReader) {
        this.consoleReader = consoleReader;
    }

    @Override
    public InputValuesDto read(List<String> orderList) {
        try {
            java.io.FileReader fileReader = new java.io.FileReader("parameterFile.txt");
            Scanner scan = new Scanner(fileReader);
            List<String> args = new ArrayList<>();
            while (scan.hasNextLine()) {
                args.add(scan.nextLine());
            }
            fileReader.close();
            return consoleReader.read(args);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
