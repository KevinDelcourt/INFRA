package irit.infra.state;

import java.util.ArrayList;

public interface LifeCycle extends Runnable{
    void setCurrentState(State newState);

    State getCurrentState();

    void shareVariable(String variableName, ArrayList dataToShare);

    ArrayList getSharedData(String variableName);
}
