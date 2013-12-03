package com.github.castlecorp.statsPlugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CmdStats implements CommandExecutor {

	private StatsPlugin plugin;
	
	public CmdStats(StatsPlugin plugin) {
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		
		
		return false;
		
	}

}
