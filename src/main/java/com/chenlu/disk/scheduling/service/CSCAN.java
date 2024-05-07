package com.chenlu.disk.scheduling.service;
import com.chenlu.disk.scheduling.SchedulingResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CSCAN implements DiskSchedulingAlgorithm {

    @Override
    public SchedulingResult schedule(List<Integer> requests, int initialPosition) {
        SchedulingResult result = new SchedulingResult();
        List<Integer> sequence = new ArrayList<>();
        int currentPosition = initialPosition;
        int seekCount = 0;

        // Assuming a disk size, you may need to adjust this value.
        final int diskSize = 200;

        // Sort the list of requests
        Collections.sort(requests);

        // Lists to hold requests that are before and after the initial head position
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

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

        // Jump to the beginning of the disk, and continue to the first request
        // The seek to the start adds the max distance minus the last serviced request
        if (!right.isEmpty()) {
            seekCount += (diskSize - currentPosition) + diskSize; // From the last position on the right to the start of the disk
        }
        currentPosition = 0;

        // Process all requests to the left of the initial head position (beginning of the disk)
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
