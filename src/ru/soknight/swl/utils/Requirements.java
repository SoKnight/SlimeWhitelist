package ru.soknight.swl.utils;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ru.soknight.swl.database.DatabaseManager;
import ru.soknight.swl.files.Messages;

public class Requirements {
	
	public static boolean hasPermission(CommandSender sender, String permission) {
		if(!sender.hasPermission(permission)) {
			sender.sendMessage(Messages.getMessage("error-no-permission"));
			return false;
		} else return true;
	}
	
	public static boolean isOffline(CommandSender sender, String name) {
		if(Bukkit.getOfflinePlayer(name).isOnline()) {
			sender.sendMessage(Messages.getMessage("error-player-online").replace("%nickname%", name));
			return false;
		} else return true;
	}
	
	public static boolean isInDatabase(CommandSender sender, String name) {
		if(!DatabaseManager.hasProfile(name)) {
			sender.sendMessage(Messages.getMessage("error-profile-not-found").replace("%nickname%", name));
			return false;
		} else return true;
	}
	
	public static boolean playerExist(CommandSender sender, String name) {
		for(Player p : Bukkit.getOnlinePlayers())
			if(p.getName().equals(name)) return true;
		for(OfflinePlayer op : Bukkit.getOfflinePlayers())
			if(op.getName().equals(name)) return true;
		sender.sendMessage(Messages.getMessage("error-player-not-found").replace("%nickname%", name));
		return false;
	}
	
	public static boolean isInvalidUsage(CommandSender sender, String[] args, int neededargscount) {
		if(args.length < neededargscount) {
			sender.sendMessage(Messages.getMessage("error-invalid-syntax"));
			return true;
		} else return false;
	}
	
	public static boolean argIsInteger(CommandSender sender, String arg) {
		try {
			Integer.parseInt(arg);
			return true;
		} catch (NumberFormatException e) {
			sender.sendMessage(Messages.getMessage("error-arg-is-not-int").replace("%arg%", arg));
			return false;
		}
	}
	
}
