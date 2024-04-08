package com.learning.accountsservice.util;

public enum AccountConstants {
    SAVINGS("Savings"),
    ADDRESS("123 Main Street, New York"),
    STATUS_201("201"),
    MESSAGE_201("Account Created Successfully"),
    STATUS_200("200"),
    MESSAGE_200("Request Processed Successfully"),
    STATUS_417("417"),
    MESSAGE_417_UPDATE("Update operation failed. Please try again or contact Dev team"),
    MESSAGE_417_DELETE( "Delete operation failed. Please try again or contact Dev team"),
    STATUS_500("500"),
    MESSAGE_500("An Error occurred. Please contact Dev Team");


    public final String message;


    AccountConstants(String message) {
        this.message = message;
    }
}
