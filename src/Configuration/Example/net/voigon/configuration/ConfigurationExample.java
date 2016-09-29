package net.voigon.configuration;

import java.io.File;
import java.io.IOException;

import org.bukkit.plugin.java.JavaPlugin;

public class ConfigurationExample extends JavaPlugin {
	
	public void onEnable() {
		Configuration config;

		// Create new config
		config = new Configuration(this, "adir");
		
		// OR:
		config = new Configuration(new File(getDataFolder(), "config.yml"));
		config = new Configuration(new File(getDataFolder(), "config.yml").getPath()); // Of course you can write the path manually
		config = new Configuration(getDataFolder(), "config.yml"); 

		// Get an exists config
		config = Configuration.getInstance("adir");
		
		String str = config.getString("str");
		config.set("str", str+str);
		config.save();
		
		// OR (save this config the another file
		try {
			config.save(new File(getDataFolder(), "config.yml"));
		} catch (IOException e) {

			e.printStackTrace();
		}
		
	}
	
}
