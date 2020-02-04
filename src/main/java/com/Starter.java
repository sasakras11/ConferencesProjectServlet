package com;


import com.service.util.PasswordUtil;

public class Starter {

    public static void main(String[] args) {

        System.out.println(new PasswordUtil().getHashedPassword("ira"));
    }

}



