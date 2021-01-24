package irit.infra.factory;


import irit.infra.agent.InfraAgent;
import irit.infra.agent.InfraAgentImpl;
import irit.infra.communication.Communication;
import irit.infra.directory.AgentDirectory;
import irit.infra.scheduler.Scheduler;
import irit.infra.state.LifeCycleImpl;

public class InfraAgentFactoryImpl implements InfraAgentFactory, AgentKiller {
    private final AgentDirectory annuaire;
    private final Scheduler scheduler;

    public InfraAgentFactoryImpl(AgentDirectory annuaire, Scheduler scheduler) {
        this.annuaire = annuaire;
        this.scheduler = scheduler;
    }

    @Override
    public InfraAgent createInfrastructureAgent(LifeCycleImpl lifeCycle, Communication myMailBoxManager) {
        InfraAgent infraAgent = new InfraAgentImpl(lifeCycle, myMailBoxManager);
        annuaire.addAgent(infraAgent);
        scheduler.addAgentToScheduler(infraAgent);
        return infraAgent;
    }

    @Override
    public void kill(InfraAgentImpl infraAgent) {
        annuaire.removeAgent(infraAgent.getInfraAgentID());
        scheduler.deleteAgentFromScheduler(infraAgent);
    }

}
