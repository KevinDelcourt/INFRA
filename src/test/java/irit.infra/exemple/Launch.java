package irit.infra.exemple;


// interface IMessage : getter et setter sans attribut

import irit.infra.Infrastructure;
import irit.infra.InfrastructureImpl;
import irit.infra.agent.InfraAgent;
import irit.infra.state.LifeCycleImpl;

public class Launch {

    public static void main(String[] args) {
        Infrastructure i = new InfrastructureImpl();  // un scheduler et un annuaire
        CommunicationParMessage maCom= new CommunicationParMessage(i);

        // creation d'un agent de l'application et lien avec infra
        MonAgent a1=new MonAgent("a1");

        InfraAgent infraA1=i.createInfrastructureAgent(new LifeCycleImpl(a1.getPerception()), maCom);
        // la création ajoute l'agent dans l'infrastructure

        a1.setInfraAgent(infraA1);
        a1.getPerception().setInfraAgent(infraA1);
        a1.getDecision().setInfraAgent(infraA1);
        a1.getDecision().setCommunication(i);



        // creation d'un agent de l'application et lien avec infra
        MonAgent a2=new MonAgent("a2");

        a2.getDecision().setCommunication(i);


        InfraAgent infraA2=i.createInfrastructureAgent(new LifeCycleImpl(a2.getPerception()), maCom);

        a2.setInfraAgent(infraA2);
        a2.getPerception().setInfraAgent(infraA2);
        a2.getDecision().setInfraAgent(infraA2);

        i.startScheduling();
  }
}
