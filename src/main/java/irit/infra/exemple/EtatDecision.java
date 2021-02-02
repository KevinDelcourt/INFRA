package irit.infra.exemple;



import irit.infra.agent.InfraAgent;
import irit.infra.communication.Communication;
import irit.infra.state.State;
import irit.infra.state.LifeCycle;

import java.util.ArrayList;

public class EtatDecision implements State {

    private State nextState;
    private Communication communication;
    private InfraAgent agt;
    private String name;

    public EtatDecision(String name) {
        this.name = name;
    }

    public void setCommunication(Communication communication) {
        this.communication = communication;
    }

    public void setInfraAgent(InfraAgent agt) {
        this.agt = agt;
    }

    public void setNextState(State nextState) {
        this.nextState = nextState;
    }

    @Override
    public void execute(LifeCycle c) {

        MonMessage m = new MonMessage(agt.getInfraAgentID(), null);
        if (communication != null) {
            communication.sendMessageBroadcast(m);
        }
            System.out.println(name+ " : décision partage " + c.getSharedData("decision"));
            ArrayList<String> info = new ArrayList();
            info.add("broadcast");
            c.shareVariable("perception", info);
            c.setCurrentState(this.nextState);
        }



}