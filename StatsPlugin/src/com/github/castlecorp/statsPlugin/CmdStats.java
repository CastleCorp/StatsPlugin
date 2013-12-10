package com.github.castlecorp.statsPlugin;

import java.util.logging.Logger;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class CmdStats implements CommandExecutor {

	private static final Logger log = Logger.getLogger("Stats: ");
	
	private StatsPlugin plugin;
	public Economy econ = null;
	public Chat chat = null;
	private OnPlayerDeathEvent deaths;
	private OnPlayerKillEvent kills;



	public CmdStats(StatsPlugin plugin, Economy econ, OnPlayerDeathEvent deaths, OnPlayerKillEvent kills) {
		this.plugin = plugin;
		this.econ = econ;
		this.deaths = deaths;
		this.kills = kills;

	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if(sender.hasPermission("stats.view")) {
			if( sender instanceof Player) {
			// use plugin.msg method to send the player the death count, kill count and balance.
			String player = sender.getName();
			EconomyResponse playerBalance = econ.bankBalance(player);
			String balance = playerBalance.toString();



			// message sender their stats
			String deathCount = deaths.getDeathCount(player);
			String killCount = kills.getKillCount(player);


			plugin.Msg(sender, balance);
			plugin.Msg(sender, deathCount);
			plugin.Msg(sender, killCount);


			} else if(!(sender instanceof Player)) {
				String prefix = "[@Stats] ";
				log.severe(prefix+"Uh oh... It looks like you are trying to run the stats command from the console");
				log.severe(prefix+"Only players can run the stats command! :[");
				return true;
			}
		}

		return false;

	}
}
