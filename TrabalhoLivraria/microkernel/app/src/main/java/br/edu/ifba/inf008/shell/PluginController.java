package br.edu.ifba.inf008.shell;

import br.edu.ifba.inf008.App;
import br.edu.ifba.inf008.interfaces.IPluginController;
import javafx.scene.layout.VBox;
import br.edu.ifba.inf008.interfaces.IPlugin;
import java.io.File;
import java.io.FilenameFilter;
import java.net.URL;
import java.net.URLClassLoader;

public class PluginController implements IPluginController
{
    public boolean init(VBox vbox) {
        try {
            File currentDir = new File("./plugins");

            // Define a FilenameFilter to include only .jar files
            FilenameFilter jarFilter = new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.toLowerCase().endsWith(".jar");
                }
            };

            String []plugins = currentDir.list(jarFilter);
            int i;
            URL[] jars = new URL[plugins.length];
            for (i = 0; i < plugins.length; i++)
            {
                jars[i] = (new File("./plugins/" + plugins[i])).toURL();
            }
            URLClassLoader ulc = new URLClassLoader(jars, App.class.getClassLoader());
            for (i = 0; i < plugins.length; i++)
            {
                String pluginName = plugins[i].split("\\.")[0];
                IPlugin plugin = (IPlugin) Class.forName("br.edu.ifba.inf008.plugins." + pluginName, true, ulc).newInstance();
                plugin.init(vbox);
            }

            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e.getClass().getName() + " - " + e.getMessage());

            return false;
        }
    }
}
