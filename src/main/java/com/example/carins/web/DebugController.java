package com.example.carins.web;

import com.example.carins.service.PolicyExpiryScheduler;
import org.apache.commons.logging.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/debug")
public class DebugController {

        private final PolicyExpiryScheduler scheduler;

        public DebugController(PolicyExpiryScheduler scheduler) {
            this.scheduler = scheduler;
        }

        @GetMapping("/run-expiry-check")
        public ResponseEntity<Void> runCheck() {
            scheduler.logExpiredPolicies();
            return ResponseEntity.ok().build();
        }
    }

