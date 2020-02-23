package com.service.impl;

import com.context.AppContext;
import com.dao.impl.CrudPageableDaoSpeechImpl;
import com.entity.Speech;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

public class AbstractServiceTest {


    private SpeechServiceImpl speechService;


    @Before
     public void init(){
        speechService = new SpeechServiceImpl(AppContext.getSpeechDao(),AppContext.getUserDao(),AppContext.getConferenceDao());
    }

    @Test
     public void IfParameterIntegerIsCorrectShouldReturnOptionalOfInteger(){
        Optional<Integer> valid = speechService.getParsedOctalNumberOrOptionalEmpty("200");
        Assert.assertEquals(200, (int) valid.get());
    }
    @Test
    public void IfParameterIntegerIsNotOctalShouldReturnOptionalEmpty(){
        Optional<Integer> empty = speechService.getParsedOctalNumberOrOptionalEmpty("notOctal");

        Assert.assertEquals(Optional.empty(),empty);

    }
    @Test
    public void IfParameterIntegerHasTooBigLengthShouldReturnOptionalEmpty(){
        Optional<Integer> empty = speechService.getParsedOctalNumberOrOptionalEmpty("100000000");
          Assert.assertEquals(Optional.empty(),empty);
    }
    @Test
    public void IfNameHasLengthLessThenFourShouldReturnOptionalEmpty(){
        Optional<String> empty = speechService.getValidatedNameOrOptionalEmpty("ab");
        Assert.assertEquals(Optional.empty(),empty);

    }
    @Test
    public void IfNameIsCorrectShouldReturnOptionalOfString(){
        Optional<String> valid = speechService.getValidatedNameOrOptionalEmpty("valid name");
        Assert.assertTrue(valid.isPresent());

    }

    @Test
     public void ifDateIsNotValidShouldReturnOptionalEmpty(){
        Optional<Date> empty = speechService.getParsedDateOrOptionalEmpty("03.10.2000");
Assert.assertFalse(empty.isPresent());
    }

    @Test
     public void ifDateIsValidShouldReturnValidDate(){
        Optional<Date> date = speechService.getParsedDateOrOptionalEmpty("2000-10-03");

        Assert.assertTrue(date.isPresent());
    }

    @Test
    public void IfHourIsNotValidShouldReturnOptionalEmpty(){
        Optional<Integer> empty = speechService.getValidHourOrOptionalEmpty("26");
        Assert.assertFalse(empty.isPresent());
    }

    @Test
    public void ifEndHourIsGreaterThanStartHourShouldReturnOptionalEmpty(){
        Optional<Integer> empty = speechService.getValidEndHourOrOptionalEmpty("22","21");
        Assert.assertFalse(empty.isPresent());
    }
    @Test
    public void IfEndHourIsCorrectShouldReturnInteger(){
        Optional<Integer> endHour = speechService.getValidEndHourOrOptionalEmpty("21","22");
        Assert.assertTrue(endHour.isPresent());
    }



}