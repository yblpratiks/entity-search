package com.ybl.genie.entitysearch.service;


import com.ybl.genie.entitysearch.model.EntMapper;
import com.ybl.genie.entitysearch.model.EntityMaster;

import java.util.List;

public interface EntitySearchService {

    public List<EntMapper> getEntityMapper(EntMapper em);

    public List<EntityMaster> getEntityMaster(EntityMaster es);

}
