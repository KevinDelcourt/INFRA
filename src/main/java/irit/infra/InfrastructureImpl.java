
package irit.infra;

import irit.infra.agent.InfraAgent;
import irit.infra.agent.InfraAgentID;
import irit.infra.agent.InfraAgentImpl;
import irit.infra.communication.Communication;
import irit.infra.communication.Message;
import irit.infra.directory.AgentDirectory;
import irit.infra.directory.AgentDirectoryImpl;
import irit.infra.factory.InfraAgentFactoryImpl;
import irit.infra.scheduler.*;
import irit.infra.state.LifeCycleImpl;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class InfrastructureImpl implements Infrastructure {

    private InfraAgentFactoryImpl infraAgentFactory;
    private Scheduler scheduler;
    private AgentDirectory annuaire;

    public InfrastructureImpl() {
        scheduler = new SchedulerImpl(new ClassicStrategy(new ArrayList<>(), new ArrayList<>()));
//        scheduler = new Scheduler(new CycleByCycleStrategy(new ArrayList<>(), new ArrayList<>()));
        annuaire = AgentDirectoryImpl.getInstance();
        infraAgentFactory = new InfraAgentFactoryImpl(annuaire, scheduler);
    }

    @Override
    public void kill(InfraAgentImpl agent) {
        infraAgentFactory.kill(agent);
    }

    @Override
    public void startScheduling() {
        scheduler.startScheduling();
    }

    /**
     * Start a special scheduling cycle with a set of agents and for a certain number of cycles.
     * An example of a use case for this method, is to treat the feedback : scheduling the agents which are supposed to treat the feedback.
     *
     * @param listAgentsToSchedule :   the list of agents to schedule
     * @param numberCycles         :   the number of agent cycle to run (One cycle = Perception, Decision, Action)
     */
    @Override
    public void startSpecialScheduling(List<InfraAgent> listAgentsToSchedule, int numberCycles) {
        this.scheduler.startSpecialScheduling(listAgentsToSchedule, numberCycles);
    }

    @Override
    public void changeSpeed(EnumSpeed newSpeed) {
        scheduler.changeSpeed(newSpeed);
    }

    @Override
    public void changeSchedulingStrategy(SchedulingStrategy schedulingStrategy) {
        scheduler.changeSchedulingStrategy(schedulingStrategy);
    }

    @Override
    public void stopScheduling() {
        scheduler.stopScheduling();
    }

    @Override
    public void sendMessageBroadcast(Message message) {
        annuaire.sendMessageBroadcast(message);
    }

    @Override
    public void sendMessage(Message message) {
        annuaire.sendMessage(message);
    }

    @Override
    public Optional<Message> receiveMessage(InfraAgentID receiver) {
        return annuaire.receiveMessage(receiver);
    }

    @Override
    public ArrayList<Message> receiveMessages(InfraAgentID receiver) {
        return annuaire.receiveMessages(receiver);
    }

    @Override
    public InfraAgent createInfrastructureAgent(LifeCycleImpl lifeCycle, Communication myMailBoxManager) {
        InfraAgent infraAgent = infraAgentFactory.createInfrastructureAgent(lifeCycle, myMailBoxManager);
        return infraAgent;
    }

    public AgentDirectory getAnnuaire() {
        return annuaire;
    }

    public Scheduler getScheduler() {
        return scheduler;
    }

    @Override
    public void addAgentToScheduler(InfraAgent infraAgent) {

        scheduler.addAgentToScheduler(infraAgent);
    }

    @Override
    public void deleteAgentFromScheduler(InfraAgent infraAgent) {

        scheduler.deleteAgentFromScheduler(infraAgent);
    }

    /**
     * Set the value of the number of agent cycle per OCE Cycle
     * @param maxCycleAgent
     */
    @Override
    public void setMaxCycleAgent(int maxCycleAgent) {
       this.scheduler.setMaxCycleAgent(maxCycleAgent);
    }


    @Override
    public void resetCurrentCycleAgent() {
        this.scheduler.resetCurrentCycleAgent();
    }

    /**
     * This method gets called when a bound property is changed.
     *
     * @param evt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        //Depending on the name of the property we do the corresponding operation

    }
}
