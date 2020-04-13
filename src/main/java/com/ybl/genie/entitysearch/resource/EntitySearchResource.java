package com.ybl.genie.entitysearch.resource;

import com.ybl.genie.entitysearch.model.EntMapper;
import com.ybl.genie.entitysearch.model.EntityMaster;
import com.ybl.genie.entitysearch.service.EntitySearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/entity/search")
public class EntitySearchResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(EntitySearchResource.class);
    @Autowired
    EntitySearchService entitySearchService;
    @Autowired
    EntMapper em;
    @Autowired
    EntityMaster es;

    @GetMapping("/mapper")
    public List<EntMapper> getMapper(@RequestParam(value = "key", required = false) String key, @RequestParam(value = "name", required = false) String name,
                                     @RequestParam(value = "entity_id", required = false) String entity_id, @RequestParam(value = "business_name", required = false) String business_name) {
        long startTime = System.currentTimeMillis();
        LOGGER.info("Search initiated by  user {},  user role {} for query {} with source  {}  with queries", key, name, entity_id, business_name);
        // LOGGER.info("Search initiated by user {} for query {} with source  {} and reference id {}", SecurityContext.getUserId(), query, source, referenceId);

        List<EntMapper> resp = new ArrayList<EntMapper>();
        em.setKey(key);
        em.setName(name);
        em.setEntity_id(entity_id);
        em.setBusiness_name(business_name);
        LOGGER.info("Response returned in time(ms) : {}", (System.currentTimeMillis() - startTime));
        LOGGER.debug("Search response received on {} by user {} and response:{}", resp);
        resp = entitySearchService.getEntityMapper(em);

        LOGGER.info("Response returned in time(ms) : {}", (System.currentTimeMillis() - startTime));
        return resp;

    }

    @GetMapping("/master")
    public List<EntityMaster> getMapper(@RequestParam(value = "key", required = true) String key) {
        long startTime = System.currentTimeMillis();
        LOGGER.info("Search initiated by  user {},  user role {} for query {} with source  {}  with queries", key);
        // LOGGER.info("Search initiated by user {} for query {} with source  {} and reference id {}", SecurityContext.getUserId(), query, source, referenceId);
        List<EntityMaster> resp = new ArrayList<EntityMaster>();
        es.setKey(key);
        resp = entitySearchService.getEntityMaster(es);
        LOGGER.info("Response returned in time(ms) : {}", (System.currentTimeMillis() - startTime));
        LOGGER.debug("Search response received on {} by user {} and response:{}", resp);
        return resp;
    }
}
