package Readers_Writers.Proxy;

import Readers_Writers.Proxy.TreasureRoomDoor;
import kingdom.Flyweight.Gem;
import kingdom.Singleton.Catalog;

import java.util.List;

public class TreasureRoomGuardsman implements TreasureRoomDoor
{
    private TreasureRoomDoor room;

    // I used strategy from slides, similar to SeaBear exercise
    @Override public void acquireReadAccess(String actorName)
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

    @Override public void acquireWriteAccess(String actorName)
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
            room.acquireReadAccess("transporter");
        }
    }
     }
    @Override
    public void releaseReadAccess(String actorName) {
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
    public void releaseWriteAccess(String actorName) {
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
        }

    }

    @Override
    public Gem retrieveValuable() {
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