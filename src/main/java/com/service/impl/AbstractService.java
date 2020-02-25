package com.service.impl;

import com.dao.CrudDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

public abstract class AbstractService <T,R extends CrudDao<T>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractService.class);

    public Optional<T> findByIdIfPresentOrGetOptionalEmpty(String id, R repository) {

        Optional<Integer> optional = getParsedOctalNumberOrOptionalEmpty(id);
        if (optional.isPresent()) {
            return repository.findById(optional.get());

        } else{
            return Optional.empty();
        }
    }

    public Optional<String> getValidatedNameOrOptionalEmpty(String value) {
        if (value==null||value.length() < 4) {
            LOGGER.warn(String.format("name [%s] has too small length,should be more then 3", value));
            return Optional.empty();

        } else {
            return Optional.of(value);
        }
    }

    public Optional<Integer> getParsedOctalNumberOrOptionalEmpty(String value) {
        try {
            int num = Integer.parseInt(value);

            if (num > 1_000_000) {
                LOGGER.warn(String.format("number [%s]  has too big value,should be less then 1_000_000", value));
                return Optional.empty();
            }
            return Optional.of(num);

        } catch (Exception e) {
            LOGGER.warn(String.format("string [%s] is not octal number", value));
            return Optional.empty();
        }
    }

    public Optional<Date> getParsedDateOrOptionalEmpty(String value) {
        if (value==null ||value.length() > 10) {
        return Optional.empty();
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {

            return Optional.of(dateFormat.parse(value));
        } catch (ParseException e) {
            LOGGER.warn(String.format("date [%s]  has wrong format,Should be yyyy-MM-dd", value));
            return Optional.empty();
        }
    }

    public Optional<Integer> getValidHourOrOptionalEmpty(String value) {
        Optional<Integer> optional = getParsedOctalNumberOrOptionalEmpty(value);
        if (optional.isPresent()) {
            if( optional.get() >= 0 &&optional.get() < 24){
                return optional;

            }
            LOGGER.warn(String.format("hour [%s] is not valid", value));
            return Optional.empty();
        }
        LOGGER.warn(String.format("hour [%s] is not valid", value));
        return Optional.empty();
    }

    public Optional<Integer> getValidEndHourOrOptionalEmpty(String startHour, String endHour) {
        Optional<Integer> optionalStartH = getParsedOctalNumberOrOptionalEmpty(startHour);
        Optional<Integer> optionalEndH = getParsedOctalNumberOrOptionalEmpty(endHour);

        if(optionalEndH.isPresent()&&optionalStartH.isPresent()&&optionalEndH.get()>optionalStartH.get()){
            return optionalEndH;

        } else {
            LOGGER.warn(String.format("endHour [%s] is not valid, because is less then then startHour - [%s]", endHour, startHour));
           return Optional.empty();
        }
    }
}
