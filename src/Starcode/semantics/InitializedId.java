package Starcode.semantics;

public class InitializedId
{
    public String id;
    public Object value;

    public InitializedId(String id, Object value)
    {
        this.id = id;
        this.value = value;
    }

    public String toString()
    {
        return id;
    }

    public Object getValue(){
        return value;
    }
}
