package Starcode;

import Starcode.ast.OneDeclaration;

import java.util.Vector;

public class DeclarationsTable
{
    private Vector<IdEntry> declarations = new Vector<>();

    public DeclarationsTable()
    {

    }

    public void addDeclaration(String id, OneDeclaration declaration)
    {
        IdEntry idEntry = findDeclaration(id);
        if(idEntry != null)
        {
            System.out.println( id + " declared twice" );
            return;
        }

        declarations.add(new IdEntry(id, declaration));
    }

    public OneDeclaration getDeclaration(String id)
    {
        IdEntry entry = findDeclaration(id);

        if( entry != null )
            return entry.attr;
        else
            return null;
    }

    private IdEntry findDeclaration( String id )
    {
        for(IdEntry entry : declarations)
        {
            if(entry.id.equals(id))
            {
                return entry;
            }
        }
        return null;
    }
}
