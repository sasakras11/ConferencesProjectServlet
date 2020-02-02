package com.dao;

import com.entity.Speech;

public interface CrudPageableSpeechDao extends CrudPageableDao<Speech> {


    int count();

}
