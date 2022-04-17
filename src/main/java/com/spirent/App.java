package com.spirent;

import com.spirent.service.PhoneNumberService;

/**
 * Hello world!
 *
 */
public class App 
{
    /*
+7 812 number, +7 (495) number, +7812number, +7815 number, 1-814-number

(812) number,  812number, 812 number, 095-number

123-4567, 123-45-67, 1234567
 */
    public static void main( String[] args )
    {
        PhoneNumberService service = new PhoneNumberService();
        System.out.println(service.normalizePhoneNumberString("+7 812 1234567"));
        System.out.println(service.normalizePhoneNumberString("+7 (495) 1234567"));
        System.out.println(service.normalizePhoneNumberString("+78121234567"));
        System.out.println(service.normalizePhoneNumberString("+7815 1234567"));
        System.out.println(service.normalizePhoneNumberString("+1-814-1234567"));
        System.out.println(service.normalizePhoneNumberString("(812) 1234567"));
        System.out.println(service.normalizePhoneNumberString("8121234567"));
        System.out.println(service.normalizePhoneNumberString("812 1234567"));
        System.out.println(service.normalizePhoneNumberString("095-1234567"));
        System.out.println(service.normalizePhoneNumberString("123-4567"));
        System.out.println(service.normalizePhoneNumberString("123-45-67"));
        System.out.println(service.normalizePhoneNumberString("1234567"));
    }
}
