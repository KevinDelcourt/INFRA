package irit.infra.directory;


import irit.infra.agent.InfraAgentID;
import irit.infra.communication.Message;

public interface MessageAgentListener {

    void messageEnvoye(InfraAgentID expediteur, InfraAgentID destinataire, Message Message);

    void messageRecu(InfraAgentID expediteur, InfraAgentID destinataire, Message Message);
}
