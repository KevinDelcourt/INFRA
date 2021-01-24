package irit.infra.directory;


import irit.infra.agent.InfraAgent;
import irit.infra.agent.InfraAgentID;

public interface AgentDirectoryManager {

    void addAgent(InfraAgent infraAgent);

    void removeAgent(InfraAgentID infraAgentID);
}
