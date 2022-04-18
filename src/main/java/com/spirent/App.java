package com.spirent;

import com.spirent.service.PhoneNumberService;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.out.println("No files entered");
            return;
        }
        PhoneNumberService service = new PhoneNumberService();
        for (String fileName : args) {
            service.readPhoneNumbersFromFolder(
                    Path.of(fileName)).forEach(System.out::println);
        }
    }
}
