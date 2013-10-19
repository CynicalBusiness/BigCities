package net.mayateck.BigCities;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CityCommandHandler implements CommandExecutor {
	private BigCities plugin;
	public Map<String, String> helptext = new HashMap<String, String>();
	
	public CityCommandHandler(BigCities plugin) {
		this.plugin = plugin;
		helptext.put("found [city]", "Founds a new city by the name [city].");
	}
	
	
	
		

	@Override
	public boolean onCommand(CommandSender ps, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("city")){
			if (ps.hasPermission("bigcities.city")){
				if (args.length>0){
					if (args[0].equalsIgnoreCase("help")){
						
					} else if (args[0].equalsIgnoreCase("found")){
						if (ps.hasPermission("bitcities.city.found")){
							if (args.length==2){
								// TODO Economy
								if (args[1].matches("^[A-Za-z]{"+plugin.getConfig().getInt("data.cities.nameLengthMin")+","+plugin.getConfig().getInt("data.cities.nameLengthMax")+"}$")){
									plugin.getLogger().info(ps.getName()+" attempted city foundation by the name of "+args[1]);
									plugin.cityHandler.createCity(args[1], (Player)ps);
									return true;
								} else {
									ps.sendMessage(plugin.parseColor(BigCities.tag+"&cError: &fCity name does not match format! &8(&c^[A-Za-z]{6,16}$&8)"));
									return true;
								}
							} else {
								ps.sendMessage(plugin.parseColor(BigCities.tag+"&cError: &fBad argument count."));
								return true;
							}
						} else {
							ps.sendMessage(plugin.parseColor(BigCities.tag+"&cError: &fYou don't have permission to found a city!"));
							return true;
						}
					} else {
						ps.sendMessage(plugin.parseColor(BigCities.tag+"&cError: &fUnknown sub-command. Try &7/city help&f."));
						return true;
					}
				} else {
					ps.sendMessage(plugin.parseColor(BigCities.tag+" &cError: &fYou don't have permission to do that."));
					return true;
				}
			} else {
				args[0]="help";
				return onCommand(ps, cmd, label, args);
			}
		}
		return false;
	}

}
