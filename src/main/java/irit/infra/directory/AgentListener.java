package irit.infra.directory;


import irit.infra.agent.InfraAgent;
import irit.infra.agent.InfraAgentImpl;

public interface AgentListener {

    void addAgent(InfraAgent infraAgent);

    void deleteAgent(InfraAgent infraAgent);
}
