package com.spirent.service;

import com.spirent.model.PhoneNumber;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PhoneNumberService {

    public Set<String> readPhoneNumbersFromFolder(Path phoneNumberPath) {
        return searchPhoneFilesOnFolder(phoneNumberPath)
                .flatMap(this::readPhoneNumbersFromFile)
                .sorted()
                .map(PhoneNumber::getNormalizedPhoneNumber)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    private PhoneNumber buildPhoneNumberFromString(String phoneNumber) {
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
        return phoneNumberBuilder.build();
    }

    private Stream<Path> searchPhoneFilesOnFolder(Path phoneNumberPath) {
        if (!Files.isDirectory(phoneNumberPath)) {
            throw new IllegalArgumentException("path is not a directory");
        }
        try {
            Stream<Path> paths = Files.walk(phoneNumberPath);
            return paths.filter(file -> !Files.isDirectory(file));
        } catch (IOException e) {
            return Stream.empty();
        }
    }

    private Stream<PhoneNumber> readPhoneNumbersFromFile(Path phoneNumberFile) {
        try {
            Stream<String> lines = Files.lines(phoneNumberFile);
            return lines.map(this::buildPhoneNumberFromString);
        } catch (IOException e) {
            return Stream.empty();
        }
    }

}
