package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/parts")
public class PartController {

    @Autowired
    private PartService partService;

    @GetMapping("/all")
    public List<PartEntity> getParts() {
        return partService.getParts();
    }

    @RequestMapping(value = {"/{ID}"}, method = RequestMethod.GET)
    public PartEntity getPartById(@PathVariable (value = "ID") String id) {
        return partService.getPart(id);
    }

    @PostMapping("/")
    public PartEntity create(@RequestBody PartEntity part) {
        return partService.createPart(part);
    }

    @RequestMapping(value = {"/{ID}"}, method = RequestMethod.PUT)
    public PartEntity update(@RequestBody PartEntity part, @PathVariable(value = "ID") String id) {
        part.setId(id);
        return partService.updatePart(part);
    }

    @RequestMapping(value = {"/{ID}"}, method = RequestMethod.DELETE)
    public boolean delete(@PathVariable(value = "ID") String id) {
        return partService.deletePart(id);
        }
    }

