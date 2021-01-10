package com.projektskybox.skybrewdisable;

import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import com.projektskybox.skybrewdisable.commands.DisableBrew;
import com.projektskybox.skybrewdisable.commands.DisableLinger;
import com.projektskybox.skybrewdisable.commands.DisableSplash;
import com.projektskybox.skybrewdisable.commands.PotionDrinking;
import com.projektskybox.skybrewdisable.commands.ReloadCommand;
import com.projektskybox.skybrewdisable.listeners.BrewListener;
import com.projektskybox.skybrewdisable.listeners.LingerSplaListener;
import com.projektskybox.skybrewdisable.listeners.PotionDrinkListener;
import com.projektskybox.skybrewdisable.listeners.SplashLingerDispenserListener;

public class P extends JavaPlugin{
	
	private static P instance;
	
	public static P getInstance() {
		return instance;
	}
	
	@Override
	public void onEnable() {
		instance = this;
		this.saveDefaultConfig();
		
		System.out.println("[Skyla's Brewing Disabler] Start");
		
		this.enableListeners();
		this.enableCommands();
		
	}
	
	@Override
	public void onDisable() {
		this.saveDefaultConfig();
		System.out.println("[Skyla's Brewing Disabler] Stop");
		HandlerList.unregisterAll();
		instance = null;
	}
	
	public void enableListeners() {
		getServer().getPluginManager().registerEvents(new BrewListener(), this);
		getServer().getPluginManager().registerEvents(new SplashLingerDispenserListener(), this);
		getServer().getPluginManager().registerEvents(new LingerSplaListener(), this);
		getServer().getPluginManager().registerEvents(new PotionDrinkListener(), this);
	}
	
	public void enableCommands() {
		this.getCommand("enable-potion-making").setExecutor(new DisableBrew());
		this.getCommand("skybrewdisable").setExecutor(new ReloadCommand());
		this.getCommand("splash-potions").setExecutor(new DisableSplash());
		this.getCommand("linger-potions").setExecutor(new DisableLinger());
		this.getCommand("potion-drinking").setExecutor(new PotionDrinking());
	}
}
