package com.midasin.mtsmember.utils;

public final class RegexPattern {

    public static final String EMAIL_REGEX = "^(?=.{1,64}@)[\\p{L}0-9_-]+(\\.[\\p{L}0-9_-]+)*@[^-][\\p{L}0-9-]{1,254}+(\\.[\\p{L}0-9-]+)*(\\.[\\p{L}]{2,3})$";
    public static final String PHONE_REGEX = "^\\d{3}-\\d{3,4}-\\d{4}$";

}
