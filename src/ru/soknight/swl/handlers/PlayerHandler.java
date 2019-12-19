package ru.soknight.swl.handlers;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import ru.soknight.swl.database.DatabaseManager;
import ru.soknight.swl.database.PlayerProfile;

public class PlayerHandler implements Listener {
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onQuit(PlayerQuitEvent event) {
    	String name = event.getPlayer().getName();
    	
    	long current = System.currentTimeMillis() / 60000;
    	PlayerProfile profile = new PlayerProfile(name, current);
    	
    	if(DatabaseManager.hasProfile(name))
    		DatabaseManager.updateProfile(name, current);
    	else DatabaseManager.addProfile(profile);
        return;
    }

}
