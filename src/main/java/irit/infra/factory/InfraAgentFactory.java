package irit.infra.factory;


import irit.infra.agent.InfraAgent;
import irit.infra.communication.Communication;
import irit.infra.state.LifeCycleImpl;

public interface InfraAgentFactory {

    InfraAgent createInfrastructureAgent(LifeCycleImpl lifeCycle, Communication myMailBoxManager);
}
