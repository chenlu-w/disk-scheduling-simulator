package com.chenlu.disk.scheduling;

import java.util.List;

public class SchedulingRequest {
    private List<Integer> queue;
    private int initialPosition;
    private String algorithm;

    // Constructors, getters, and setters
    public List<Integer> getQueue() {
        return queue;
    }

    public void setQueue(List<Integer> queue) {
        this.queue = queue;
    }

    public int getInitialPosition() {
        return initialPosition;
    }

    public void setInitialPosition(int initialPosition) {
        this.initialPosition = initialPosition;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }
}
