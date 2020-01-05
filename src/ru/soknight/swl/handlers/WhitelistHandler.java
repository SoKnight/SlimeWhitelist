package ru.soknight.swl.handlers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

import ru.soknight.swl.utils.Logger;

public class WhitelistHandler implements Listener {
	
	@EventHandler
	public void onAsyncPreLogin(AsyncPlayerPreLoginEvent event) {
		String name = event.getName();
		UUID uuid = event.getUniqueId();
		if(event.getLoginResult().equals(org.bukkit.event.player.AsyncPlayerPreLoginEvent.Result.KICK_WHITELIST)) {
			List<String> whitelisted = new ArrayList<>();
			Bukkit.getWhitelistedPlayers().forEach(p -> whitelisted.add(p.getName().toLowerCase()));
			if(whitelisted.contains(name.toLowerCase())) {
				event.setLoginResult(org.bukkit.event.player.AsyncPlayerPreLoginEvent.Result.ALLOWED);
				Logger.info(name + " logged in using whitelist fix by SoKnight. (AsyncPreLogin)");
				return;
			}
			List<UUID> uuids = new ArrayList<>();
			Bukkit.getWhitelistedPlayers().forEach(p -> uuids.add(p.getUniqueId()));
			if(uuids.contains(uuid)) {
				event.setLoginResult(org.bukkit.event.player.AsyncPlayerPreLoginEvent.Result.ALLOWED);
				Logger.info(uuid + " (" + name + ") logged in using whitelist fix by SoKnight. (AsyncPreLogin)");
				return;
			}
			Logger.info(uuid + " (" + name + ") denied login. (AsyncPreLogin)");
		}
	}
	
	@EventHandler
	public void onLogin(PlayerLoginEvent event) {
		String name = event.getPlayer().getName();
		UUID uuid = event.getPlayer().getUniqueId();
		if(event.getResult().equals(Result.KICK_WHITELIST)) {
			List<String> whitelisted = new ArrayList<>();
			Bukkit.getWhitelistedPlayers().forEach(p -> whitelisted.add(p.getName().toLowerCase()));
			if(whitelisted.contains(name.toLowerCase())) { 
				event.setResult(Result.ALLOWED); 
				Logger.info(name + " logged in using whitelist fix by SoKnight. (Login)");
				return; }
			List<UUID> uuids = new ArrayList<>();
			Bukkit.getWhitelistedPlayers().forEach(p -> uuids.add(p.getUniqueId()));
			if(uuids.contains(uuid)) {
				event.setResult(Result.ALLOWED);
				Logger.info(uuid + " (" + name + ") logged in using whitelist fix by SoKnight. (Login)");
				return;
			}
			Logger.info(uuid + " (" + name + ") denied login. (Login)");
		}
	}
	
}
