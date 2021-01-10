package com.projektskybox.skybrewdisable.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class SplashLingerDispenserListener implements Listener {
	
	Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("SkylasBrewingDisabler");
	
	@EventHandler
	public void onSplashDispense(BlockDispenseEvent event) {
		Block block = event.getBlock();
		ItemStack pot = event.getItem();
		boolean splashDispense = plugin.getConfig().getBoolean("splash-potions.disable-dispensers");
		boolean lingerDispense = plugin.getConfig().getBoolean("lingering-potions.disable-dispensers");
		if(block.getType() == Material.DISPENSER) {
			if(pot.getType() == Material.SPLASH_POTION) {
				if(splashDispense == true) {
					event.setCancelled(true);
				} else {
					event.setCancelled(false);
				}
			} else if (pot.getType() == Material.LINGERING_POTION) {
				if (lingerDispense == true) {
					event.setCancelled(true);
				} else {
					event.setCancelled(false);
				}
			}
		}
	}

}
