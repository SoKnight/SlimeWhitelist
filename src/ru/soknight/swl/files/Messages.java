package ru.soknight.swl.files;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import ru.soknight.swl.SlimeWhitelist;
import ru.soknight.swl.utils.HelpCommand;
import ru.soknight.swl.utils.Logger;

public class Messages {

	public static FileConfiguration config;
	private static Map<String, HelpCommand> help_list;
	private static Set<String> keys = new HashSet<>();
	
	public static void refresh() {
		SlimeWhitelist instance = SlimeWhitelist.getInstance();
		File datafolder = instance.getDataFolder();
		if(!datafolder.isDirectory()) datafolder.mkdirs();
		File file = new File(instance.getDataFolder(), "messages.yml");
		if(!file.exists()) {
			try {
				Files.copy(instance.getResource("messages.yml"), file.toPath(), StandardCopyOption.REPLACE_EXISTING);
				Logger.info("Generated new messages file.");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		config = YamlConfiguration.loadConfiguration(file);
		keys = config.getKeys(true);
		initHelpList();
	}
	
	public static String getMessage(String section) {
		if(!keys.contains(section)) {
			Logger.error("Couldn't load message from messages.yml: " + section);
			return "Whoops! Message not found :(";
		}
		String output = config.getString(section).replace("&", "\u00A7");
		return output;
	}
	
	public static HelpCommand getHelpString(String key) {
		return help_list.get(key);
	}
	
	public static List<String> getStringList(String key) {
		List<String> output = new ArrayList<>();
		for(String s : config.getStringList(key))
			output.add(s.replace("&", "\u00A7"));
		return output;
	}
	
	public static String format;
	
	private static void initHelpList() {
		format = getMessage("help-body");
		help_list = new HashMap<>();
		
		HelpCommand 
			help = new HelpCommand("help"),
			gettime = new HelpCommand("gettime", "player"),
			list = new HelpCommand("list"),
			purge = new HelpCommand("purge"),
			reload = new HelpCommand("reload"),
			settime = new HelpCommand("settime", "player", "time");
		
		help_list.put("help", help);
		help_list.put("gettime", gettime);
		help_list.put("list", list);
		help_list.put("purge", purge);
		help_list.put("reload", reload);
		help_list.put("settime", settime);
	}
	
}
