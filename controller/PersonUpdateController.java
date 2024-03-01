package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.PersonUpdateProducerService;


import java.util.UUID;

import static org.apache.kafka.common.requests.DeleteAclsResponse.log;

@RestController
@RequestMapping("/api")
public class PersonUpdateController {

    @Autowired
    private PersonUpdateProducerService producerService;

    @PostMapping("/person/updateWithRandom/{id}")
    public ResponseEntity<String> triggerRandomUpdate(@PathVariable UUID id) {
        try {
            log.info("Triggering random update for person ID: {}", id);
            producerService.triggerRandomPersonUpdate("personUpdatesTopic", id);
            return ResponseEntity.ok("Random update triggered");
        } catch (Exception e) {
            log.error("Error triggering random update", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error triggering random update");
        }
    }

}
