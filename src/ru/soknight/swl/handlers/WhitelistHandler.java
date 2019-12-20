package ru.soknight.swl.handlers;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

import ru.soknight.swl.utils.Logger;

public class WhitelistHandler implements Listener {
	
	@EventHandler
	public void onLogin(PlayerLoginEvent event) {
		String name = event.getPlayer().getName();
		if(event.getResult().equals(Result.KICK_WHITELIST)) {
			Logger.info(name + " logged in using whitelist fix by SoKnight.");
			List<String> whitelisted = new ArrayList<>();
			Bukkit.getWhitelistedPlayers().forEach(p -> whitelisted.add(p.getName().toLowerCase()));
			if(whitelisted.contains(name.toLowerCase())) event.setResult(Result.ALLOWED);
		}
	}
	
}
