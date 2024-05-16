package com.task_management_system.utilities;

import java.util.Random;

public class UniqueRefUtil {
    Random rnd = new Random();
    static String generateUniqueRef(String prefix, int count) {
        String ref = rnd..randomAlphanumeric(count);
        return String.format("%s%s",prefix, ref.toUpperCase());
    }
}
