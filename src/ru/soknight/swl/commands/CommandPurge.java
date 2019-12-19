package ru.soknight.swl.commands;

import org.bukkit.command.CommandSender;

import ru.soknight.swl.database.DatabaseManager;
import ru.soknight.swl.files.Config;
import ru.soknight.swl.files.Messages;

public class CommandPurge {
    
    public static void execute(CommandSender sender) {
        int purged = DatabaseManager.purge(Config.maxofflinetime);
        sender.sendMessage(Messages.getMessage("purge-success").replace("%count%", String.valueOf(purged)));
        return;
    }
    
}
