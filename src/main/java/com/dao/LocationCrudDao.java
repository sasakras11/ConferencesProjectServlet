package com.dao;

import com.entity.Location;

public interface LocationCrudDao extends CrudDao<Location> {

    Location findByConferenceId(int conferenceId);
}
