package com.chenlu.disk.scheduling.service;

import org.springframework.stereotype.Service;

@Service
public class DiskSchedulingService {

    private final FCFS fcfs = new FCFS();

    // Other algorithms would be initialized here...

    public List<Integer> schedule(String algorithm, List<Integer> requests, int initialPosition) {
        switch (algorithm) {
            case "FCFS":
                return fcfs.schedule(requests, initialPosition);
            // Other cases for other algorithms...
            default:
                return fcfs.schedule(requests, initialPosition);
        }
    }
}
