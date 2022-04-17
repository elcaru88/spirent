package com.spirent.service;

import com.spirent.model.PhoneNumber;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PhoneNumberService {


    public String normalizePhoneNumberString(String phoneNumber) {
        PhoneNumber.PhoneNumberBuilder phoneNumberBuilder = PhoneNumber.builder().country("7");
        StringBuilder lastSevenNumbers = new StringBuilder();
        StringBuilder threeCityNumbers = new StringBuilder();
        for (int i = phoneNumber.length() - 1; i >= 0; i--) {
            char singleChar = phoneNumber.charAt(i);
            if (Character.isDigit(singleChar)) {
                if (lastSevenNumbers.length() < 7) {
                    lastSevenNumbers.append(singleChar);
                } else if ((threeCityNumbers.length() > 0 || Character.isDigit(phoneNumber.charAt(i - 1))) && threeCityNumbers.length() < 3) {
                    threeCityNumbers.append(singleChar);
                } else {
                    phoneNumberBuilder.country(String.valueOf(singleChar));
                }
            }
        }
        phoneNumberBuilder.number(lastSevenNumbers.reverse().toString());
        if (threeCityNumbers.length() > 0) {
            phoneNumberBuilder.city(threeCityNumbers.reverse().toString());
        } else {
            phoneNumberBuilder.city("812");
        }
        return phoneNumberBuilder.build().getNormalizedPhoneNumber();
    }

    public Stream<Path> searchPhoneFilesOnFolder(Path phoneNumberPath) throws IOException {
        if (!Files.isDirectory(phoneNumberPath)) {
            throw new IllegalArgumentException("path is not a directory");
        }
        return Files.walk(phoneNumberPath).filter(file -> !Files.isDirectory(file));
    }

    public List<String> readPhoneNumbersFromFolder(Path phoneNumberPath) throws IOException {
        List<String> result = new ArrayList<>();
        searchPhoneFilesOnFolder(phoneNumberPath).map(phoneNumberFile -> {
            try {
                return readPhoneNumbersFromFile(phoneNumberFile);
            } catch (IOException e) {
                return Stream.of("");
            }
        }).forEach(phoneNumbers -> result.addAll(phoneNumbers.collect(Collectors.toList())));
        return result;
    }

    public Stream<String> readPhoneNumbersFromFile(Path phoneNumberFile) throws IOException {
        return Files.lines(phoneNumberFile).map(this::normalizePhoneNumberString);
    }

}
