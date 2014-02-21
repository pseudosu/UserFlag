package net.blockarray.flag.commands;

import java.sql.SQLException;

import net.blockarray.flag.Flag;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CmdFlag implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command command,
			String alias, String[] args) {

		if (args.length < 1) {
			sender.sendMessage(ChatColor.RED
					+ "Invalid syntax. Usage: /flag <user> or /flag clear <user>");
			return true;
		}
		if (!sender.hasPermission("flag.execute"))
			return true;

		String username = args[0].toLowerCase();

		if (args[0].equalsIgnoreCase("clear")
				&& sender.hasPermission("flag.clear")) {
			try {
				Flag.getNetwork().clear(username, sender.getName());

			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return true;
		}

		try {
			Flag.getNetwork().updateFlagCount("users", username, 1,
					sender.getName());

		} catch (SQLException e) {
			sender.sendMessage(ChatColor.RED
					+ "Something happend while updating the database, "
					+ e.getMessage());
			e.printStackTrace();
		}

		return false;
	}

}
