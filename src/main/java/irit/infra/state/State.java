

package irit.infra.state;

/**
 * Interface representing an anbstract state of the life cyrcle of an agent
 * @author Walid YOUNES
 * @version 1.0
 */
public interface State {

    /**
     * This function allows to run the action associated to the current state
     * @param c the life cycle of the agent
     */
    void execute(LifeCycle c);
}