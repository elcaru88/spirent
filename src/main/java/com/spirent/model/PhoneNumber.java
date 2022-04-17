package com.spirent.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PhoneNumber {
    private String country;

    private String city;

    private String number;

    public String getNormalizedPhoneNumber () {
        return "+" + country + " (" + city + ") " + number.substring(0,3) + "-" + number.substring(3);
    }

}
