package inf008.ecommerce.interfaces;

public interface IAuthenticationPlugin extends IPlugin {
    public boolean signIn(String username, String password);
    public boolean signOut();
}
