package net.blockarray.flag.listeners;

import java.sql.SQLException;

import net.blockarray.flag.Flag;
import net.blockarray.flag.Util;
import net.blockarray.flag.network.Network;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class onLogin implements Listener {
	@EventHandler
	public void onLog(PlayerLoginEvent event) {

		String username = event.getPlayer().getName();
		Network network = Flag.getNetwork();

		try {
			if (network.getUserData(event.getPlayer().getName().toLowerCase()) != null) {
				if (network.getTotalFlags(username) <= 0)
					return;
				Util.sendStaff("&6Player &c" + event.getPlayer().getName()
						+ "&6 has been flagged &c"
						+ network.getTotalFlags(username)
						+ "&6 times, look out.");
			} else {

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
