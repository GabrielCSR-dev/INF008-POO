class Administrator extends User{

    public Administrator(String name, String email, String password) throws Exception{
        super(name, email, password);
    }
    public void enterMenu() throws Exception{
        while(true){
            int menuChoice = UIController.adminHomeUI();
            switch(menuChoice){
                case 1: createNewProduct(); break;
                case 2: createNewUser(); break;
                case 3: Order.displayMostExpensive(); break;
                case 4: Product.displayLeastStocked(); break;
                case 5: return;
                default: System.out.println("Invalid option."); break;
           }
        }
    }
    private void createNewProduct(){
        String[] atribute = UIController.adminProductCreationUI();
        new Product(atribute[0], atribute[1], Float.parseFloat(atribute[2]), Integer.parseInt(atribute[3]), atribute[4]);
    }
    private void createNewUser() throws Exception{
        int type = UIController.adminSelectTypeUI();
        String[] atribute = UIController.adminUserCreationUI(type);
        if(type == 1) new Administrator(atribute[0], atribute[1], atribute[2]);
        else new Customer(atribute[0], atribute[1], atribute[2], atribute[3]);
    }
}