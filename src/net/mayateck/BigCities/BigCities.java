package net.mayateck.BigCities;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class BigCities extends JavaPlugin implements Listener{
	Plugin plugin = this;
	
	public final String version = plugin.getDescription().getVersion();
	EventHandler eventCatch;
	CityHandler cityHandler;
	FileManager files = new FileManager(this);
	public static Map<String, double[]> playerChunks = new HashMap<String, double[]>();
	public static final String tag = "&8[&eBigCities&8]&r ";
	
	@Override
	public void onEnable(){
		this.getLogger().info("#======# BigCities v"+version+" by Wehttam664 #======#");
		this.getLogger().info("Initializing local data...");
			getCommand("city").setExecutor(new CityCommandHandler(this));
			eventCatch = new EventHandler(this);
			cityHandler = new CityHandler(this);
			this.saveDefaultConfig();
			files.saveAllDefaultData();
		this.getLogger().info("Initializing instance data...");
		
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
		this.getLogger().info("Goodbye.");
		this.getLogger().info("#=====================================================#");
	}
	
	public String parseColor(String str){
		str = ChatColor.translateAlternateColorCodes('&', str);
		return str;
	}
	
}
