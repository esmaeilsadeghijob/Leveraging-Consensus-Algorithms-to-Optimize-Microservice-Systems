package com.example.paxosconsensusservice;

@Service
public class PaxosService {

    private final Paxos paxos = new Paxos();

    public boolean propose(int proposalId, int value) {
        return paxos.propose(proposalId, value);
    }

    public boolean accept(int proposalId, int value) {
        return paxos.accept(proposalId, value);
    }

    public Integer getAcceptedValue(int proposalId) {
        return paxos.getAcceptedValue(proposalId);
    }
}

