package com.hhly.jpa.springdatajpa;

import org.jasypt.util.text.BasicTextEncryptor;

public class MainTest {

    public static void main(String[] args) {
        BasicTextEncryptor encryptor = new BasicTextEncryptor();
        encryptor.setPassword("root");
        String encrypted = encryptor.encrypt("xxxx");
        System.out.println(encrypted);
    }

}
