package com.chenlu.disk.scheduling.service;

import java.util.ArrayList;
import java.util.List;

public class FCFS implements DiskSchedulingAlgorithm {

    @Override
    public List<Integer> schedule(List<Integer> requests, int initialPosition) {
        // Implement the FCFS scheduling logic here.
        requests = new ArrayList<>();
        return requests; // Assuming requests are already in the order they arrive.
    }
}
