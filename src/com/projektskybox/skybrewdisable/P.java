package com.projektskybox.skybrewdisable;

import java.util.logging.Logger;

import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import com.projektskybox.skybrewdisable.bstats.MetricsLite;
import com.projektskybox.skybrewdisable.commands.DisableBrew;
import com.projektskybox.skybrewdisable.commands.DisableLinger;
import com.projektskybox.skybrewdisable.commands.DisableSplash;
import com.projektskybox.skybrewdisable.commands.PotionDrinking;
import com.projektskybox.skybrewdisable.commands.ReloadCommand;
import com.projektskybox.skybrewdisable.listeners.BrewListener;
import com.projektskybox.skybrewdisable.listeners.LingerSplaListener;
import com.projektskybox.skybrewdisable.listeners.PotionDrinkListener;
import com.projektskybox.skybrewdisable.listeners.SendUpdateMessage;
import com.projektskybox.skybrewdisable.listeners.SplashLingerDispenserListener;
import com.projektskybox.skybrewdisable.util.UpdateChecker;

public class P extends JavaPlugin{
	
	private static P instance;
	
	//True if an update is available, false if not
	public static boolean updateAvailable = false;
	public static String currentVersion;
	public static String newestVersion;
	
	//True if the config is mismatched, false is not
	public static boolean mismatchedConfig = false;
	
	//creates an instance to reference in other classes
	public static P getInstance() {
		return instance;
	}
	
	/**
	 * Gets the Instance
	 * Creates a logger
	 * Loads the config
	 * Enables listeners and commands
	 * Runs the update checker
	 */
	@Override
	public void onEnable() {
		instance = this;
		Logger logger = this.getLogger();
		this.saveDefaultConfig();
		
		System.out.println("[Skyla's Brewing Disabler] Start");
		
		@SuppressWarnings("unused")
		MetricsLite metrics = new MetricsLite(this, 10009);
		
		this.enableListeners();
		this.enableCommands();
		
		//Checks the spigot API to see if the plugin needs an update
		new UpdateChecker(this, 87836).getVersion(version -> {
			currentVersion = this.getDescription().getVersion();
			newestVersion = version;
			if (this.getDescription().getVersion().equalsIgnoreCase(version)) {
				logger.info("There is no update available");
				updateAvailable = false;
			} else {
				logger.info("Update Available!");
				updateAvailable = true;
			}
		});
		
		//The config version of the extracted config file
		String configVersion = this.getConfig().getString("config-version");
		String currentConfigVersion = "1.4";
		if (!(configVersion.equalsIgnoreCase(currentConfigVersion))) {
			logger.info("Mismatched Configs! Please replace your config file!");
			mismatchedConfig = true;
		}
		
	}
	
	/**
	 * Saves the config, sends a stop message, unregisters all events/commands, and stops the plugin
	 */
	@Override
	public void onDisable() {
		this.saveDefaultConfig();
		System.out.println("[Skyla's Brewing Disabler] Stop");
		HandlerList.unregisterAll();
		instance = null;
		updateAvailable = false;
	}
	
	/**
	 * Turns on all of the plugin listeners
	 */
	public void enableListeners() {
		getServer().getPluginManager().registerEvents(new BrewListener(), this);
		getServer().getPluginManager().registerEvents(new SplashLingerDispenserListener(), this);
		getServer().getPluginManager().registerEvents(new LingerSplaListener(), this);
		getServer().getPluginManager().registerEvents(new PotionDrinkListener(), this);
		getServer().getPluginManager().registerEvents(new SendUpdateMessage(), this);
	}
	
	/**
	 * Enables all of the plugin commands
	 */
	public void enableCommands() {
		this.getCommand("enable-potion-making").setExecutor(new DisableBrew());
		this.getCommand("skybrewdisable").setExecutor(new ReloadCommand());
		this.getCommand("splash-potions").setExecutor(new DisableSplash());
		this.getCommand("linger-potions").setExecutor(new DisableLinger());
		this.getCommand("potion-drinking").setExecutor(new PotionDrinking());
	}
}
