package irit.infra.agent;

import java.util.UUID;

public class InfraAgentIDImpl implements InfraAgentID {
    private final UUID uuid;

    public InfraAgentIDImpl() {
        uuid = UUID.randomUUID();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        InfraAgentIDImpl that = (InfraAgentIDImpl) o;

        return uuid.equals(that.uuid);
    }


    @Override
    public UUID getUuid() {
        return uuid;
    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }

    @Override
    public String toString() {
        return "INFRA.IDAgent{" + this.uuid + '}';
    }
}
