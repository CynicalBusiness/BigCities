package net.mayateck.BigCities;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class FileManager {
	private BigCities plugin;
	
	private FileConfiguration players = null; private File playersFile = null;
	private FileConfiguration cities = null; private File citiesFile = null;
	private FileConfiguration chunks = null; private File chunksFile = null;
	
	public FileManager(BigCities plugin){
		this.plugin = plugin;
	}
	
	public void reloadPlayersList() {
		if (playersFile == null) {
			playersFile = new File(plugin.getDataFolder(), "players.yml");
		}
	    players = YamlConfiguration.loadConfiguration(playersFile);
	    InputStream vendorConfigStream = plugin.getResource("players.yml");
	    if (vendorConfigStream != null) {
	        YamlConfiguration vendorConfig = YamlConfiguration.loadConfiguration(vendorConfigStream);
	        players.setDefaults(vendorConfig);
	    }
	}
	public void reloadCitiesList() {
		if (citiesFile == null) {
			citiesFile = new File(plugin.getDataFolder(), "cities.yml");
		}
		cities = YamlConfiguration.loadConfiguration(citiesFile);
	    InputStream vendorConfigStream = plugin.getResource("cities.yml");
	    if (vendorConfigStream != null) {
	        YamlConfiguration vendorConfig = YamlConfiguration.loadConfiguration(vendorConfigStream);
	        cities.setDefaults(vendorConfig);
	    }
	}
	public void reloadChunksList() {
		if (chunksFile == null) {
			chunksFile = new File(plugin.getDataFolder(), "chunks.yml");
		}
		chunks = YamlConfiguration.loadConfiguration(chunksFile);
	    InputStream vendorConfigStream = plugin.getResource("chunks.yml");
	    if (vendorConfigStream != null) {
	        YamlConfiguration vendorConfig = YamlConfiguration.loadConfiguration(vendorConfigStream);
	        chunks.setDefaults(vendorConfig);
	    }
	}
	// #===# //
	public FileConfiguration getPlayersList() {
		if (players == null) {
	    	this.reloadPlayersList();
		}
		return players;
	}
	public FileConfiguration getCitiesList() {
		if (cities == null) {
	    	this.reloadPlayersList();
		}
		return cities;
	}
	public FileConfiguration getChunksList() {
		if (chunks == null) {
	    	this.reloadChunksList();
		}
		return chunks;
	}
	// #===# //
	
	public void savePlayersList() {
	    if (players == null || playersFile == null) {
	    	return;
	    }
	    try {
	        getPlayersList().save(playersFile);
	    } catch (IOException ex) {
	        plugin.getLogger().log(Level.SEVERE, "Could not save config to " + playersFile, ex);
	    }
	}
	public void saveCitiesList() {
	    if (cities == null || citiesFile == null) {
	    	return;
	    }
	    try {
	        getPlayersList().save(citiesFile);
	    } catch (IOException ex) {
	        plugin.getLogger().log(Level.SEVERE, "Could not save config to " + citiesFile, ex);
	    }
	}
	public void saveChunksList() {
	    if (chunks == null || chunksFile == null) {
	    	return;
	    }
	    try {
	        getPlayersList().save(chunksFile);
	    } catch (IOException ex) {
	        plugin.getLogger().log(Level.SEVERE, "Could not save config to " + citiesFile, ex);
	    }
	}
	// #===# //
	public void saveDefaultPlayersList() {
	    if (playersFile == null) {
	        playersFile = new File(plugin.getDataFolder(), "players.yml");
	    }
	    if (!playersFile.exists()) {
	         plugin.saveResource("players.yml", false);
	    }
	}
	public void saveDefaultCitiesList() {
	    if (citiesFile == null) {
	    	citiesFile = new File(plugin.getDataFolder(), "cities.yml");
	    }
	    if (!citiesFile.exists()) {
	         plugin.saveResource("cities.yml", false);
	    }
	}
	public void saveDefaultChunksList() {
	    if (chunksFile == null) {
	    	chunksFile = new File(plugin.getDataFolder(), "chunks.yml");
	    }
	    if (!chunksFile.exists()) {
	         plugin.saveResource("chunks.yml", false);
	    }
	}
}
