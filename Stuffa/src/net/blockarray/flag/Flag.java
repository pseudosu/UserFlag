package net.blockarray.flag;

import java.sql.Connection;

import net.blockarray.flag.commands.CmdFlag;
import net.blockarray.flag.listeners.onLogin;
import net.blockarray.flag.network.Network;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Flag extends JavaPlugin {

	private static Network network;

	public void onEnable() {

		setupConfig();

		network = new Network("localhost", getConfig().getString("database"),
				getConfig().getString("username"), getConfig().getString(
						"password"));
		network.connect();
		network.setupDatabases();
		getCommand("flag").setExecutor(new CmdFlag());
		Bukkit.getServer().getPluginManager()
				.registerEvents(new onLogin(), this);

		// Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new
		// Broadcast(),
		// 0, 12000);
	}

	public void setupConfig() {
		getConfig().options().copyDefaults(true);

		getConfig().addDefault("database", "smp_flags");
		getConfig().addDefault("username", "root");
		getConfig().addDefault("password", "password");

		saveConfig();
	}

	public static Network getNetwork() {
		return network;
	}

	public static Connection getConnection() {
		return network.getConnection();
	}

	public void onDisable() {

	}
}
