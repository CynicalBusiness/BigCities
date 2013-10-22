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
	
	public void createRank(String cityName, String rankID, String rankName, String rankTag,
			boolean isMod, boolean isAdmin, boolean canPvp, boolean canBuild){
		String ct = "cities."+cityName+".ranks."+rankID+".";
		plugin.files.getCitiesList().set(ct+"name", rankName);
		plugin.files.getCitiesList().set(ct+"tag", rankTag);
		plugin.files.getCitiesList().set(ct+"mod", isMod);
		plugin.files.getCitiesList().set(ct+"admin", isAdmin);
		plugin.files.getCitiesList().set(ct+"pvp", canPvp);
		plugin.files.getCitiesList().set(ct+"build", canBuild);
	}
	
	public void createCity(String name, Player p){
		double cx = Math.floor(p.getLocation().getX()/16);
		double cz = Math.floor(p.getLocation().getZ()/16);
		String w = p.getWorld().getName();
		String cityOnChunk = "";
		Set<String> cities = plugin.files.getCitiesList().getConfigurationSection("cities").getKeys(false);
		for (String city : cities){
			List<String> claims = plugin.files.getCitiesList().getStringList("cities."+city+".claims");
			for (String claim : claims){
				if (claim.equalsIgnoreCase("["+cx+","+cz+","+w+"]")){
					cityOnChunk = city;
				}
			}
		}
		if (!cityOnChunk.equalsIgnoreCase("")){
			String cname = name.toLowerCase();
			String ct = "cities."+cname+".";
			plugin.files.getCitiesList().set(ct+"alias", name);
			plugin.files.getCitiesList().set(ct+"color", plugin.getConfig().getString("data.cities.defaultColor"));
			plugin.files.getCitiesList().set(ct+"funds", 0);
				plugin.files.getCitiesList().set(ct+"home.x", 0);
				plugin.files.getCitiesList().set(ct+"home.y", 0);
				plugin.files.getCitiesList().set(ct+"home.z", 0);
				plugin.files.getCitiesList().set(ct+"home.yaw", 0);
				plugin.files.getCitiesList().set(ct+"home.pitch", 0);
			plugin.files.getCitiesList().set(ct+"players", null);
			plugin.files.getCitiesList().set(ct+"ranks", null);
			plugin.files.getCitiesList().set(ct+"claims", Arrays.asList("["+cx+","+cz+","+w+"]"));
			createRank(cname, "default", "Citizen", "", false, false, false, false);
			createRank(cname, "admin", "Mayor", "", false, false, false, true);
		} else {
			p.sendMessage(plugin.parseColor(BigCities.tag+"&fYou cannot overclaim &9"+cityOnChunk+"&f!"));
		}
	}
}
