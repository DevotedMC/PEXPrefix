package com.github.soerxpso.pexprefix;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

import ru.tehkode.permissions.bukkit.PermissionsEx;

public final class PEXPrefix extends JavaPlugin implements Listener {

	FileConfiguration config;
	String prefixFormat = "[%3$s] %1$s: %2$s", normalFormat = "<%1$s> %2$s";
	
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
		setupConfig();
	}
	
	private void setupConfig() {
		config = getConfig();
		if(config.contains("prefix")) {
			prefixFormat = config.getString("prefix");
		}
		if(config.contains("normal")) {
			prefixFormat = config.getString("normal");
		}
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onChat(AsyncPlayerChatEvent e) {
		if(PermissionsEx.getUser(e.getPlayer()).getPrefix() != "") {
			e.setFormat(prefixFormat.replace("%3$s", PermissionsEx.getUser(e.getPlayer()).getPrefix()));
		}else {
			e.setFormat(normalFormat);
		}
		System.out.println(e.getFormat());
	}
}
