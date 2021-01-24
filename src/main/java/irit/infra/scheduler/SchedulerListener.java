package irit.infra.scheduler;


import irit.infra.agent.InfraAgentID;
import irit.infra.state.State;

public interface SchedulerListener {

    void changementEtat(InfraAgentID infraAgentID, State etatAbstract);
}
