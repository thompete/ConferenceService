package com.thompete.conferenceservice.component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;
import org.springframework.util.FileSystemUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;

@Component
public class MailerMockup implements IMailer {
    private Path logsDirectory = Paths.get("logs");

    @PostConstruct
    private void postConstruct() {
        try {
            FileSystemUtils.deleteRecursively(logsDirectory);
            Files.createDirectories(logsDirectory);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @PreDestroy
    private void preDestroy() {
        try {
            FileSystemUtils.deleteRecursively(logsDirectory);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void send(String email, String message) {
        String line = LocalDateTime.now().toString() + '\t' + email + '\t' + message + '\n';
        try {
            Files.write(logsDirectory.resolve(Paths.get("mails.txt")), line.getBytes(),
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
