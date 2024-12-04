public class Main{
    public static void main(String[] args) throws Exception {
        User Arnaldo = new User("Arnaldo", "arnaldogameplays@gmail.com", "Senha123");
        User Gabriel = new User("Gabriel", "gabrielcsribeiro42@gmail.com", "Senha12345");
        User Pedro = new User("Pedro", "pedroplays47@gmail.com", "Senha12345");
        User Joao = new User("Joao", "jumbalaca47@gmail.com", "Senha1234567");
        // Arnaldo.display();
        Product Cadeira = new Product("Cadeira", "para sentar", 15.50f, 20, "Utilidades");
        Product Prato = new Product("Prato", "para botar comida", 5.00f, 60, "Cozinha");
        Product Televisao = new Product("Televisão", "para assistir", 200.00f, 15, "Lazer");
        Product Camiseta = new Product("Camiseta", "para vestir", 107.99f, 30, "Roupa");
        User.displayAll();
        Product.displayAll();
    }
}