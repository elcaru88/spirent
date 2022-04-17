package com.spirent;

import com.spirent.service.PhoneNumberService;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) throws IOException {


        PhoneNumberService service = new PhoneNumberService();
        service.readPhoneNumbersFromFolder(Path.of("/home/ccaruso/IdeaProjects/spirent/src/main/resources")).forEach(System.out::println);
    }
}
