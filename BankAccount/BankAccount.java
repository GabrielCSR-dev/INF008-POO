class BankAccount{
  //  METODOS
  public BankAccount(int id, String holder){
    this.id = id;
    this.holder = holder;
    balance = 0;
  }

  public void display() {
    System.out.println(holder + " - " + id + " - " + balance);
  }
  
  // ATRIBUTOS
  private int id;
  private float balance;
  private String holder;
}