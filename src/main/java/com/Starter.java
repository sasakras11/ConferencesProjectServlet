package com;


import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

import java.util.Properties;

public class Starter {

    public static void main(String[] args) throws Exception {

        Properties prop = new Properties();
        prop.load(new FileReader("src/main/resources/query.properties"));

    }

}



