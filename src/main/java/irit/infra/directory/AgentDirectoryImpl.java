package irit.infra.directory;


import irit.infra.agent.InfraAgent;
import irit.infra.agent.InfraAgentID;
import irit.infra.communication.Message;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Consumer;

public class AgentDirectoryImpl implements AgentDirectory {

    private List<AgentListener> agentListeners;
    private List<ReferenceAgentListener> referenceAgentListeners;
    private List<MessageAgentListener> messageAgentListeners;
    private ConcurrentMap<InfraAgentID, InfraAgent> agents; // references des agents à l'instant t
    private ConcurrentMap<InfraAgentID, ConcurrentLinkedQueue<Message>> agentsMessagesQueues; // references des agents associés aux messages reçus
    private ConcurrentMap<InfraAgentID, ReadWriteLock> agentsLocks;

    private AgentDirectoryImpl() {
        referenceAgentListeners = Collections.synchronizedList(new ArrayList<>());
        agentListeners = Collections.synchronizedList(new ArrayList<>());
        messageAgentListeners = Collections.synchronizedList(new ArrayList<>());
        agents = new ConcurrentHashMap<>();
        agentsMessagesQueues = new ConcurrentHashMap<>();
        agentsLocks = new ConcurrentHashMap<>();
    }

    private static class AgentDirectoryHolder {
        private final static AgentDirectoryImpl instance = new AgentDirectoryImpl();
    }

    public static AgentDirectoryImpl getInstance() {
        return AgentDirectoryHolder.instance;
    }

    public ConcurrentMap<InfraAgentID, InfraAgent> getAgents() {
        return agents;
    }

    @Override
    public void addAgent(InfraAgent infraAgent) {
        agentsLocks.put(infraAgent.getInfraAgentID(), new ReentrantReadWriteLock());
        lockAgentEcriture(infraAgent.getInfraAgentID());
        agents.put(infraAgent.getInfraAgentID(), infraAgent);
        agentsMessagesQueues.put(infraAgent.getInfraAgentID(), new ConcurrentLinkedQueue<>());
        unlockAgentEcriture(infraAgent.getInfraAgentID());
        referenceAgentListeners.forEach(agentListener -> agentListener.agentAjoute(infraAgent.getInfraAgentID()));
        agentListeners.forEach(agentListener -> agentListener.addAgent(infraAgent));
    }

    @Override
    public void removeAgent(InfraAgentID infraAgentID) {

        agentListeners.forEach(agentListener -> agentListener.deleteAgent(agents.get(infraAgentID)));
        lockAgentEcriture(infraAgentID);
        agents.remove(infraAgentID);
        agentsMessagesQueues.remove(infraAgentID);
        unlockAgentEcriture(infraAgentID);
        referenceAgentListeners.forEach(agentListener -> agentListener.agentRetire(infraAgentID));
    }


    public ConcurrentMap<InfraAgentID, ConcurrentLinkedQueue<Message>> getAgentsMessagesQueues() {
        return agentsMessagesQueues;
    }

    @Override
    public void sendMessage(Message message) {
        int index;
        for (index = 0; index < message.getReceivers().size(); index++) {
            lockAgentLecture(message.getReceivers().get(index));
            if (agentsMessagesQueues.containsKey(message.getReceivers().get(index))) {
                agentsMessagesQueues.get(message.getReceivers().get(index)).add(message);
                int finalIndex = index;
                messageAgentListeners.forEach(messageAgentListener -> messageAgentListener.messageEnvoye(message.getEmitter(),
                        message.getReceivers().get(finalIndex), message));
            }
            unlockAgentLecture(message.getReceivers().get(index));
        }
    }


    @Override
    public void sendMessageBroadcast(Message message) {
        agentsMessagesQueues.keySet().forEach(this::lockAgentLecture);
        agentsMessagesQueues.entrySet().forEach(referenceAgentEntry -> {
            if (referenceAgentEntry.getKey() != message.getEmitter()) {
                referenceAgentEntry.getValue().add(message);
                notifierMessageAgentListeners(message.getEmitter(), message, referenceAgentEntry.getKey());
            }
        });
        agentsMessagesQueues.keySet().forEach(this::unlockAgentLecture);
    }

    private void notifierMessageAgentListeners(InfraAgentID expediteur, Message Message,
                                               InfraAgentID infraAgentID) {
        messageAgentListeners.forEach(
                messageAgentListener -> messageAgentListener.messageEnvoye(expediteur, infraAgentID, Message));
    }


    @Override
    public Optional<Message> receiveMessage(InfraAgentID receiver) {
        lockAgentLecture(receiver);
        Optional<Message> message = Optional.ofNullable(agentsMessagesQueues.get(receiver))
                .map(ConcurrentLinkedQueue::poll);
        message.ifPresent(messageAgent -> notifierMessageAgentListeners(messageAgent.getEmitter(), messageAgent,
                receiver));
        unlockAgentLecture(receiver);
        return message;
    }

    @Override
    public ArrayList<Message> receiveMessages(InfraAgentID receiver) {
        ArrayList messages = new ArrayList<>(agentsMessagesQueues.get(receiver));
        agentsMessagesQueues.get(receiver).clear();
        return messages;
    }


    private void lockAgentEcriture(InfraAgentID infraAgentID) {
        executeIfPresent(agentsLocks.get(infraAgentID), readWriteLock -> readWriteLock.writeLock().lock());
    }

    private void lockAgentLecture(InfraAgentID infraAgentID) {
        executeIfPresent(agentsLocks.get(infraAgentID), readWriteLock -> readWriteLock.readLock().lock());
    }

    private void unlockAgentEcriture(InfraAgentID infraAgentID) {
        executeIfPresent(agentsLocks.get(infraAgentID), readWriteLock -> readWriteLock.writeLock().unlock());
    }

    private void unlockAgentLecture(InfraAgentID infraAgentID) {
        executeIfPresent(agentsLocks.get(infraAgentID), readWriteLock -> readWriteLock.readLock().unlock());
    }

    private <T> void executeIfPresent(T object, Consumer<T> objectConsumer) {
        if (object != null) {
            objectConsumer.accept(object);
        }
    }

    public List<MessageAgentListener> getMessageAgentListeners() {
        return messageAgentListeners;
    }

}