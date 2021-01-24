package irit.infra.factory;


import irit.infra.agent.InfraAgentImpl;

public interface AgentKiller {

    void kill(InfraAgentImpl infraAgent);
}
