package ru.soknight.swl.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import ru.soknight.swl.files.Messages;
import ru.soknight.swl.utils.Requirements;

public class SubCommands implements CommandExecutor, TabCompleter {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(args.length == 0) {
			sender.sendMessage(Messages.getMessage("error-without-args"));
			return true; }
		
		switch(args[0]) {
		case "help":
			if(Requirements.hasPermission(sender, "swl.help")) CommandHelp.execute(sender);
			break;
		case "gettime":
			if(Requirements.hasPermission(sender, "swl.gettime")) CommandGettime.execute(sender, args);
			break;
		case "list":
			if(Requirements.hasPermission(sender, "swl.list")) CommandList.execute(sender, args);
			break;
		case "purge":
			if(Requirements.hasPermission(sender, "swl.purge")) CommandPurge.execute(sender);
			break;
		case "reload":
			if(Requirements.hasPermission(sender, "swl.reload")) CommandReload.execute(sender);
			break;
		case "settime":
			if(Requirements.hasPermission(sender, "swl.settime")) CommandSettime.execute(sender, args);
			break;
		default:
			sender.sendMessage(Messages.getMessage("error-command-not-found"));
			break;
		}
		return true;
	}
	
	public static final List<String> subcommands = Arrays.asList("help", "gettime", "list", "purge", "reload", 
			"settime");
	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		if(args.length == 0) return null;
		List<String> output = new ArrayList<>();
		if(args.length == 1) {
			for(String s : subcommands)
				if(s.startsWith(args[0].toLowerCase())) 
					output.add(s);
			return output;
		}
		
		switch(args[0]) {
		case "gettime":
			if(args.length == 2)
				output.addAll(getPlayers(args[1].toLowerCase()));
			break;
		case "settime":
			if(args.length == 2)
				output.addAll(getPlayers(args[1].toLowerCase()));
			break;
		default:
			break;
		}
		return output;
	}
	
	private List<String> getPlayers(String arg) {
		List<String> output = new ArrayList<>();
		String s = arg.toLowerCase();
		for(OfflinePlayer op : Bukkit.getWhitelistedPlayers())
			if(op.getName().toLowerCase().startsWith(s)) output.add(op.getName());
		return output;
	}

}
