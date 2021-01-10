package com.projektskybox.skybrewdisable.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class PotionDrinking implements CommandExecutor {

	Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("SkylasBrewingDisabler");

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		boolean enabled = plugin.getConfig().getBoolean("drinking-potions.enabled");
		boolean delete = plugin.getConfig().getBoolean("drinking-potions.delete-item");

		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (label.equalsIgnoreCase("potion-drinking")) {
				if (args.length == 0) {
					// /potion-drinking
					sender.sendMessage(ChatColor.RED + "Usage: /potion-drinking [enabled/delete-item] [true/false]");
					return true;
				}
				if (args.length > 0) {

					// /potion-drinking enabled
					if (args[0].equalsIgnoreCase("enabled")) {
						
						if (args.length == 1) {
							player.sendMessage(ChatColor.RED + "Usage: /potion-drinking enabled [true/false]");
							return true;
						// /potion-drinking enabled true
						} else if (args[1].equalsIgnoreCase("true")) {

							if (enabled == false) {
								plugin.getConfig().set("drinking-potions.enabled", true);
								plugin.saveConfig();
								player.sendMessage(ChatColor.GOLD + "Potion drinking is now enabled!");
								plugin.reloadConfig();
								return true;
							} else {
								player.sendMessage(ChatColor.GOLD + "Potion drinking is already enabled!");
								return true;
							}
							// /potion-drinking enabled false
						} else if (args[1].equalsIgnoreCase("false")) {
							if (enabled == true) {
								plugin.getConfig().set("drinking-potions.enabled", false);
								plugin.saveConfig();
								player.sendMessage(ChatColor.GOLD + "Potion drinking has been disabled!");
								plugin.reloadConfig();
								return true;
							} else {
								player.sendMessage(ChatColor.GOLD + "Potion drinking is already disabled!");
								return true;
							}
						}
						// /potion-drinking delete-item
					} else if (args[0].equalsIgnoreCase("delete-item")) {
						if (args.length == 1) {
							player.sendMessage(ChatColor.RED + "Usage: /potion-drinking delete-item [true/false]");
							return true;
							// /potion-drinking delete-item true
						} else if (args[1].equalsIgnoreCase("true")) {

							if (delete == false) {
								plugin.getConfig().set("drinking-potions.delete-item", true);
								plugin.saveConfig();
								player.sendMessage(ChatColor.GOLD + "Potion item deletion after drinking is now enabled!");
								plugin.reloadConfig();
								return true;
							} else {
								player.sendMessage(ChatColor.GOLD + "Potion item deletion is already enabled!");
								return true;
							}
							// /potion-drinking delete-item false
						} else if (args[1].equalsIgnoreCase("false")) {

							if (delete == true) {
								plugin.getConfig().set("drinking-potions.delete-item", false);
								plugin.saveConfig();
								player.sendMessage(ChatColor.GOLD + "Potion item deletion after drinking is now disabled!");
								plugin.reloadConfig();
								return true;
							} else {
								player.sendMessage(ChatColor.GOLD + "Potion item deletion is already disabled!");
								return true;
							}
						}
					} else {
						player.sendMessage(ChatColor.RED + "Usage: /potion-drinking [enabled/delete-item]");
						return true;
					}
				}
			}

		} else {
			sender.sendMessage(ChatColor.DARK_RED
					+ "This command can only be run by an in-game player! Please edit the config.yml and run /skybrewdisable reload!");
			return true;
		}
		return false;
	}

}
