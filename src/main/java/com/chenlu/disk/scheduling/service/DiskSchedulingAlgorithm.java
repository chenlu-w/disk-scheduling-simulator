package com.chenlu.disk.scheduling.service;

import java.util.List;

public interface DiskSchedulingAlgorithm {
    List<Integer> schedule(List<Integer> requests, int initialPosition);
}
