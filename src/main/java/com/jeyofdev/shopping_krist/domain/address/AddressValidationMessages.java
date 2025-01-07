package com.jeyofdev.shopping_krist.domain.address;

public class AddressValidationMessages {
    public static final String REQUIRED_NAME = "The name field is required.";
    public static final String VALID_NAME = "The name field must contain between {min} and {max} characters.";
    public static final String REQUIRED_PHONE = "The phone number field is required.";
    public static final String VALID_PHONE = "Please provide a valid phone number.";
    public static final String REQUIRED_STREET_NUMBER = "The street number is required.";
    public static final String VALID_STREET_NUMBER = "The street number must be a maximum of 4 digits.";
    public static final String REQUIRED_STREET = "The street field is required.";
    public static final String VALID_STREET_SIZE = "The street field must contain between {min} and {max} characters.";
    public static final String REQUIRED_ZIP_CODE = "The zip code field is required.";
    public static final String VALID_ZIP_CODE = "The zip code must be exactly 5 digits.";
}
