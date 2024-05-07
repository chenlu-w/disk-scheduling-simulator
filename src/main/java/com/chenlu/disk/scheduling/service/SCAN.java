package com.chenlu.disk.scheduling.service;
import com.chenlu.disk.scheduling.SchedulingResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SCAN implements DiskSchedulingAlgorithm {

    @Override
    public SchedulingResult schedule(List<Integer> requests, int initialPosition) {
        SchedulingResult result = new SchedulingResult();
        List<Integer> sequence = new ArrayList<>();
        int currentPosition = initialPosition;
        int seekCount = 0;

        // Sort the list of requests
        Collections.sort(requests);

        // Split requests into those less than the initial position and those greater
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        for (int request : requests) {
            if (request < initialPosition) {
                left.add(request);
            } else {
                right.add(request);
            }
        }

        // Move towards the end of disk to the right first
        for (int request : right) {
            seekCount += Math.abs(currentPosition - request);
            sequence.add(request);
            currentPosition = request;
        }

        // Then move to the left
        Collections.reverse(left);  // Reverse left requests for servicing after right
        for (int request : left) {
            seekCount += Math.abs(currentPosition - request);
            sequence.add(request);
            currentPosition = request;
        }

        // Set results
        result.setTotalSeekCount(seekCount);
        result.setSequence(sequence);

        return result;
    }
}
