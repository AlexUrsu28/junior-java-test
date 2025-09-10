package com.example.carins.service;

import com.example.carins.model.InsurancePolicy;
import com.example.carins.repo.InsurancePolicyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Service
public class PolicyExpiryScheduler {

    private static final Logger log = LoggerFactory.getLogger(PolicyExpiryScheduler.class);

    private final InsurancePolicyRepository policyRepo;
    private final ZoneId zoneId;

    public PolicyExpiryScheduler(
            InsurancePolicyRepository policyRepo,
            @Value("${app.timezone:}") String configuredZone
    ) {
        this.policyRepo = policyRepo;
        this.zoneId = (configuredZone == null || configuredZone.isBlank())
                ? ZoneId.systemDefault()
                : ZoneId.of(configuredZone);
    }

    /**
     * Run once per day at 00:05 local time (within 1 hour after midnight),
     * log each policy that has endDate == today.
     * Since this runs only once per day, it won't spam.
     */
    @Scheduled(cron = "*/10 * * * * *")
    @Transactional(readOnly = false)
    public void logExpiredPolicies() {
        LocalDate today = LocalDate.now(zoneId);
        List<InsurancePolicy> expired = policyRepo.findAllExpiredAndNotLogged(today);

        for (InsurancePolicy p : expired) {
            log.info("Policy {} for car {} expired on {}", p.getId(), p.getCar().getId(), p.getEndDate());
            p.setExpiredLogged(true);
        }
    }
}
