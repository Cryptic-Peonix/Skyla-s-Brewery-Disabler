package com.projektskybox.skybrewdisable.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

/**
 * @author Skyla
 */
public class DisableBrew implements CommandExecutor {

	Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("SkylasBrewingDisabler");

	@Override
	/**
	 * Command used to enable/disable fueling brewing stands Can only be run by an
	 * in-game player Automatically reloads the config file after the command is run
	 */
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		boolean make = plugin.getConfig().getBoolean("brewing.enabled");
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (label.equalsIgnoreCase("enable-potion-making")) {
				if (args.length > 2 || args.length == 0) {
					player.sendMessage(ChatColor.RED + "Usage: /enable-potion-making [true/false]");
					return true;
				}
				if (args[0].equalsIgnoreCase("false")) {
					if (make == true) {
						plugin.getConfig().set("brewing.enabled", false);
						plugin.saveConfig();
						player.sendMessage(ChatColor.GOLD + "Potion making has been disabled!");
						plugin.reloadConfig();
						return true;
					} else {
						player.sendMessage(ChatColor.GOLD + "Potion making is already disabled!");
						return true;
					}
				} else if (args[0].equalsIgnoreCase("true")) {
					if (make == false) {
						plugin.getConfig().set("brewing.enabled", true);
						plugin.saveConfig();
						player.sendMessage(ChatColor.GOLD + "Potion making has been enabled!");
						plugin.reloadConfig();
						return true;
					} else {
						player.sendMessage(ChatColor.GOLD + "Potion making is already enabled!");
						return true;
					}
				}
				return false;
			}
		} else {
			sender.sendMessage(ChatColor.DARK_RED
					+ "This command can only be run by an in-game player! Please edit the config.yml and run /skybrewdisable reload!");
			return true;
		}
		return false;
	}
}
