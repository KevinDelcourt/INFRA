package irit.infra.agent;


import irit.infra.communication.Communication;
import irit.infra.communication.Message;
import irit.infra.state.LifeCycleImpl;
import irit.infra.state.State;

import java.util.ArrayList;
import java.util.Optional;

public class InfraAgentImpl implements InfraAgent {

    private final InfraAgentID infraAgentID;
    private LifeCycleImpl lifeCycle;
    private Communication myMailBoxManager;

    public InfraAgentImpl(LifeCycleImpl lifeCycle, Communication myMailBoxManager) {

        this.infraAgentID = new InfraAgentIDImpl();
        this.lifeCycle = lifeCycle;
        this.myMailBoxManager = myMailBoxManager;
    }

    @Override
    public void run() {
        this.lifeCycle.run();
    }

    @Override
    public InfraAgentID getInfraAgentID() {
        return infraAgentID;
    }

    @Override
    public State getState() {
        return lifeCycle.getCurrentState();
    }

    @Override
    public ArrayList<Message> readMessages() {
        return this.myMailBoxManager.receiveMessages(this.infraAgentID);
    }

    @Override
    public Optional<Message> readMessage() {
        return this.myMailBoxManager.receiveMessage(this.infraAgentID);
    }

    @Override
    public String toString() {
        return "INFRA.IDAgent{" + infraAgentID + '}';
    }
}
