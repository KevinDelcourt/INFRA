package irit.infra.exemple;


import irit.infra.Infrastructure;
import irit.infra.agent.InfraAgentID;
import irit.infra.communication.Communication;
import irit.infra.communication.Message;

import java.util.ArrayList;
import java.util.Optional;

public class CommunicationParMessage implements Communication {

    private Infrastructure i;

    @Override
    public void sendMessageBroadcast(Message message) {

    }

    public CommunicationParMessage(Infrastructure i) {

        this.i = i;
    }

    @Override
    public void sendMessage(Message message) {

        i.sendMessage(message);

    }

    @Override
    public Optional<Message> receiveMessage(InfraAgentID receiver) {
        return Optional.empty();
    }


    @Override
    public ArrayList<Message> receiveMessages(InfraAgentID receiver) {
        return i.receiveMessages(receiver);
    }

    public Infrastructure getI() {
        return i;
    }
}
