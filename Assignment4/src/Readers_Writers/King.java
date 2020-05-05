package Readers_Writers;


import kingdom.Flyweight.Gem;
import kingdom.Singleton.Catalog;
import Readers_Writers.Proxy.TreasureRoomDoor;

import java.util.List;

public class King implements
    TreasureRoomDoor //na to nie patrz bo to porazkaaaaaaa, pozniej to zrobie, no co ty
{
    private boolean kingInTreasureRoom;

    private int waitingTransporter;
    private int transporterInTreasureRoom;
    private int accountantInTreasureRoom;

    public static void tellAboutGems()
    {
        //tell catalog about throwing/ canceling parties
        Catalog.getInstance().printAction("Amazing, we are going to the party!");
    }

    @Override
    public synchronized void acquireReadAccess(String actorName) {
        if(actorName.equals("King"))
        {
            while (kingInTreasureRoom || waitingTransporter>0)
            {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            accountantInTreasureRoom++;
        }

    }

    @Override
    public synchronized void acquireWriteAccess(String actorName) {
        if(actorName.equals("King"))
        {
            waitingTransporter++;
            while (kingInTreasureRoom || accountantInTreasureRoom>0)
            {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            waitingTransporter--;
            kingInTreasureRoom=true;
        }
    }

    @Override
    public synchronized void releaseReadAccess(String actorName) {
        if(actorName.equals("King"))
        {
            accountantInTreasureRoom--;
            if(accountantInTreasureRoom==0)
            {
                notifyAll();
            }
        }
    }

    @Override
    public synchronized void releaseWriteAccess(String actorName) {
        kingInTreasureRoom=false;
        notifyAll();
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