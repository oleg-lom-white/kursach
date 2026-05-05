package com.example.demo;

import java.time.Instant;

public class PartEntity {
    private String id;
    private String material;
    private String type;
    private String size;
    private Instant date;



    public PartEntity() {
        date = Instant.now();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Instant getDate() {
        return date;
    }
}
