package ru.soknight.swl.commands;

import org.bukkit.command.CommandSender;

import ru.soknight.swl.files.Config;
import ru.soknight.swl.files.Messages;

public class CommandReload {
    
    public static void execute(CommandSender sender) {
        Config.refresh();
        Messages.refresh();
        sender.sendMessage(Messages.getMessage("reload-success"));
        return;
    }
        
}
