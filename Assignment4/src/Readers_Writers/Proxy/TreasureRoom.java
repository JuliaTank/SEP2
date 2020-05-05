package Readers_Writers.Proxy;

import Readers_Writers.Proxy.TreasureRoomDoor;
import kingdom.Flyweight.Gem;
import kingdom.Singleton.Catalog;

import java.util.ArrayList;
import java.util.List;

public class TreasureRoom implements TreasureRoomDoor
{
    private List<Gem> gems;

    public TreasureRoom() {
        gems = new ArrayList<>();
    }

    // access methods

    public synchronized void acquireReadAccess(String actorName) {
        Catalog.getInstance().printAction(actorName+" entered");
    }

    public synchronized void acquireWriteAccess(String actorName) {
        Catalog.getInstance().printAction(actorName+" entered");
    }

    public synchronized void releaseReadAccess(String actorName) {
        Catalog.getInstance().printAction(actorName+" left");
    }

    public synchronized void releaseWriteAccess(String actorName) {
        Catalog.getInstance().printAction(actorName+" left");
    }

    // interact methods

    public Gem retrieveValuable() {
        Gem v = null;
        if (gems.size() > 0) {
            v = gems.remove(0);
        }
        return v;
    }

    public void addValuable(Gem v) {
        gems.add(v);
    }

    @Override
    public List<Gem> lookAtAllGems() {
        return new ArrayList<Gem>(gems);
    }
}
