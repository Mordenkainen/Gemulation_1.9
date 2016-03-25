package com.m4thg33k.gemulation.core.util;

public class StringHelper {

    public static String splitCamelCase(String s)
    {
        return s.replaceAll(
                String.format("%s|%s|%s",
                        "(?<=[A-Z])(?=[A-Z][a-z])",
                        "(?<=[^A-Z])(?=[A-Z])",
                        "(?<=[A-Za-z])(?=[^A-Za-z])"
                ),
                " "
        );
    }

    public static String limitToNCharacters(String s,int n)
    {
        if (s.length()>n)
        {
            return s.substring(0,n);
        }
        return s;
    }
}
