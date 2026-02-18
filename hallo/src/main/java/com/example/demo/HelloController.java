package com.example.demo;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class HelloController {
    Map<String, String> entries = new HashMap<>();


    @GetMapping("/")
    public String index(@RequestParam String key) {
        return entries.get(key);
    }

    @PostMapping("/")
    public String create(@RequestBody String name) {
        String key = UUID.randomUUID().toString();
        entries.put(key, name);
        return key;
    }

    //@PutMapping("/")
    @RequestMapping(value = {"/{ID}"}, method = RequestMethod.PUT)
    public ResponseEntity<Object> update(@RequestBody String name, @PathVariable(value = "ID") String key) {
        if (entries.containsKey(key)) {
            entries.put(key, name);
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @RequestMapping(value = {"/{ID}"}, method = RequestMethod.DELETE)
    public ResponseEntity<Object> delete(@PathVariable(value = "ID") String key) {
        if (entries.containsKey(key)) {
            entries.remove(key);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
