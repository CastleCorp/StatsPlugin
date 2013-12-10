package com.github.castlecorp.statsPlugin;

import java.util.Map;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class OnPlayerKillEvent implements Listener {
	Map<Player, Integer> map;

	public OnPlayerKillEvent(StatsPlugin plugin) {
		Player[] players = plugin.getOnlinePlayers();

		for( int i=0; i <= players.length; i++ ) {
			if(players.length > 0) {
				addPairs(players[i], 0);
			} else {
				/*
				 *  Because the same check is made in the OnPlayerDeathEvent class
				 *  and they are initialized at the same time, it is only necessary to log warnings
				 *  in one class. Logging is done in OnPlayerDeathEvent.
				 */

			}
		}
	}

	private void addPairs(Player name, Integer count) {
		map.put(name, count);
	}

	@EventHandler
	public void playerKillEvent(PlayerDeathEvent e) {

		Player dead = e.getEntity();
		Entity killer = dead.getKiller();

		if(killer instanceof Player) {
			Player killerName = (Player) killer;

			if(map.containsKey(killerName)) {
				Integer count = map.get(killerName);
				addPairs(killerName, count);
			} else {
				addPairs(killerName, 1);
			}
		} else {
			// don't need to do anything here
		}

	}

	public String getKillCount(Player player) {
		return ""+map.get(player);
	}


}
