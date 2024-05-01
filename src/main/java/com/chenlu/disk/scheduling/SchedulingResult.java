package com.chenlu.disk.scheduling;

import java.util.List;

public class SchedulingResult {
    private int totalSeekCount;
    private List<Integer> sequence;

    // Constructors, getters, and setters
    public int getTotalSeekCount() {
        return totalSeekCount;
    }

    public void setTotalSeekCount(int totalSeekCount) {
        this.totalSeekCount = totalSeekCount;
    }


    public List<Integer> getSequence() {
        return sequence;
    }

    public void setSequence(List<Integer> sequence) {
        this.sequence = sequence;
    }
}
