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
	private FileConfiguration nations = null; private File nationsFile = null;
	
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
	public void reloadNationsList() {
		if (nationsFile == null) {
			nationsFile = new File(plugin.getDataFolder(), "nations.yml");
		}
		nations = YamlConfiguration.loadConfiguration(nationsFile);
	    InputStream vendorConfigStream = plugin.getResource("nations.yml");
	    if (vendorConfigStream != null) {
	        YamlConfiguration vendorConfig = YamlConfiguration.loadConfiguration(vendorConfigStream);
	        nations.setDefaults(vendorConfig);
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
	public FileConfiguration getNationsList() {
		if (nations == null) {
	    	this.reloadNationsList();
		}
		return nations;
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
	public void saveNationsList() {
	    if (nations == null || nationsFile == null) {
	    	return;
	    }
	    try {
	        getPlayersList().save(nationsFile);
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
	public void saveDefaultNationsList() {
	    if (nationsFile == null) {
	    	nationsFile = new File(plugin.getDataFolder(), "nations.yml");
	    }
	    if (!nationsFile.exists()) {
	         plugin.saveResource("nations.yml", false);
	    }
	}
	public void reloadAllData(){
		reloadPlayersList();
		reloadCitiesList();
		reloadNationsList();
	}
	public void saveAllDefaultData(){
		saveDefaultPlayersList();
		saveDefaultCitiesList();
		saveDefaultNationsList();
	}
	public void saveAllData(){
		savePlayersList();
		saveCitiesList();
		saveNationsList();
	}
}
