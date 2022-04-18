package com.spirent.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PhoneNumber implements Comparable<PhoneNumber>{
    private String country;

    private String city;

    private String number;

    public String getNormalizedPhoneNumber () {
        return "+" + country + " (" + city + ") " + number.substring(0,3) + "-" + number.substring(3);
    }

    @Override
    public int compareTo(PhoneNumber o) {
        int country = compareTwoStringNumbers(this.country, o.country);
        if (country != 0) {
            return country;
        }
        int city = compareTwoStringNumbers(this.city, o.city);
        if (city != 0) {
            return city;
        }
        return compareTwoStringNumbers(this.number, o.number);
    }

    private static int compareTwoStringNumbers(String value1, String value2) {
        return Integer.parseInt(value1) - Integer.parseInt(value2);
    }

}
