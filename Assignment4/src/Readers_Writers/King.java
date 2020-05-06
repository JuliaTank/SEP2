package Readers_Writers;


import Readers_Writers.Proxy.TreasureRoom;
import kingdom.Flyweight.Gem;
import kingdom.Singleton.Catalog;
import Readers_Writers.Proxy.TreasureRoomDoor;

import java.util.List;
import java.util.Random;

public class King implements TreasureRoomDoor, Runnable
{
    private TreasureRoom room;
    public static void tellAboutGems()
    {
        //tell catalog about throwing/ canceling parties
        Catalog.getInstance().printAction("Amazing, we are going to the party!");
    }

    @Override
    public synchronized void acquireReadAccess(String actorName) {
        if(room ==null)
        {
            room = new TreasureRoom();
        }
        Catalog.getInstance().printAction("Actor "+actorName+" enters the room");





    }

    @Override
    public synchronized void acquireWriteAccess(String actorName) {
        if(room ==null)
        {
            room = new TreasureRoom();
        }
        Catalog.getInstance().printAction("Actor "+actorName+" enters the room");





    }

    @Override
    public synchronized void releaseReadAccess(String actorName) {
    }

    @Override
    public synchronized void releaseWriteAccess(String actorName) {

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

    @Override
    public void run() {

        Random random = new Random();

        while (true)
        {
            int target = random.nextInt(100)+50;

    }
}}
