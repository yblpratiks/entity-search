package com.ybl.genie.entitysearch.service.impl;

import com.ybl.genie.entitysearch.model.EntMapper;
import com.ybl.genie.entitysearch.model.EntityMaster;
import com.ybl.genie.entitysearch.repo.EntitySearchRepo;
import com.ybl.genie.entitysearch.service.EntitySearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EntitySearchServiceImpl implements EntitySearchService {

    @Autowired
    EntitySearchRepo entitySearchRepo;

    @Override
    public List<EntMapper> getEntityMapper(EntMapper em) {

        return entitySearchRepo.getEntityMapper(em);

    }

    @Override
    public List<EntityMaster> getEntityMaster(EntityMaster es) {
        return entitySearchRepo.getEntityMaster(es);
    }
}
