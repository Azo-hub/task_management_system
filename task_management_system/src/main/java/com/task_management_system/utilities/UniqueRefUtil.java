package com.task_management_system.utilities;

import java.util.Random;

public class UniqueRefUtil {
    public static String generateUniqueRef(String prefix, int count) {

        String ALPHANUMERICLETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890abcdefghijklmnopqrstuvwxyz";
        StringBuilder randomString = new StringBuilder ();
        Random rnd = new Random();

        while (randomString.length()< count) {

            int index = (int) (rnd.nextFloat()*ALPHANUMERICLETTERS.length());
            randomString.append(ALPHANUMERICLETTERS.charAt(index));

        }

        return randomString.append(prefix).toString();
    }

}
