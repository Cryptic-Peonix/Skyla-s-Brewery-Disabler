package com.projektskybox.skybrewdisable.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.BrewingStandFuelEvent;
import org.bukkit.plugin.Plugin;

/**
 * @author Skyla
 * 
 * Listener that enables/disables the fueling of brewing stands
 * Reads the config every time the event occurs and allows/denies it
 */
public class BrewListener implements Listener {
	
	Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("SkylasBrewingDisabler");
	
	@EventHandler
	public void onBrewEvent(BrewingStandFuelEvent event) {
		boolean make = plugin.getConfig().getBoolean("brewing.enabled");
		if (make == false) {
			event.setCancelled(true);
		} else {
			event.setCancelled(false);
		}
	}

}
