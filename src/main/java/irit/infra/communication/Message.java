package irit.infra.communication;

import irit.infra.agent.InfraAgentID;

import java.util.ArrayList;

public interface Message {

    InfraAgentID getEmitter();

    void setEmitter(InfraAgentID emitter);

    ArrayList<InfraAgentID> getReceivers();

    void setReceivers(ArrayList<InfraAgentID> receivers);

}
