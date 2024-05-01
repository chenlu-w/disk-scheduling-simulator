package com.chenlu.disk.scheduling;

import com.chenlu.disk.scheduling.service.DiskSchedulingService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@RestController
public class DiskSchedulingController {

    private final DiskSchedulingService schedulingService;

    @Autowired
    public DiskSchedulingController(DiskSchedulingService schedulingService) {
        this.schedulingService = schedulingService;
    }

    @PostMapping("/schedule")
    public ResponseEntity<SchedulingResult> scheduleDisk(@RequestBody SchedulingRequest request) {
        try {
            SchedulingResult result = schedulingService.schedule(
                    request.getQueue(),
                    request.getInitialPosition(),
                    request.getAlgorithm());
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new SchedulingResult()); // Consider how you handle errors
        }
    }
}
