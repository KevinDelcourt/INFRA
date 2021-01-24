package irit.infra.communication;

import irit.infra.agent.InfraAgentID;

import java.util.ArrayList;
import java.util.Optional;

public interface Communication {

    void sendMessageBroadcast(Message message);

    void sendMessage(Message message);

    Optional<Message> receiveMessage(InfraAgentID receiver);

    ArrayList<Message> receiveMessages(InfraAgentID receiver);

}
