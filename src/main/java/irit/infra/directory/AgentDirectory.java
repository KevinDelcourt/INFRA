package irit.infra.directory;


import irit.infra.agent.InfraAgent;
import irit.infra.agent.InfraAgentID;
import irit.infra.communication.Communication;
import irit.infra.communication.Message;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;

public interface AgentDirectory extends Communication, AgentDirectoryManager {

    ConcurrentMap<InfraAgentID, ConcurrentLinkedQueue<Message>> getAgentsMessagesQueues();

    ConcurrentMap<InfraAgentID, InfraAgent> getAgents();

}
