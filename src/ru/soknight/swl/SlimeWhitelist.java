package ru.soknight.swl;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import ru.soknight.swl.commands.SubCommands;
import ru.soknight.swl.database.Database;
import ru.soknight.swl.files.Config;
import ru.soknight.swl.files.Messages;
import ru.soknight.swl.handlers.PlayerHandler;
import ru.soknight.swl.handlers.PurgeTask;
import ru.soknight.swl.utils.Logger;

public class SlimeWhitelist extends JavaPlugin {

	private static SlimeWhitelist instance;
	private Database database;
	
	@Override
	public void onEnable() {
		instance = this;
		if(!Bukkit.hasWhitelist()) {
			Logger.info("Plugin work only with whitelist mode!");
			Bukkit.getPluginManager().disablePlugin(this);
			return;
		}
		
		Config.refresh();
		Messages.refresh();
		
		// Init database
		try {
			database = new Database();
		} catch (Exception e) {
			e.printStackTrace();
			Bukkit.getPluginManager().disablePlugin(this);
			return;
		}
		
		// Register listeners and executors
		getCommand("swl").setExecutor(new SubCommands());
		getCommand("swl").setTabCompleter(new SubCommands());
		
 		getServer().getPluginManager().registerEvents(new PlayerHandler(), this);
 		
 		// Launching purge task
 		long period = Config.config.getLong("purge-period") * 1200;
 		Bukkit.getScheduler().runTaskTimer(instance, new PurgeTask(), 20, period);
 		
		Logger.info("Enabled!");
	}
	
	public static SlimeWhitelist getInstance() {
		return instance;
	}

	public Database getDatabase() {
		return database;
	}
	
}
