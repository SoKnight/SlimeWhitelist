package ru.soknight.swl.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import ru.soknight.swl.database.DatabaseManager;
import ru.soknight.swl.database.PlayerProfile;
import ru.soknight.swl.files.Messages;
import ru.soknight.swl.utils.DateFormater;
import ru.soknight.swl.utils.Requirements;

public class CommandSettime {
    
    public static void execute(CommandSender sender, String[] args) {
    	if(Requirements.isInvalidUsage(sender, args, 3)) return;
        
        String name = args[1];
	    if(!Requirements.isOffline(sender, name)) return;
	    
	    List<String> whitelisted = new ArrayList<>();
	    Bukkit.getWhitelistedPlayers().forEach(p -> whitelisted.add(p.getName()));
	    
	    if(!whitelisted.contains(name)) {
	    	sender.sendMessage(Messages.getMessage("error-player-not-whitelisted").replace("%nickname%", name));
	    	return; }
        
	    long time;
	    try {
			time = Long.parseLong(args[2]);
		} catch (NumberFormatException e) {
			sender.sendMessage(Messages.getMessage("error-arg-is-not-int").replace("%arg%", args[2]));
			return; 
		}
        
        long current = System.currentTimeMillis() / 60000;
        long offline = current - time;

        String date = DateFormater.getFormatedTime(time);
        
        if(DatabaseManager.hasProfile(name))
        	DatabaseManager.updateProfile(name, offline);
        else DatabaseManager.addProfile(new PlayerProfile(name, offline));
        
        sender.sendMessage(Messages.getMessage("settime-success")
        		.replace("%time%", date)
        		.replace("%player%", name));
        return;
    }
    
}
