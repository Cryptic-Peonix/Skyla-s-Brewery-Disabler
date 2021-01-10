package com.projektskybox.skybrewdisable.listeners;

import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class LingerSplaListener implements Listener {

	Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("SkylasBrewingDisabler");

	// DO NOT SEPERATE THESE INTO TWO CLASSES, SPIGOT DOES NOT LIKE IT

	@EventHandler
	public void onLingerEvent(PlayerInteractEvent event) {
		boolean splashEnable = plugin.getConfig().getBoolean("splash-potions.enabled");
		boolean lingerEnable = plugin.getConfig().getBoolean("lingering-potions.enabled");
		boolean splashDelete = plugin.getConfig().getBoolean("splash-potions.delete-item");
		boolean lingerDelete = plugin.getConfig().getBoolean("lingering-potions.delete-item");

		Random random = new Random();

		List<String> splashDeny = plugin.getConfig().getStringList("splash-potions.deny-use-messages");
		List<String> lingerDeny = plugin.getConfig().getStringList("lingering-potions.deny-use-messages");
		String splashMessage = splashDeny.get(random.nextInt(splashDeny.size()));
		String lingerMessage = lingerDeny.get(random.nextInt(lingerDeny.size()));

		String splashDeleteMessage = plugin.getConfig().getString("splash-potions.item-deletion-message");
		String lingerDeleteMessage = plugin.getConfig().getString("lingering-potions.item-deletion-message");
		if (!(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)) {
			return;
		} else {
			ItemStack isMain = event.getPlayer().getInventory().getItemInMainHand();
			ItemStack isOff = event.getPlayer().getInventory().getItemInOffHand();
			// Lingering
			if (isMain.getType() == Material.LINGERING_POTION) {
				if (lingerEnable == false) {
					event.setCancelled(true);
					event.getPlayer().sendMessage(lingerMessage);
					if (lingerDelete == true) {
						int slot = event.getPlayer().getInventory().getHeldItemSlot();
						event.getPlayer().getInventory().setHeldItemSlot(slot);
						event.getPlayer().getInventory().setItemInMainHand(new ItemStack(Material.AIR));
						event.getPlayer().sendMessage(ChatColor.RED + lingerDeleteMessage);
					}
				}
			} else if (isMain.getType() == Material.SPLASH_POTION) {
				if (splashEnable == false) {
					event.setCancelled(true);
					event.getPlayer().sendMessage(splashMessage);
					if (splashDelete == true) {
						int slot = event.getPlayer().getInventory().getHeldItemSlot();
						event.getPlayer().getInventory().setHeldItemSlot(slot);
						event.getPlayer().getInventory().setItemInMainHand(new ItemStack(Material.AIR));
						event.getPlayer().sendMessage(ChatColor.RED + splashDeleteMessage);
					}
				}
			} else if (isOff.getType() == Material.LINGERING_POTION) {
				if (lingerEnable == false) {
					event.setCancelled(true);
					event.getPlayer().sendMessage(lingerMessage);
					if (lingerDelete == true) {
						int slot = event.getPlayer().getInventory().getHeldItemSlot();
						event.getPlayer().getInventory().setHeldItemSlot(slot);
						event.getPlayer().getInventory().setItemInOffHand(new ItemStack(Material.AIR));
						event.getPlayer().sendMessage(ChatColor.RED + lingerDeleteMessage);
					}
				}
			} else if (isOff.getType() == Material.SPLASH_POTION) {
				if (splashEnable == false) {
					event.setCancelled(true);
					event.getPlayer().sendMessage(splashMessage);
					if (splashDelete == true) {
						int slot = event.getPlayer().getInventory().getHeldItemSlot();
						event.getPlayer().getInventory().setHeldItemSlot(slot);
						event.getPlayer().getInventory().setItemInOffHand(new ItemStack(Material.AIR));
						event.getPlayer().sendMessage(ChatColor.RED + splashDeleteMessage);
					}
				}
			} 
		}
	}

}
