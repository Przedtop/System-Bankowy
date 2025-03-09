package com.przedtop.bank.system.logs;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class WriteLog {
    private static final String LOG_FILE_PATH = "resources/logs.txt";

    public static void writeLog(String message, Optional<String> apiDir) {
        try {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String date = now.format(formatter);
            String logMessage;
            if (apiDir.isPresent())
                logMessage = date + ": " + apiDir.get() + message + System.lineSeparator();
            else
                logMessage = date + ": " + message + System.lineSeparator();

            Path path = Paths.get(LOG_FILE_PATH);

            if (!Files.exists(path)) {
                Files.createDirectories(Paths.get("resources"));
                Files.createFile(path);
            }

            Files.write(path, logMessage.getBytes(), StandardOpenOption.APPEND);

        } catch (IOException ignored) {
        }
    }

    public static void writeLogAndPrintMessage(String message, Optional<String> apiDir) {
        try {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String date = now.format(formatter);
            String logMessage;
            if (apiDir.isPresent())
                logMessage = date + ": " + apiDir.get() + message + System.lineSeparator();
            else
                logMessage = date + ": " + message + System.lineSeparator();

            Path path = Paths.get(LOG_FILE_PATH);

            if (!Files.exists(path)) {
                Files.createDirectories(Paths.get("resources"));
                Files.createFile(path);
            }

            Files.write(path, logMessage.getBytes(), StandardOpenOption.APPEND);
            if (apiDir.isPresent())
                System.out.println(apiDir.get() + message);
            else
                System.out.println(message);
        } catch (IOException ignored) {
        }
    }
}
