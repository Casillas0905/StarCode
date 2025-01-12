package Starcode.semantics;

import java.util.Vector;

public class InitializationTable
{
    private Vector<InitializedId> initializedVariables = new Vector<>();

    public InitializationTable()
    {

    }

    public void addInitialization(String id, Object value)
    {
        InitializedId initializedId = getInitializedId(id);
        if(initializedId != null)
        {
            initializedId.value = value;
            return;
        }

        initializedVariables.add(new InitializedId(id, value));
    }

    public InitializedId getInitializedId(String id)
    {
        for(InitializedId initId : initializedVariables)
        {
            if(initId.id.equals(id))
            {
                return initId;
            }
        }
        return null;
    }
}
