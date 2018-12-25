package com.hi7s.tech.zebra.util;

import org.apache.commons.lang3.StringUtils;

public class PathUtils {

    private static final String USER_HOME = System.getProperty("user.home");

    private static final String FILE_SEPARATOR = System.getProperty("file.separator");

    public static String toHome(String... file) {
        return convert(true, file);
    }

    public static String toRoot(String... file) {
        return convert(false, file);
    }

    private static String convert(Boolean useHome, String... file) {
        return (useHome ? USER_HOME : "") + FILE_SEPARATOR + StringUtils.join(file, FILE_SEPARATOR);
    }

    public static String merge(String baseDirectory, String... file) {
        return baseDirectory + FILE_SEPARATOR + StringUtils.join(file, FILE_SEPARATOR);
    }

    public static void main(String[] args) {
        System.out.println(toRoot("usr", "local", "mysql", "5.6", "bin", "mysqld_safe"));
        System.out.println(toHome("tools", "mysql", "5.6", "mysql-5.5.56-linux-glibc2.5-x86_64.tar.gz"));
    }
}