package kingdom.Readers_Writers.Proxy;

import kingdom.Flyweight.Gem;
import kingdom.Singleton.Catalog;

import java.util.List;

public class TreasureRoomGuardsman implements TreasureRoomDoor
{
    private TreasureRoomDoor room;


    // I used strategy from slides
    @Override public synchronized void acquireReadAccess(String actorName)
    {

        if (room == null)
        {
            room = new TreasureRoom();
        }
        Catalog.getInstance().printAction("Actor " + actorName + " enters the room");
        switch (actorName)
        {
            case "accountant":
            {
                room.acquireReadAccess("accountant");
            }
        }

    }

    @Override public synchronized void acquireWriteAccess(String actorName)
    {
    if(room ==null)
    {
        room = new TreasureRoom();
    }
        Catalog.getInstance().printAction("Actor "+actorName+" enters the room");

        switch(actorName)
    {
        case "transporter":
        {
            room.acquireWriteAccess("transporter");
        }
        case "king":
        {
            room.acquireWriteAccess("king");
        }
    }

     }
    @Override
    public synchronized void releaseReadAccess(String actorName) {
        if (room == null)
        {
            room = new TreasureRoom();
        }
        Catalog.getInstance().printAction("Actor " + actorName + " leaves the room");
        switch (actorName)
        {
            case "accountant":
            {
                room.releaseReadAccess("accountant");
            }
        }

    }

    @Override
    public synchronized void releaseWriteAccess(String actorName) {
        if (room == null)
        {
            room = new TreasureRoom();
        }
        Catalog.getInstance().printAction("Actor " + actorName + " leaves the room");
        switch (actorName)
        {
            case "transporter":
            {
                room.releaseWriteAccess("transporter");
            }
            case "king":
            {
                room.releaseWriteAccess("king");
            }
        }

    }

    @Override
    public  Gem retrieveValuable() {
        return room.retrieveValuable();
    }

    @Override
    public void addValuable(Gem v) {
        room.addValuable(v);
    }

    @Override
    public List<Gem> lookAtAllGems() {
        return room.lookAtAllGems();
    }
}
