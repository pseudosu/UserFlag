package net.blockarray.flag.network;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import net.blockarray.flag.Flag;
import net.blockarray.flag.Queries;
import net.blockarray.flag.Util;

public class Network {

	private String host;
	private String database;
	private String username;
	private String password;
	private Connection c;

	public Network(String host, String database, String username,
			String password) {
		this.host = host;
		this.database = database;
		this.username = username;
		this.password = password;
	}

	public boolean connect() {
		try {
			c = DriverManager.getConnection("jdbc:mysql://" + host + "/"
					+ database + "?" + "user=" + username + "&password="
					+ password);

			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	public boolean setupDatabases() {

		try {
			PreparedStatement ps = c.prepareStatement(
					Queries.SETUP_DATBASES);
			ps.executeUpdate();

			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	public void clear(String username, String sender) throws SQLException {

		PreparedStatement ps = Flag.getConnection().prepareStatement(
				Queries.CLEAR);
		ps.setString(1, username);

		ps.executeUpdate();

		Util.sendStaff("&6Player &c" + sender + " &6cleared &c" + username
				+ "&6's flags.");
	}

	public boolean updateFlagCount(String table, String username, int amount,
			String sender) throws SQLException {

		Statement s = Flag.getConnection().createStatement();

		ResultSet userData = getUserData(username);

		if (userData.next()) {
			int flagCount = userData.getInt("flagAmount") + amount;

			PreparedStatement ps = Flag.getConnection().prepareStatement(
					Queries.UPDATE_FLAG_DATA);
			ps.setInt(1, flagCount);
			ps.setString(2, username);

			ps.executeUpdate();

			Util.sendStaff("&6Player &c" + sender + " &6flagged the user &c"
					+ username + "&6, the user now has &c"
					+ Flag.getNetwork().getTotalFlags(username)
					+ "&6 total flags. Keep an eye out!");

			return true;
		} else {
			s.executeUpdate("INSERT INTO users(`username`, `flagAmount`) VALUES('"
					+ username + "', '" + "1')");

			return true;
		}
	}

	public ResultSet getUserData(String username) throws SQLException {

		PreparedStatement ps = Flag.getConnection().prepareStatement(
				Queries.GET_USER_DATA);
		ps.setString(1, username);

		return ps.executeQuery();
	}

	public int getTotalFlags(String username) throws SQLException {
		ResultSet res = getUserData(username);
		if (res.next())
			return res.getInt("flagAmount");
		return -1;
	}

	public Connection getConnection() {
		return c;
	}
}
