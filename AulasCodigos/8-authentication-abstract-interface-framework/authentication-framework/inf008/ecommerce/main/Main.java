package inf008.ecommerce.main;

import inf008.ecommerce.interfaces.IAuthenticationPlugin;
import inf008.ecommerce.plugins.DatabaseAuthenticationPlugin;
import inf008.ecommerce.plugins.GoogleAuthenticationPlugin;

public class Main {
    public static void main(String[] args) {
        IAuthenticationPlugin authPlugin = new GoogleAuthenticationPlugin();
        authPlugin.signIn("sandroandrade", "minhasenha");
    }
}
