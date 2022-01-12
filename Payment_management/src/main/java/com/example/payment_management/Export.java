package com.example.payment_management;

import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

@Service
@Log
public class Export {

    public Path createPath(String name){
        return Path.of("payment_record/"+name+".txt");
    }

    public void record(AuthorEvent authorEvent,String name){
        log.info("record->" + authorEvent);
        try {
            Files.writeString(
                    createPath(name),
                    authorEvent + System.lineSeparator(),
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND
            );
        }
        catch (IOException ioe) {
            throw new RuntimeException("can't record: " + ioe.getMessage());
        }
    }
}
