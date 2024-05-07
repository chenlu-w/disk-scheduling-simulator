package com.chenlu.disk.scheduling.service;

import com.chenlu.disk.scheduling.SchedulingResult;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DiskSchedulingService {

    private final FCFS fcfs = new FCFS();
    private final SSTF sstf = new SSTF();
    private final SCAN scan = new SCAN();
    private final CSCAN cscan = new CSCAN();
    private final LOOK look = new LOOK();
    private final CLOOK clook = new CLOOK();

    public SchedulingResult schedule(List<Integer> requests, int initialPosition, String algorithm) {
        switch (algorithm) {
            case "FCFS":
                return fcfs.schedule(requests, initialPosition);
            case "SSTF":
                return sstf.schedule(requests, initialPosition);
            case "SCAN":
                return scan.schedule(requests, initialPosition);
            case "CSCAN":
                return cscan.schedule(requests, initialPosition);
            case "LOOK":
                return look.schedule(requests, initialPosition);
            case "CLOOK":
                return clook.schedule(requests, initialPosition);
            default:
                throw new IllegalArgumentException("Unsupported disk scheduling algorithm: " + algorithm);
        }
    }
}

