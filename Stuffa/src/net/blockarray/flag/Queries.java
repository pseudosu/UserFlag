package net.blockarray.flag;

public class Queries {
	public static final String CLEAR = "DELETE FROM users WHERE username = ?";
	public static final String UPDATE_FLAG_DATA = "UPDATE users SET `flagAmount` = ? WHERE username = ? AND flagAmount >= 0";
	public static final String GET_USER_DATA = "SELECT * FROM users WHERE username = ?";
	public static final String SETUP_DATBASES = "CREATE TABLE IF NOT EXISTS users (username VARCHAR(255), flagAmount int)";
}
