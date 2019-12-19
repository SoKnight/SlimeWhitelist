package ru.soknight.swl.commands;

import java.util.List;

import org.bukkit.command.CommandSender;

import ru.soknight.swl.database.DatabaseManager;
import ru.soknight.swl.database.PlayerProfile;
import ru.soknight.swl.files.Messages;
import ru.soknight.swl.utils.DateFormater;
import ru.soknight.swl.utils.Requirements;

public class CommandList {
    
    public static void execute(CommandSender sender, String[] args) {
        int page = 1;
        if(args.length > 1) {
        	if(!Requirements.argIsInteger(sender, args[1])) return;
        	else page = Integer.parseInt(args[1]); }

        int total = DatabaseManager.getProfilesCount();
        int maxpage = total / 10;
        if((total % 10) != 0) maxpage++;

        if(page > maxpage) page = maxpage;

        int start = (page - 1) * 10, end = start + 9;
        List<PlayerProfile> profiles = DatabaseManager.getProfiles(start, end);
        if(profiles.isEmpty()) {
            sender.sendMessage(Messages.getMessage("list-failed-empty"));
            return;
        }
        
        String header = Messages.getMessage("list-header")
                .replace("%cur%", String.valueOf(page))
                .replace("%max%", String.valueOf(maxpage));
        String body = Messages.getMessage("list-body");
        String footer = Messages.getMessage("list-footer");
        
        sender.sendMessage(header);
        profiles.forEach(p -> {
        	String date = DateFormater.getFormatedTime(p.getTime());
        	sender.sendMessage(body.replace("%player%", p.getName()).replace("%time%", date));
        });
        sender.sendMessage(footer);
        return;
    }
    
}
