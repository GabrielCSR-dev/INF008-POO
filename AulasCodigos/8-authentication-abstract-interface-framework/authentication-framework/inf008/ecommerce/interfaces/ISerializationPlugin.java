package inf008.ecommerce.interfaces;

public interface ISerializationPlugin extends IPlugin {
    public boolean save();
    public boolean load();
}
