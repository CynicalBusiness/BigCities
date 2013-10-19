package net.mayateck.BigCities;


import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class EventHandler implements Listener{
	
	private BigCities plugin;
	public EventHandler(BigCities plugin) {
		this.plugin = plugin;
	}
	
	public void onPlayerChangeChunk(Player p){
		BigCities.playerChunks.put(p.getName(), new double[]{Math.floor(p.getLocation().getX()/16), Math.floor(p.getLocation().getZ()/16)});
		// TODO Echo chunk info to player.
	}
	
	public void onPlayerMove(PlayerMoveEvent e){
		Player p = e.getPlayer();
		String pname = p.getName();
		if (!BigCities.playerChunks.containsKey(pname)){
			BigCities.playerChunks.put(pname, new double[]{Math.floor((p.getLocation().getX())/16), Math.floor((p.getLocation().getZ())/16)});
			plugin.getLogger().info("Player "+pname+" not registered in chunk data. Registered and recalling...");
			onPlayerMove(e);
		} else {
			double[] pos = BigCities.playerChunks.get(pname);
			if ((!(pos[0]==Math.floor(p.getLocation().getX()/16))) || (!(pos[1]==Math.floor(p.getLocation().getZ()/16)))){
				onPlayerChangeChunk(p);
			}
		}
	}
	
	public void onPlayerJoin(PlayerJoinEvent e){
		Player p = e.getPlayer();
		if (!plugin.files.getPlayersList().contains("players."+p.getName())){
			plugin.files.getPlayersList().set("players."+p.getName()+".city", "Survivors");
			plugin.files.getPlayersList().set("players."+p.getName()+".strength", plugin.getConfig().getDouble("data.players.strengthStart"));
			plugin.files.savePlayersList();
		}
	}
	
	public void onEntityDeath(EntityDeathEvent e){
		EntityType etype = e.getEntityType();
		if (etype==EntityType.PLAYER){
			Player p = (Player)e.getEntity();
			double loss = plugin.getConfig().getDouble("data.players.strengthLossOnDeath");
			double s = plugin.files.getPlayersList().getDouble("players."+p.getName()+".strength");
			if ((s-loss)>=plugin.getConfig().getDouble("data.players.strengthMin")){
				plugin.files.getPlayersList().set("players."+p.getName()+".strength", s-loss);
			} else {
				plugin.files.getPlayersList().set("players."+p.getName()+".strength", plugin.getConfig().getDouble("data.players.strengthMin"));
			}
			plugin.files.savePlayersList();
			s = plugin.files.getPlayersList().getDouble("players."+p.getName()+".strength");
			double maxs = plugin.getConfig().getDouble("data.players.strengthMax");
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Your strength was dimished to &c"+s+" &7of &9"+maxs+" &7for your death."));
		}
	}

}
