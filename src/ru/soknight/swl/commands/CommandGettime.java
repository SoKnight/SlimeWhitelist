package ru.soknight.swl.commands;

import org.bukkit.command.CommandSender;

import ru.soknight.swl.database.DatabaseManager;
import ru.soknight.swl.files.Messages;
import ru.soknight.swl.utils.DateFormater;
import ru.soknight.swl.utils.Requirements;

public class CommandGettime {
    
    public static void execute(CommandSender sender, String[] args) {
		if(Requirements.isInvalidUsage(sender, args, 2)) return;
        
        String name = args[1];
	    if(!Requirements.isOffline(sender, name)) return;
	    if(!Requirements.isInDatabase(sender, name)) return;
        
        long current = System.currentTimeMillis() / 60000;
        long offline = DatabaseManager.getProfile(name).getTime();

        long time = current - offline;
        String date = DateFormater.getFormatedTime(time);
        
        sender.sendMessage(Messages.getMessage("gettime-success")
        		.replace("%player%", name)
        		.replace("%time%", date));
        return;
    }
    
}
