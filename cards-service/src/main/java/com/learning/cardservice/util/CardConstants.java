package com.learning.cardservice.util;

public enum CardConstants {
    CREDIT_CARD("Credit Card"),
    NEW_CARD_LIMIT("100000"),
    STATUS_201("201"),
    MESSAGE_201("Card Created Successfully"),
    STATUS_200("200"),
    MESSAGE_200("Request Processed Successfully"),
    STATUS_417("417"),
    MESSAGE_417_UPDATE("Update operation failed. Please try again or contact Dev team"),
    MESSAGE_417_DELETE( "Delete operation failed. Please try again or contact Dev team");


    public final String message;


    CardConstants(String message) {
        this.message = message;
    }
}
