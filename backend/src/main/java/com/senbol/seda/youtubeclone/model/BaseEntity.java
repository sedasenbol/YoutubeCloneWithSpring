package com.senbol.seda.youtubeclone.model;

import org.springframework.data.annotation.Id;

public class BaseEntity {
    @Id
    public String id;

    public BaseEntity() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
