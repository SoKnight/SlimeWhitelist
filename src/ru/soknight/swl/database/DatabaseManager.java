package ru.soknight.swl.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;

import ru.soknight.swl.SlimeWhitelist;
import ru.soknight.swl.utils.Logger;

public class DatabaseManager {
    
    public static void addProfile(PlayerProfile profile) {
		Database db = SlimeWhitelist.getInstance().getDatabase();
		String insert = "INSERT INTO players (player, time) VALUES (?, ?);";
		
		try {
			Connection connection = db.getConnection();
			
			PreparedStatement stm = connection.prepareStatement(insert);
			stm.setString(1, profile.getName());
			stm.setLong(2, profile.getTime());
			stm.executeUpdate();

			stm.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
    
    public static PlayerProfile getProfile(String name) {
		Database db = SlimeWhitelist.getInstance().getDatabase();
		String get = "SELECT * FROM players WHERE player=?;";
		
		PlayerProfile profile = null;
		
		try {
			Connection connection = db.getConnection();
			
			PreparedStatement stm = connection.prepareStatement(get);
			stm.setString(1, name);
			
			ResultSet output = stm.executeQuery();
			
			while(output.next()) {
				long time = output.getLong("time");
				profile = new PlayerProfile(name, time);
			}

			stm.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return profile;
	}
    
    public static List<PlayerProfile> getProfiles(int start, int end) {
		Database db = SlimeWhitelist.getInstance().getDatabase();
		String get = "SELECT * FROM players";
		
		List<PlayerProfile> profiles = new ArrayList<>();
		
		try {
			Connection connection = db.getConnection();
			PreparedStatement stm = connection.prepareStatement(get);
			
			ResultSet output = stm.executeQuery();
			int counter = 0;
			
			while(output.next()) {
				if(counter < start) continue;
				if(counter > end) break;
				
				String name = output.getString("player");
				long time = output.getLong("time");
				PlayerProfile profile = new PlayerProfile(name, time);
				profiles.add(profile);
				counter++;
			}

			stm.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return profiles;
	}
    
    public static int getProfilesCount() {
    	Database db = SlimeWhitelist.getInstance().getDatabase();
		String get = "SELECT * FROM players;";
		
		int count = 0;
		
		try {
			Connection connection = db.getConnection();
			PreparedStatement stm = connection.prepareStatement(get);
			
			ResultSet output = stm.executeQuery();
			
			while(output.next()) count++;
			
			stm.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return count;
    }
    
    public static boolean hasProfile(String name) {
    	Database db = SlimeWhitelist.getInstance().getDatabase();
		String search = "SELECT * FROM players WHERE player=?;";
		
		try {
			Connection connection = db.getConnection();
			
			PreparedStatement stm = connection.prepareStatement(search);
			stm.setString(1, name);
			
			ResultSet output = stm.executeQuery();
			
			int count = 0;
			while(output.next()) count++;
			
			stm.close();
			connection.close();
			
			if(count == 0) return false;
			else return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return false;
    }
    
    public static int purge(long limit) {
		Database db = SlimeWhitelist.getInstance().getDatabase();
		String select = "SELECT * FROM players WHERE time<=?;";
		String purge = "DELETE FROM players WHERE time<=?;";
		
		int count = 0;
		long needed = (System.currentTimeMillis() / 60000) - limit;
		
		try {
			Connection connection = db.getConnection();
			
			PreparedStatement selectstm = connection.prepareStatement(select);
			selectstm.setLong(1, needed);
			
			ResultSet output = selectstm.executeQuery();
			while(output.next()) {
				String name = output.getString("player");
				Bukkit.getOfflinePlayer(name).setWhitelisted(false);
				Logger.info("Player " + name + " removed from whitelist."); }
			selectstm.close();
			
			Bukkit.getServer().reloadWhitelist();
			
			PreparedStatement purgestm = connection.prepareStatement(purge);
			purgestm.setLong(1, needed);
			
			count = purgestm.executeUpdate();
			purgestm.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return count;
	}
    
    public static void removeProfile(String name) {
		Database db = SlimeWhitelist.getInstance().getDatabase();
		String delete = "DELETE FROM players WHERE player=?;";
		
		try {
			Connection connection = db.getConnection();
			
			PreparedStatement stm = connection.prepareStatement(delete);
			stm.setString(1, name);
			stm.executeUpdate();
			
			stm.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
    
    public static void updateProfile(String name, long time) {
		Database db = SlimeWhitelist.getInstance().getDatabase();
		String update = "UPDATE players SET time=? WHERE player=?;";
		
		try {
			Connection connection = db.getConnection();
			
			PreparedStatement stm = connection.prepareStatement(update);
			stm.setLong(1, time);
			stm.setString(2, name);
			stm.executeUpdate();
			
			stm.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
