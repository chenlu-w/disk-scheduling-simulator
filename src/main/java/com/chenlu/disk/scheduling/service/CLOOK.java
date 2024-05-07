package com.chenlu.disk.scheduling.service;
import com.chenlu.disk.scheduling.SchedulingResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CLOOK implements DiskSchedulingAlgorithm {

    @Override
    public SchedulingResult schedule(List<Integer> requests, int initialPosition) {
        SchedulingResult result = new SchedulingResult();
        List<Integer> sequence = new ArrayList<>();
        int currentPosition = initialPosition;
        int seekCount = 0;

        // Sort the list of requests
        Collections.sort(requests);

        // Lists to hold requests that are after the initial head position
        List<Integer> right = new ArrayList<>();
        List<Integer> left = new ArrayList<>();

        for (int request : requests) {
            if (request < initialPosition) {
                left.add(request);
            } else {
                right.add(request);
            }
        }

        // Process all requests to the right of the head
        for (int request : right) {
            seekCount += Math.abs(currentPosition - request);
            sequence.add(request);
            currentPosition = request;
        }

        // Jump directly to the first request on the left (smallest request above 0)
        if (!left.isEmpty() && !right.isEmpty()) {
            seekCount += Math.abs(currentPosition - left.get(0));
            currentPosition = left.get(0);
        }

        // Process all remaining requests starting from the lowest
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
