package net.blockarray.flag;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.bukkit.Achievement;
import org.bukkit.Bukkit;

public class Broadcast implements Runnable {

	public void run() {

		ArrayList<String> users = new ArrayList<String>();

		try {
			ResultSet res = Flag.getConnection().createStatement()
					.executeQuery("SELECT * FROM users");

			while (res.next()) {
				if (users.contains(Bukkit.getPlayer(res.getString(1))))
					continue;
				users.add(res.getString(1));
			}
			for (String s : users) {
				Bukkit.getServer().broadcastMessage(s);
				continue;
				// if (p.isOnline())
				// Util.sendStaff("Player "
				// + p.getName()
				// + " has a flag level of "
				// + Flag.getNetwork().getTotalFlags(
				// p.getName().toLowerCase()) + ", watch out!");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
