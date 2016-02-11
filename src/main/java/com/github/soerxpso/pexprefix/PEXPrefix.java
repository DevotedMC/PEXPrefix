package com.github.soerxpso.pexprefix;

import org.bukkit.ChatColor;
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
			normalFormat = config.getString("normal");
		}
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onChat(AsyncPlayerChatEvent e) {
		getLogger().info("PEXPrefix caught chat event: " + e.getMessage());
		if(PermissionsEx.getUser(e.getPlayer()).getPrefix() != "") {
			String format = prefixFormat.replace("%3$s", PermissionsEx.getUser(e.getPlayer()).getPrefix());
			if (config.getBoolean("convertFormat", false)) {
				e.setFormat(doColors(format));
			}
		}else {
			if (config.getBoolean("convertFormat", false)) {
				e.setFormat(doColors(normalFormat));
			}
		}
		if (config.getBoolean("convertMessage", false)) {
			e.setMessage(doColors(e.getMessage()));
		}
	}

	public String doColors(String str) {
		return str.replace('&', ChatColor.COLOR_CHAR);
	}
}
