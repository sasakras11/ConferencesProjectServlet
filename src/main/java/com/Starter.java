package com;


import com.context.AppContext;
import com.entity.Role;
import com.entity.User;

public class Starter {

    public static void main(String[] args) {

        System.out.println(AppContext.getUserDao().getSpeakerOfSpeech(3));
    }

}



