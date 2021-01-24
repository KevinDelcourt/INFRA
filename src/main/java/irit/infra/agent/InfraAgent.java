package irit.infra.agent;

import irit.infra.communication.Message;
import irit.infra.state.State;

import java.util.ArrayList;
import java.util.Optional;

public interface InfraAgent extends Runnable {
    InfraAgentID getInfraAgentID();

    State getState();

    ArrayList<Message> readMessages();

    Optional<Message> readMessage();
}
