package ru.soknight.swl.files;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import ru.soknight.swl.SlimeWhitelist;
import ru.soknight.swl.utils.Logger;

public class Config {

	public static FileConfiguration config;
	public static String separator;
	public static long maxofflinetime;
	public static Map<String, String> units;
	
	public static void refresh() {
		SlimeWhitelist instance = SlimeWhitelist.getInstance();
		File datafolder = instance.getDataFolder();
		if(!datafolder.isDirectory()) datafolder.mkdirs();
		File file = new File(instance.getDataFolder(), "config.yml");
		if(!file.exists()) {
			try {
				Files.copy(instance.getResource("config.yml"), file.toPath(), StandardCopyOption.REPLACE_EXISTING);
				Logger.info("Generated new config file.");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		config = YamlConfiguration.loadConfiguration(file);
		
		separator = config.getString("date-formating.separator").replace("&", "\u00A7");
		maxofflinetime = config.getInt("max-offline-time") * 1440;
		units = new HashMap<>();
		ConfigurationSection section = config.getConfigurationSection("date-formating.units");
		
		for(String key : section.getKeys(false)) {
			String value = section.getString(key).replace("&", "\u00A7");
			units.put(key, value); }
	}
	
	public static List<String> getStringList(String section) {
		List<String> output = new ArrayList<>();
		for(String s : config.getStringList(section))
			output.add(s.replace("&", "\u00A7"));
		return output;
	}
	
}
