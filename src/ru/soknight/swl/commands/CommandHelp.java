package ru.soknight.swl.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.CommandSender;

import ru.soknight.swl.files.Messages;
import ru.soknight.swl.utils.HelpCommand;

public class CommandHelp {
    
    public static void execute(CommandSender sender) {
    	String header = Messages.getMessage("help-header");
		String footer = Messages.getMessage("help-footer");
		List<HelpCommand> body = new ArrayList<>();
		
		body.add(Messages.getHelpString("help"));
		if(sender.hasPermission("swl.gettime")) 		body.add(Messages.getHelpString("gettime"));
		if(sender.hasPermission("swl.list")) 			body.add(Messages.getHelpString("list"));
		if(sender.hasPermission("swl.purge")) 			body.add(Messages.getHelpString("purge"));
		if(sender.hasPermission("swl.reload")) 			body.add(Messages.getHelpString("reload"));
		if(sender.hasPermission("swl.settime")) 		body.add(Messages.getHelpString("settime"));
		
		sender.sendMessage(header);
		body.forEach(str -> sender.sendMessage(str.toString()));
		sender.sendMessage(footer);
        return;
    }
    
}
