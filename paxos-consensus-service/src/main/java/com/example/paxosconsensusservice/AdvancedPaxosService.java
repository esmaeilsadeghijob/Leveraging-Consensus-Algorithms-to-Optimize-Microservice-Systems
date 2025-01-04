package com.example.paxosconsensusservice;

@Service
public class AdvancedPaxosService {

    private final Map<Integer, Integer> acceptedValues = new HashMap<>();
    private final Map<Integer, Integer> proposedValues = new HashMap<>();
    private final Map<Integer, Integer> promisedValues = new HashMap<>();

    public synchronized boolean propose(int proposalId, int value) {
        if (proposedValues.containsKey(proposalId)) {
            return false;
        }
        proposedValues.put(proposalId, value);
        return true;
    }

    public synchronized boolean promise(int proposalId, int value) {
        if (!promisedValues.containsKey(proposalId) || promisedValues.get(proposalId) < value) {
            promisedValues.put(proposalId, value);
            return true;
        }
        return false;
    }

    public synchronized boolean accept(int proposalId, int value) {
        if (proposedValues.get(proposalId) == value && promisedValues.get(proposalId) == value) {
            acceptedValues.put(proposalId, value);
            return true;
        }
        return false;
    }

    public synchronized Integer getAcceptedValue(int proposalId) {
        return acceptedValues.get(proposalId);
    }
}
