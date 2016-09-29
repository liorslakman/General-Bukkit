package net.voigon.configuration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.naming.OperationNotSupportedException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class Configuration extends FileConfiguration {
	
    private static final Map<String,Configuration>
            files = new HashMap<>();
   
    public static final Configuration getInstance(String name) {
        if (!files.containsKey(name))
            files.put(name, new Configuration(new File(baseFolder, name)));
        return files.get(name);
    }
   
    public static File
            baseFolder;
   
    protected File
            file;
   
    protected String
            name;
   
    protected Plugin
    		plugin;
    
    /**
     * @param parent parent folder
     * @param name the name of the file
     */
    public Configuration(File parent, String name) {
        this(new File(parent,name));
       
    }
   
    /**
     * @param file the path to the file
     */
    public Configuration(String file) {
        this(baseFolder, file + ".yml");
       
        if (baseFolder == null) {
            try {
                throw new OperationNotSupportedException("baseFolder cannot be null");
            } catch (OperationNotSupportedException e) {
 
                e.printStackTrace();
            }
        }
       
    }
   
    /**
     * @param owner the owner of this file
     * @param name the full path to this file
     */
    public Configuration(Plugin owner, String name) {
        this(owner.getDataFolder(),name);
        this.plugin = owner;
        
    }
   
    /**
     * @param file the file to handle
     */
    public Configuration(File file) {
        super(YamlConfiguration.loadConfiguration(file));
       
        this.file = file;
        this.name = file.getName().replaceAll(".yml", "");

        save();
        files.put(name, this);
       
    }
    
    /**
     * delete this file
     */
    public boolean delete() {
    	files.remove(getName());
    	return getFile().delete();
    }
    
    /**
     * get the file of this config
     * @return the file of this config
     */
    public File getFile() {
    	
    	return file;
    }
    
    /**
     * get the name of this config
     * @return the name of this config
     */
    @Override
    public String getName() {

    	return name;
    }
    
    @Override
    protected String buildHeader() {

    	return null;
    }
 
    @Override
    public void loadFromString(String arg0) throws InvalidConfigurationException {

    }
 
    @Override
    public String saveToString() {
 
        return null;
    }
   
    /**
     * save this config to the default file
     */
    public void save() {
        try {
            save(file);
        } catch (IOException e) {
 
            e.printStackTrace();
        }
    }
   
   
}
