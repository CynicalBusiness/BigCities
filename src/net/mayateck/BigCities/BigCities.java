package net.mayateck.BigCities;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class BigCities extends JavaPlugin implements Listener{
	Plugin plugin = this;
	
	public String version = plugin.getDescription().getVersion();
	EventHandler eventCatch;
	FileManager files = new FileManager(this);
	
	@Override
	public void onEnable(){
		this.getLogger().info("#======# BigCities v"+version+" by Wehttam664 #======#");
		this.getLogger().info("Please wait while the plug-in is set up...");
			getCommand("city").setExecutor(new CityCommandHandler(this));
			eventCatch = new EventHandler(this);
			this.saveDefaultConfig();
			files.saveDefaultCitiesList();
			files.saveDefaultPlayersList();
			
		this.getLogger().info("Setup completed. Plug-in loaded.");
		this.getLogger().info("#=====================================================#");
	}
	
	@Override
	public void onDisable(){
		this.getLogger().info("#======# BigCities v"+version+" by Wehttam664 #======#");
		this.getLogger().info("Please wait while the plug-in shuts down...");
			this.saveConfig();
			files.savePlayersList();
			files.saveCitiesList();
		this.getLogger().info("Shutdown finished. Goodbye.");
		this.getLogger().info("#=====================================================#");
	}
	
	
}
