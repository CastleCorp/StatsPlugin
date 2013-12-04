package com.github.castlecorp.statsPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class OnPlayerDeathEvent implements Listener {
	Map<Player, Integer> map;
	//Player[] name = Bukkit.getOnlinePlayers();
	private static final Logger log = Logger.getLogger("Stats: ");

	public OnPlayerDeathEvent(StatsPlugin plugin) {
		this.map = new  HashMap<Player, Integer>();
		Player[] players = plugin.getOnlinePlayers();

		for(int i=0; i <= players.length; i++) {
			if( players.length > 0 ){
				addPairs(players[i], 0);
			} else {
				String prefix = "[@Stats] ";

				log.severe(prefix+"There are no players online currently, and therefore, there are no stats for the plugin to log");
				log.severe(prefix+"The plugin will automatically begin logging stats when a player death occurs.");
				log.severe(prefix+"The stats command should still be functional (there just won't be much to see!) :]");
			}
		}
	}

	public void addPairs( Player name, Integer count ) {
		map.put(name, count);
	}

	@EventHandler
	public void playerDeathEvent(PlayerDeathEvent e) {
		Player deadName = e.getEntity();

		if(map.containsKey(deadName)) {
			Integer count = map.get(deadName); 
			addPairs(deadName, count += 1);
		} else {
			addPairs(deadName, 1);
		}

	}

	public String getDeathCount(CommandSender sender) {
		return ""+map.get(sender);
	}

}
