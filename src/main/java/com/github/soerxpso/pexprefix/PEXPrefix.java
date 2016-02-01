package com.github.soerxpso.pexprefix;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

import ru.tehkode.permissions.bukkit.PermissionsEx;

public final class PEXPrefix extends JavaPlugin implements Listener {

	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		e.setFormat("[" + PermissionsEx.getUser(e.getPlayer()).getPrefix() + "]<%1$s>%2$s");
	}
}
