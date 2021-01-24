package irit.infra.scheduler;


import irit.infra.agent.InfraAgent;
import irit.infra.agent.InfraAgentImpl;
import irit.infra.directory.AgentListener;

import java.util.List;

public interface SchedulingStrategy extends AgentListener {
    void startScheduling();

    /**
     * Start a special scheduling cycle with a set of agents and for a certain number of cycles.
     * An example of a use case for this method, is to treat the feedback : scheduling the agents which are supposed to treat the feedback.
     * @param listAgentsToSchedule  :   the list of agents to schedule
     * @param numberCycles          :   the number of agent cycle to run (One cycle = Perception, Decision, Action)
     */
    void startSpecialScheduling(List<InfraAgent> listAgentsToSchedule, int numberCycles);

    void changeSpeed(EnumSpeed speed);

    void stopScheduling();

    void setMaxCycleAgent(int maxCycleAgent);

    void resetCurrentCycleAgent();

    void addSchedulingListener(SchedulerListener schedulerListener);

    void addAgent(InfraAgent infraAgent);

    void deleteAgent(InfraAgent infraAgent);
}
