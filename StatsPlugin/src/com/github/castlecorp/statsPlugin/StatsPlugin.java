package com.github.castlecorp.statsPlugin;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class StatsPlugin extends JavaPlugin implements Listener {
	
	public String version;
	
	public StatsPlugin() {
		
	}
	
	public void logger(String msg) {
		getLogger().info("[@Stats] "+msg);
	}
	
	public void Msg (CommandSender sender, String msg) {

		if(sender instanceof Player) {
			sender.sendMessage(ChatColor.RED+"[@Stats] "+ChatColor.AQUA+msg);

		} else logger(msg);
	}

	@Override
	public void onEnable() {
		version = getDescription().getVersion();
	}
	
	@Override
	public void onDisable() {

		// Do nothing...

	}
	
	public void loadConfiguration() {

		org.bukkit.configuration.file.FileConfiguration config = getConfig();

		//Paths and Value pairs
	
		
		
		// End path and value pairs

		config.options().copyDefaults(true);

		saveConfig();

	}
	
}
