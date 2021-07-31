package com.palo.oms.util;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NRICUtil {

    private static final char[] FIRST_LETTER_ARRAY = {'S', 'T', 'F', 'G'};

    public static String createRandomNRIC(){
        Random rand = new Random();
        int num = rand.nextInt(4);
        StringBuilder sb = new StringBuilder(Character.toString(FIRST_LETTER_ARRAY[num]));

        num = rand.nextInt(9000000) + 1000000;
        sb.append(num);

        char c = (char) (rand.nextInt(26) + 'A');
        sb.append(c);
        return sb.toString();
    }

    public static boolean isValidNRIC(String nric)
    {
        String regex = "["+ String.valueOf(FIRST_LETTER_ARRAY)+"][0-9]{7}[A-Z]";
        Pattern p = Pattern.compile(regex);
        if (nric == null) {
            return false;
        }
        Matcher m = p.matcher(nric);
        return m.matches();
    }
}
