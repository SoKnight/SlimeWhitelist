package ru.soknight.swl.database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.configuration.ConfigurationSection;

import ru.soknight.swl.SlimeWhitelist;
import ru.soknight.swl.files.Config;
import ru.soknight.swl.utils.Logger;

public class Database {

	static String database_type;
	static String database_url;
	static String mysql_host;
	static String mysql_name;
	static String mysql_user;
	static String mysql_password;
	static String sqlite_file;
	static int mysql_port;
	
	public Database() throws Exception {
		ConfigurationSection section = Config.config.getConfigurationSection("database");
		database_type = section.getString("type");
		if(database_type.equals("mysql")) {
			mysql_host = section.getString("host");
			mysql_name = section.getString("name");
			mysql_user = section.getString("user");
			mysql_password = section.getString("password");
			mysql_port = section.getInt("port");
			database_url = "jdbc:mysql://" + mysql_host + ":" + mysql_port + "/" + mysql_name;
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} else {
			sqlite_file = section.getString("file");
			database_url = "jdbc:sqlite:" + SlimeWhitelist.getInstance().getDataFolder() + File.separator + sqlite_file;
			Class.forName("org.sqlite.JDBC").newInstance();
		}
		
		Connection connection = getConnection();
		Statement s = connection.createStatement();
		
		s.executeUpdate("CREATE TABLE IF NOT EXISTS players (player TEXT, time LONG);");
		
		s.close();
		connection.close();
		Logger.info("Database type " + database_type + " connected!");
	}
	
	public Connection getConnection() throws SQLException {
		if(database_type.equals("mysql"))
			return DriverManager.getConnection(database_url, mysql_user, mysql_password);
		else return DriverManager.getConnection(database_url);
	}
	
}
