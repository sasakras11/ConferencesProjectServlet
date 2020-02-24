package com;


import com.context.AppContext;
import com.dao.ConferenceGroup;
import com.dao.impl.CrudPageableDaoSpeechImpl;
import com.service.util.PasswordUtil;

public class Starter {
    public static void main(String[] args) {

        System.out.println(new PasswordUtil().getHashedPassword("ira34567"));

    }

}



