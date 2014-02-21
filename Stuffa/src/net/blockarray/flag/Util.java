package net.blockarray.flag;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Util {
	public static void sendStaff(String message) {
		for (Player p : Bukkit.getServer().getOnlinePlayers()) {
			if (p.hasPermission("flag.admin")) {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&',
						message));
			}
		}
	}
}
