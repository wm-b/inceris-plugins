package com.inceris.cascade.util;

import org.bukkit.ChatColor;

import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {

    public static String colours(String message) {
        Pattern pattern = Pattern.compile("#[a-fA-F0-9]{6}");
        Matcher matcher = pattern.matcher(message);
        while (matcher.find()) {
            String hexCode = message.substring(matcher.start(), matcher.end());
            String replaceSharp = hexCode.replace('#', 'x');

            char[] ch = replaceSharp.toCharArray();
            StringBuilder builder = new StringBuilder();
            for (char c : ch) {
                builder.append("&").append(c);
            }

            message = message.replace(hexCode, builder.toString());
            matcher = pattern.matcher(message);
        }
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static boolean regex(String message, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(message);
        return matcher.find();
    }

    public static String convertToTitleCase(String inputString) {
        if (inputString == null || inputString.isEmpty()) {
            return inputString;
        }

        String[] words = inputString.toLowerCase().split("\\s");
        StringBuilder titleCase = new StringBuilder(inputString.length());

        for (String word : words) {
            if (!word.isEmpty()) {
                titleCase.append(Character.toUpperCase(word.charAt(0)));
                titleCase.append(word.substring(1));
            }
            titleCase.append(" ");
        }

        return titleCase.toString().trim();
    }

    public static int randomNumberBetween(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

}
