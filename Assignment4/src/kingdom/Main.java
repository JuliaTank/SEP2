package kingdom;

import kingdom.Readers_Writers.Accountant;
import kingdom.Readers_Writers.King;
import kingdom.Readers_Writers.Proxy.TreasureRoomDoor;
import kingdom.Readers_Writers.Proxy.TreasureRoomGuardsman;
import kingdom.Adapter.GemDeposit;
import kingdom.Producer_Consumer.GemMineWorker;
import kingdom.Producer_Consumer.GemTransporter;

public class Main {
    public static void main(String[] args) {

        GemDeposit deposit= new GemDeposit();
        TreasureRoomDoor room = new TreasureRoomGuardsman();

        GemTransporter gemTransporter = new GemTransporter(deposit,room);
        Thread thread = new Thread(gemTransporter);
        GemTransporter gemTransporter2 =new GemTransporter(deposit,room);
        Thread thread1 = new Thread(gemTransporter2);

        King king = new King(room);
        Thread kingThread = new Thread(king);

        Accountant accountant = new Accountant(room);
        Thread  thread7 = new Thread(accountant);
        GemMineWorker worker = new GemMineWorker(deposit,1);
        Thread thread2 = new Thread(worker);
        GemMineWorker worker1 = new GemMineWorker(deposit,2);
        Thread thread3 = new Thread(worker1);
        GemMineWorker worker2 = new GemMineWorker(deposit,3);
        Thread thread4 = new Thread(worker2);
        GemMineWorker worker3 = new GemMineWorker(deposit,4);
        Thread thread5 = new Thread(worker3);
        GemMineWorker worker4 = new GemMineWorker(deposit,5);
        Thread thread6 = new Thread(worker4);

        thread.start();
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread6.start();
        thread7.start();
        kingThread.start();

    }
}
