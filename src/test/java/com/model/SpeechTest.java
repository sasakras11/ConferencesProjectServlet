package com.model;


import com.model.entity.Speech;
import org.junit.Test;

public class SpeechTest {




    @Test(expected = RuntimeException.class)
    public void SettingBigHourInStartFieldReturnsExceptionIn(){
        Speech.builder().withStartHour(25);
    }

    @Test(expected = RuntimeException.class)
    public void SettingBigHourInEndFieldReturnsExceptionIn(){
        Speech.builder().withEndHour(25);
    }
    @Test(expected = RuntimeException.class)
    public void SettingNegativeHourInStartFieldReturnsExceptionIn(){
        Speech.builder().withStartHour(-1);
    }

    @Test(expected = RuntimeException.class)
    public void SettingNegativeHourInEndFieldReturnsExceptionIn(){
        Speech.builder().withEndHour(-1);
    }

}
