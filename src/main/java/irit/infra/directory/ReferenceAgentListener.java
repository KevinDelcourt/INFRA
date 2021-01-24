package irit.infra.directory;


import irit.infra.agent.InfraAgentID;

public interface ReferenceAgentListener {

    void agentAjoute(InfraAgentID infraAgentID);

    void agentRetire(InfraAgentID infraAgentID);
}
