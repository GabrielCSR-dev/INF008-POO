public class Main{
    public static void main(String[] args) throws Exception {
        Administrator admin = new Administrator("admin", "bigboss123@hotmail.com", "admin");
        Customer Arnaldo = new Customer("Arnaldo", "arnaldogameplays@gmail.com", "Senha123", "Matatu");
        Customer Gabriel = new Customer("Gabriel", "gabrielcsribeiro42@gmail.com", "Senha12345", "Alagoinhas");
        Administrator Pedro = new Administrator("Pedro", "pedroplays47@gmail.com", "Senha12345");
        Administrator Joao = new Administrator("Joao", "jumbalaca47@gmail.com", "Senha1234567");
        // Arnaldo.display();
        Product Cadeira = new Product("Cadeira", "para sentar", 15.00f, 20, "Utilidades");
        Product Prato = new Product("Prato", "para botar comida", 5.00f, 60, "Cozinha");
        Product Televisao = new Product("Televisão", "para assistir", 200.00f, 15, "Lazer");
        Product Camiseta = new Product("Camiseta", "para vestir", 109.99f, 30, "Roupa");
        User.displayAll();
        Product.displayAll();

        User.login();
    }
}