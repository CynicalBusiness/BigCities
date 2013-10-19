package net.mayateck.BigCities;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.bukkit.entity.Player;

public class CityHandler {
	BigCities plugin;
	public CityHandler(BigCities plugin) {
		this.plugin = plugin;
	}
	
	
	public void createCity(String name, Player p){
		double cx = Math.floor(p.getLocation().getX()/16);
		double cz = Math.floor(p.getLocation().getZ()/16);
		String cityOnChunk = "";
		Set<String> cities = plugin.files.getCitiesList().getConfigurationSection("cities").getKeys(false);
		for (String city : cities){
			List<String> claims = plugin.files.getCitiesList().getStringList("cities."+city+".claims");
			for (String claim : claims){
				if (claim.equalsIgnoreCase("["+cx+","+cz+"]")){
					cityOnChunk = city;
				}
			}
		}
		if (!cityOnChunk.equalsIgnoreCase("")){
			String cname = name.toLowerCase();
			String ct = "cities."+cname+".";
			plugin.files.getCitiesList().set(ct+"claims", Arrays.asList("["+cx+","+cz+"]"));
			plugin.files.getCitiesList().set(ct+"alias", name);
			plugin.files.getCitiesList().set(ct+"color", plugin.getConfig().getString("data.cities.defaultColor"));
		} else {
			p.sendMessage(plugin.parseColor(BigCities.tag+"&fYou cannot overclaim &9"+cityOnChunk+"&f!"));
		}
	}
}
