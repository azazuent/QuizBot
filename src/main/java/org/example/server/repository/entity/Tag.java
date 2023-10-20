package org.example.server.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class Tag {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    Tag(){};

    public Tag(Long id, String tag){
        this.id = id;
        this.tag = tag;
    }

    private String tag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
