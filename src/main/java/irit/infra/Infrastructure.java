package irit.infra;

import irit.infra.communication.Communication;
import irit.infra.factory.AgentKiller;
import irit.infra.factory.InfraAgentFactory;
import irit.infra.scheduler.Scheduler;

import java.beans.PropertyChangeListener;

public interface Infrastructure extends InfraAgentFactory, AgentKiller, Communication, Scheduler, PropertyChangeListener {
}
