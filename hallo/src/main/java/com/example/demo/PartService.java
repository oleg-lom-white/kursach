package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PartService {

    private Map<String, PartEntity> parts = new HashMap<>();

    public List<PartEntity> getParts() {
        return new ArrayList<>(parts.values());
    }

    public PartEntity getPart(String id) {
        return parts.get(id);
    }

    public PartEntity createPart(PartEntity part) {
        part.setId(UUID.randomUUID().toString());
        parts.put(part.getId(), part);
        return part;
    }

    public PartEntity updatePart(PartEntity part) {
        if (part.getId() == null) {
            return createPart(part);
        }
        parts.put(part.getId(), part);
        return part;
    }

    public boolean deletePart(String id){
        return parts.remove(id) != null;
    }

}
