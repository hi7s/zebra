package com.hi7s.tech.zebra.util;

public final class NamingUtils {

    private static final char SEPARATOR = '_';

    /**
     * "helloWorld" -> "hello_world"
     *
     * @param name camel case name
     * @return under line name
     */
    public static String toUnderScoreCase(String name) {
        if (name == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        boolean upperCase = false;
        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);

            boolean nextUpperCase = true;

            if (i < (name.length() - 1)) {
                nextUpperCase = Character.isUpperCase(name.charAt(i + 1));
            }

            if (Character.isUpperCase(c)) {
                if (!upperCase || !nextUpperCase) {
                    if (i > 0)
                        sb.append(SEPARATOR);
                }
                upperCase = true;
            } else {
                upperCase = false;
            }

            sb.append(Character.toLowerCase(c));
        }

        return sb.toString();
    }

    /**
     * "hello_world" -> "helloWorld"
     *
     * @param name
     * @return
     */
    public static String toCamelCase(String name) {
        if (name == null) {
            return null;
        }

        name = name.toLowerCase();

        StringBuilder sb = new StringBuilder(name.length());
        boolean upperCase = false;
        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);

            if (c == SEPARATOR) {
                upperCase = true;
            } else if (upperCase) {
                sb.append(Character.toUpperCase(c));
                upperCase = false;
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    /**
     * "hello_world" -> "HelloWorld"
     *
     * @param name under line name
     * @return title name
     */
    public static String toTitleCase(String name) {
        if (name == null) {
            return null;
        }
        name = toCamelCase(name);
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }
}