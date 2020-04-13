package com.ybl.genie.entitysearch.repo.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ybl.genie.entitysearch.model.EntMapper;
import com.ybl.genie.entitysearch.model.EntityMaster;
import com.ybl.genie.entitysearch.repo.EntitySearchRepo;
import com.ybl.genie.entitysearch.exception.EntitySearchException;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class EntitySearchRepoImpl implements EntitySearchRepo {
    private static final Logger LOGGER = LoggerFactory.getLogger(EntitySearchRepoImpl.class);
    private RestHighLevelClient restHighLevelClient;
    @Value("${gnyEntityMapper}")
    private String gnyEntityMapper;
    @Value("${gnyEntityMaster}")
    private String gnyEntityMaster;
    private ObjectMapper objectMapper;

    @Autowired
    public EntitySearchRepoImpl(RestHighLevelClient client, ObjectMapper objectMapper) {
        this.restHighLevelClient = client;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<EntMapper> getEntityMapper(EntMapper em) {
        LOGGER.info("Search request on {} getEntityMapper{}", em);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder query = QueryBuilders.boolQuery();
        if (!Objects.isNull(em.getKey()))
            query.filter(QueryBuilders.termsQuery("key", em.getKey()));
        if (!Objects.isNull(em.getName()))
            query.filter(QueryBuilders.termsQuery("name", em.getName()));
        if (!Objects.isNull(em.getEntity_id()))
            query.filter(QueryBuilders.termsQuery("entity_id", em.getEntity_id()));
        if (!Objects.isNull(em.getBusiness_name()))
            query.filter(QueryBuilders.termsQuery("business_name", em.getBusiness_name()));

        sourceBuilder.query(query);
        SearchRequest searchRequest = new SearchRequest(gnyEntityMapper);
        searchRequest.source(sourceBuilder);
        try {
            SearchResponse resp = restHighLevelClient.search(searchRequest);
            LOGGER.info("SearchResponse received from elastic cluster");
            return serializeEntMapper(resp);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new EntitySearchException(e.getMessage());
        }

    }

    @Override
    public List<EntityMaster> getEntityMaster(EntityMaster es) {
        LOGGER.info("Search request on {} getEntityMapper{}", es);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder query = QueryBuilders.boolQuery();
        if (!Objects.isNull(es.getKey()))
            query.filter(QueryBuilders.termsQuery("key", es.getKey()));
        sourceBuilder.query(query);
        SearchRequest searchRequest = new SearchRequest(gnyEntityMaster);
        searchRequest.source(sourceBuilder);
        try {
            SearchResponse resp = restHighLevelClient.search(searchRequest);
            LOGGER.info("SearchResponse received from elastic cluster");
            return serializeEntityMaster(resp);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new EntitySearchException(e.getMessage());
        }
    }


    private List<EntMapper> serializeEntMapper(SearchResponse searchResponse) {
        LOGGER.info("Transform the response started");
        if (searchResponse != null && searchResponse.getHits().getHits().length != 0) {
            SearchHit[] hits = searchResponse.getHits().getHits();
            List<EntMapper> entity = new ArrayList<>();
            LinkedList<Map<String, Object>> rawSearchedObject = Arrays.stream(hits).map(SearchHit::getSourceAsMap).collect(Collectors.toCollection(LinkedList::new));
            rawSearchedObject.stream().map(obj -> objectMapper.convertValue(obj, EntMapper.class)).forEach(entity::add);
            LOGGER.info("Transform the response completed");
            return entity;
        }
        return null;

    }

    private List<EntityMaster> serializeEntityMaster(SearchResponse searchResponse) {
        LOGGER.info("Transform the response started");
        if (searchResponse != null && searchResponse.getHits().getHits().length != 0) {
            SearchHit[] hits = searchResponse.getHits().getHits();
            List<EntityMaster> entity = new ArrayList<>();
            LinkedList<Map<String, Object>> rawSearchedObject = Arrays.stream(hits).map(SearchHit::getSourceAsMap).collect(Collectors.toCollection(LinkedList::new));
            rawSearchedObject.stream().map(obj -> objectMapper.convertValue(obj, EntityMaster.class)).forEach(entity::add);
            LOGGER.info("Transform the response completed");
            return entity;
        }
        return null;

    }
}

