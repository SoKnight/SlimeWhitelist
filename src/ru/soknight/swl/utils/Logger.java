package ru.soknight.swl.utils;

import ru.soknight.swl.SlimeWhitelist;

public class Logger {
	
	public static void info(String info) {
		SlimeWhitelist.getInstance().getLogger().info(info);
	}
	
	public static void warning(String warning) {
		SlimeWhitelist.getInstance().getLogger().warning(warning);
	}
	
	public static void error(String error) {
		SlimeWhitelist.getInstance().getLogger().severe(error);
	}
	
}
