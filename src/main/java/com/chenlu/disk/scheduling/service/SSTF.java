package com.chenlu.disk.scheduling.service;
import com.chenlu.disk.scheduling.SchedulingResult;

import java.util.ArrayList;
import java.util.List;

class Node {
    int distance = 0;
    boolean accessed = false;
}

public class SSTF implements DiskSchedulingAlgorithm {

    public static void calculateDifference(List<Integer> requests, int head, Node[] diff) {
        for (int i = 0; i < diff.length; i++) {
            diff[i].distance = Math.abs(requests.get(i) - head);
        }
    }

    public static int findMin(Node[] diff) {
        int index = -1, minimum = Integer.MAX_VALUE;
        for (int i = 0; i < diff.length; i++) {
            if (!diff[i].accessed && minimum > diff[i].distance) {
                minimum = diff[i].distance;
                index = i;
            }
        }
        return index;
    }

    @Override
    public SchedulingResult schedule(List<Integer> requests, int initialPosition) {
        if (requests.isEmpty()) {
            return new SchedulingResult();
        }

        Node[] diff = new Node[requests.size()];
        for (int i = 0; i < requests.size(); i++) {
            diff[i] = new Node();
        }

        int seekCount = 0;
        List<Integer> sequence = new ArrayList<>();
        sequence.add(initialPosition); // Start the sequence with the initial position

        int currentPosition = initialPosition;
        for (int i = 0; i < requests.size(); i++) {
            calculateDifference(requests, currentPosition, diff);
            int index = findMin(diff);
            diff[index].accessed = true;
            seekCount += diff[index].distance;
            currentPosition = requests.get(index);
            sequence.add(currentPosition); // Append the current head position to the sequence
        }

        SchedulingResult result = new SchedulingResult();
        result.setTotalSeekCount(seekCount);
        result.setSequence(sequence); // Make sure to set the sequence list, not an array
        return result;

    }
}
