package com.statkevich.receipttask.reader;

import com.statkevich.receipttask.dto.InputValueDto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class FileReader implements Reader {

    private final ConsoleReader consoleReader;

    public FileReader(ConsoleReader consoleReader) {
        this.consoleReader = consoleReader;
    }

    @Override
    public InputValueDto read(List<String> orderList) {
        try {
            Optional<String> file = orderList.stream()
                    .filter(row -> row.startsWith("file"))
                    .map(row -> row.split("-")[1])
                    .findAny();

            String parameterFile = file.orElse(null);
            assert parameterFile != null;
            java.io.FileReader fileReader = new java.io.FileReader(parameterFile);
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