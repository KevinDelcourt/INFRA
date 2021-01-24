package irit.infra.exemple;



import irit.infra.agent.InfraAgentID;

import java.util.ArrayList;

public class Message implements irit.infra.communication.Message {
    private InfraAgentID emitter;
    private ArrayList<InfraAgentID> receivers = new ArrayList();
    private String content;


    public Message(InfraAgentID emitter, InfraAgentID receiver) {
        this.emitter = emitter;
        this.receivers.add(receiver);
        this.content = "message de demande de rdv de " + emitter;
    }

    @Override
    public InfraAgentID getEmitter() {
        return emitter;
    }

    @Override
    public void setEmitter(InfraAgentID emitter) {

    }

    @Override
    public ArrayList<InfraAgentID> getReceivers() {
        return receivers;
    }

    @Override
    public void setReceivers(ArrayList<InfraAgentID> receivers) {

    }

    public String getContent() {
        return content;
    }
}
