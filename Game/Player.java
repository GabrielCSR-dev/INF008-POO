class Player {
    private String name;
    private int swordEnergy;
    private int Energy;

    public Player(String name, int minEnergy){
        this.name = name;
        this.swordEnergy = minEnergy;
        this.Energy = 0;
    }

    public void addEnergy(int Energy){
        this.Energy += Energy;
        System.out.println("Energy added!\n");
    }

    public void DuelPlayer(Player rival){
        while(true){
            int RealEnergy = Energy >= swordEnergy ? 10 * Energy : Energy;
            int RealrivalEnergy = rival.Energy >= rival.swordEnergy ? 10 * rival.Energy : rival.Energy;
            System.out.println("P1 Energy: " + Energy + "\n");
            System.out.println("P2 Energy: " + rival.Energy + "\n");
            
            if (RealEnergy < RealrivalEnergy){
                rival.Energy++;
                this.Energy--;
            } else if (RealEnergy > RealrivalEnergy){
                rival.Energy--;
                this.Energy++;
            } else{
                //aleatorio
                double Winner = Math.random();
                if(Winner > 0.5){
                    this.Energy++;
                    rival.Energy--;
                }else{
                    this.Energy--;
                    rival.Energy++;
                }
            }

            if(Energy == 0){
                System.out.println(rival.name + " won!");
                System.exit(0);
            }
            if(rival.Energy == 0){
                System.out.println(name + " won!");
                System.exit(0);
            }
        }
    }


}