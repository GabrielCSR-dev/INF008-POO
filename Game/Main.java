public class Main {
    public static void main(String[] args) {
        System.out.println("Jogo PVP\n");
        Player p1 = new Player("Player1", 10);
        Player p2 = new Player("Player2", 10);
        p1.addEnergy((int) (Math.random() * 100));
        p2.addEnergy((int) (Math.random() * 100));
        // p1.addEnergy(25);
        // p2.addEnergy(50);
        p1.DuelPlayer(p2);
    }
}