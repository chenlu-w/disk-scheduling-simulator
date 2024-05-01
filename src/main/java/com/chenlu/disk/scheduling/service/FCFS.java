package com.chenlu.disk.scheduling.service;
import com.chenlu.disk.scheduling.SchedulingResult;

import java.util.ArrayList;
import java.util.List;

public class FCFS implements DiskSchedulingAlgorithm {

    @Override
    public SchedulingResult schedule(List<Integer> requests, int initialPosition) {
        SchedulingResult result = new SchedulingResult();
        int seekCount = 0;
        int currentPosition = initialPosition;
        int distance;

        List<Integer> sequence = new ArrayList<>();

        for (int request : requests) {
            distance = Math.abs(request - currentPosition);
            seekCount += distance;
            currentPosition = request; // update the head to the current track
            sequence.add(currentPosition); // add the current track to the seek sequence
        }

        result.setSequence(sequence);
        result.setTotalSeekCount(seekCount);

        return result;
    }
}

