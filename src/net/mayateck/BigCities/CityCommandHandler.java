package net.mayateck.BigCities;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CityCommandHandler implements CommandExecutor {
	private BigCities plugin;
	public CityCommandHandler(BigCities plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender p, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("city")){
			
		}
		return false;
	}

}
