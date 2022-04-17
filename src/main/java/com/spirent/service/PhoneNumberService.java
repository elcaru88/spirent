package com.spirent.service;

import com.spirent.model.PhoneNumber;

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
                } else if ((threeCityNumbers.length() > 0 || Character.isDigit(phoneNumber.charAt(i - 1))) && threeCityNumbers.length() < 3 ){
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

}
