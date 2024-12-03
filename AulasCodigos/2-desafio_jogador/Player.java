class Player
{
    private String name;
    private int energy;
    private int minEnergySword;

    public Player(String name, int minEnergySword) {
        this.name = name;
        this.minEnergySword = minEnergySword;
        this.energy = 0;
    }

    public void addEnergy(int energy) {
        System.out.println("Adding " + energy);
        this.energy += energy;
    }

    public void fight(Player otherPlayer)
    {
        // OPERADOR TERNÁRIO
        int realEnergyThis = energy >= minEnergySword ? 10 * energy : energy;
        int realEnergyOtherPlayer = otherPlayer.energy >= otherPlayer.minEnergySword ? 10 * otherPlayer.energy : otherPlayer.energy;
        // Two players fighting: this vs. otherPlayer
        if (realEnergyThis > realEnergyOtherPlayer) {
            System.out.println("this won");
            energy++;
            otherPlayer.energy--;
        } else if (realEnergyThis < realEnergyOtherPlayer) {
            System.out.println("other won");
            energy--;
            otherPlayer.energy++;
        } else { // Even
        System.out.println("even");
            double random = Math.random();
            if (random > 0.5) {
                energy++;
                otherPlayer.energy--;
            } else {
                energy--;
                otherPlayer.energy++;
            }
        }
        if (energy == 0) {
            System.out.println(otherPlayer.name + " won!");
            System.exit(0);
        }
        if (otherPlayer.energy == 0) {
            System.out.println(name + " won!");
            System.exit(0);
        }
    }

}
