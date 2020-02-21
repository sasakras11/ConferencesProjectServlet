package com;


import com.context.AppContext;
import com.dao.ConferenceGroup;
import com.dao.impl.CrudPageableDaoSpeechImpl;

public class Starter {
    public static void main(String[] args) {

        System.out.println(AppContext.getConferenceDao().count(ConferenceGroup.COMING));

    }

}



