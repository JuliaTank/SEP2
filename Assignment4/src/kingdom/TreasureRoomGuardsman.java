package kingdom;

import kingdom.Flyweight.Gem;
import kingdom.Singleton.Catalog;

import java.util.List;

public class TreasureRoomGuardsman implements TreasureRoomDoor{
    @Override
    public void acquireReadAccess(String actorName) {
        Catalog.getInstance().printAction("Actor "+actorName+" enters the room");
        switch (actorName)
        {
            case "accountant":
            {
                
            }
        }
    }

    @Override
    public void acquireWriteAccess(String actorName) {

    }

    @Override
    public void releaseReadAccess(String actorName) {

    }

    @Override
    public void releaseWriteAccess(String actorName) {

    }

    @Override
    public Gem retrieveValuable() {
        return null;
    }

    @Override
    public void addValuable(Gem v) {

    }

    @Override
    public List<Gem> lookAtAllGems() {
        return null;
    }
}
