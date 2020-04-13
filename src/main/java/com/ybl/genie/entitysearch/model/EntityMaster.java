package com.ybl.genie.entitysearch.model;

import org.springframework.stereotype.Component;

@Component
public class EntityMaster {
    private String key = null;

    private String business_name = null;

    private String category_level_1 = null;

    private String category_level_2 = null;

    private String category_level_3 = null;

    private String category_code = null;

    private String other_category_code = null;

    private String entity_metadata = null;

    private String google_industry = null;

    private String industry = null;

    private String primary_industry = null;

    private String sub_industry = null;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getBusiness_name() {
        return business_name;
    }

    public void setBusiness_name(String business_name) {
        this.business_name = business_name;
    }

    public String getCategory_level_1() {
        return category_level_1;
    }

    public void setCategory_level_1(String category_level_1) {
        this.category_level_1 = category_level_1;
    }

    public String getCategory_level_2() {
        return category_level_2;
    }

    public void setCategory_level_2(String category_level_2) {
        this.category_level_2 = category_level_2;
    }

    public String getCategory_level_3() {
        return category_level_3;
    }

    public void setCategory_level_3(String category_level_3) {
        this.category_level_3 = category_level_3;
    }

    public String getCategory_code() {
        return category_code;
    }

    public void setCategory_code(String category_code) {
        this.category_code = category_code;
    }

    public String getOther_category_code() {
        return other_category_code;
    }

    public void setOther_category_code(String other_category_code) {
        this.other_category_code = other_category_code;
    }

    public String getEntity_metadata() {
        return entity_metadata;
    }

    public void setEntity_metadata(String entity_metadata) {
        this.entity_metadata = entity_metadata;
    }

    public String getGoogle_industry() {
        return google_industry;
    }

    public void setGoogle_industry(String google_industry) {
        this.google_industry = google_industry;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getPrimary_industry() {
        return primary_industry;
    }

    public void setPrimary_industry(String primary_industry) {
        this.primary_industry = primary_industry;
    }

    public String getSub_industry() {
        return sub_industry;
    }

    public void setSub_industry(String sub_industry) {
        this.sub_industry = sub_industry;
    }
}
