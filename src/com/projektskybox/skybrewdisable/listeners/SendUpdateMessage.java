package com.projektskybox.skybrewdisable.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

import com.projektskybox.skybrewdisable.P;

public class SendUpdateMessage implements Listener {

	Plugin plugin = P.getInstance();

	@EventHandler
	public void onOpJoinEvent(PlayerJoinEvent event) {
		Player admin = event.getPlayer();

		if (admin.isOp()) {
			//tell admin to update plugin version if it is outdated
			if (P.updateAvailable == true) {
				admin.sendMessage(
						ChatColor.GOLD + "Sklyla's Brewing Disabler is outdated! Downnload the new version here: "
								+ "\n" + "https://www.spigotmc.org/resources/skylas-brewing-disabler.87836/");
				admin.sendMessage(ChatColor.GREEN + "Current version: " + P.currentVersion + "\n" + "Latest Version: " + P.newestVersion);
			}
			// tell admin to replace the config if the versions are mismatched
			if (P.mismatchedConfig == true) {
				admin.sendMessage(ChatColor.DARK_RED + "Mismatched Configs! Some features may not work properly! Please ask the server owner to update the config!");
			}
		}

	}

}
