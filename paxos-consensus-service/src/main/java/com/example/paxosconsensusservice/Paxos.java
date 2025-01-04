package com.example.paxosconsensusservice;

import java.util.HashMap;
import java.util.Map;

public class Paxos {
    private final Map<Integer, Integer> acceptedValues = new HashMap<>();
    private final Map<Integer, Integer> proposedValues = new HashMap<>();

    public synchronized boolean propose(int proposalId, int value) {
        if (proposedValues.containsKey(proposalId)) {
            return false;
        }
        proposedValues.put(proposalId, value);
        return true;
    }

    public synchronized boolean accept(int proposalId, int value) {
        if (proposedValues.get(proposalId) == value) {
            acceptedValues.put(proposalId, value);
            return true;
        }
        return false;
    }

    public synchronized Integer getAcceptedValue(int proposalId) {
        return acceptedValues.get(proposalId);
    }
}
