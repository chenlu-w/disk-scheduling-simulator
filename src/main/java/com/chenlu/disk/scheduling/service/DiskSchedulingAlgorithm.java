package com.chenlu.disk.scheduling.service;

import com.chenlu.disk.scheduling.SchedulingResult;

import java.util.List;

public interface DiskSchedulingAlgorithm {
    SchedulingResult schedule(List<Integer> requests, int initialPosition);
}
