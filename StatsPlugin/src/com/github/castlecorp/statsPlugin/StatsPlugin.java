package com.github.castlecorp.statsPlugin;

import java.util.logging.Logger;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;



// TODO: PlayerDeathEvent listener class (make a variable for all players on server using arraylist iterator, add) as well as listener classes for kills.
// plugin.yml


public final class StatsPlugin extends JavaPlugin implements Listener {

	public static Economy econ = null;

	@SuppressWarnings("unused")
	private CmdStats statsCmd;
	private OnPlayerDeathEvent death;
	private OnPlayerKillEvent kills;

	public String version;

	private static final Logger log = Logger.getLogger("Stats: ");

	public StatsPlugin() {
	}

	public void logger(String msg) {
		getLogger().info("[@Stats] "+msg);
	}


	@Override
	public void onDisable() {

		// Do nothing...

	}


	@Override
	public void onEnable() {
		version = getDescription().getVersion();

		setupEconomy();

		if(!setupEconomy()) {
			log.severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
			getServer().getPluginManager().disablePlugin(this);
			return;
		}



		getCommand("stats").setExecutor(new CmdStats(this, econ, death, kills));

		Bukkit.getServer().getPluginManager().registerEvents(new OnPlayerDeathEvent(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new OnPlayerKillEvent(this), this);
	}



	private boolean setupEconomy() {
		if (getServer().getPluginManager().getPlugin("Vault") == null) {
			return false;
		}
		RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
		if (rsp == null) {
			return false;
		}
		econ = rsp.getProvider();
		return econ != null;
	}



	public void loadConfiguration() {

		org.bukkit.configuration.file.FileConfiguration config = getConfig();

		//Paths and Value pairs



		// End path and value pairs

		config.options().copyDefaults(true);

		saveConfig();

	}

	public void Msg(CommandSender sender, String msg) {

		if(sender instanceof Player) {
			sender.sendMessage(ChatColor.RED+"[@Stats] "+ChatColor.AQUA+msg);

		}
	}

	public Player[] getOnlinePlayers() {
		return Bukkit.getOnlinePlayers();
	}
}
