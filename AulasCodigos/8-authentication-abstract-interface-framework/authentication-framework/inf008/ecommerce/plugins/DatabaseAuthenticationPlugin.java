package inf008.ecommerce.plugins;

import inf008.ecommerce.interfaces.IAuthenticationPlugin;

public class DatabaseAuthenticationPlugin implements IAuthenticationPlugin {
    public boolean load() {
        // Conecte-se com o banco
        System.out.println("Loading DatabaseAuthenticationPlugin");
        return true;
    }
    public boolean unload() {
        // Desconecte-se com o banco
        System.out.println("Unloading DatabaseAuthenticationPlugin");
        return true;
    }
    public boolean signIn(String username, String password) {
        // Autentique no banco
        System.out.println("Authenticating " + username + " from database!");
        return true;
    }
    public boolean signOut() {
        System.out.println("Signed out!");
        return true;
    }
}
