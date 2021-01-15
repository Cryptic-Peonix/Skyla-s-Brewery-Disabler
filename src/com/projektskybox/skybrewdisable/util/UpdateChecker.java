package com.projektskybox.skybrewdisable.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Consumer;

import com.projektskybox.skybrewdisable.P;

//Author: Skyla
public class UpdateChecker {

	//Instance of the plugin
	private JavaPlugin plugin = P.getInstance();
	//The plugins rescource ID on spigot
	private int resourceId;
	
	//Create Constructor
	public UpdateChecker(JavaPlugin p, int id) {
		this.plugin = p;
		this.resourceId = id;
	}
	
	/**
	 * Gets the version of the uploaded spigot plugin
	 * @param CONSUMER
	 */
	public void getVersion(final Consumer<String> CONSUMER) {
		Bukkit.getScheduler().runTaskAsynchronously(this.plugin, () -> {
			try (InputStream inputStream = new URL("https://api.spigotmc.org/legacy/update.php?resource=" + this.resourceId).openStream(); 
					Scanner scan = new Scanner(inputStream)) {
				if (scan.hasNext()) {
					CONSUMER.accept(scan.next());
				}
			} catch (IOException exception) {
				this.plugin.getLogger().info("Cannot look for updates: " + exception.getMessage());
			}
		});
		
	}
}
