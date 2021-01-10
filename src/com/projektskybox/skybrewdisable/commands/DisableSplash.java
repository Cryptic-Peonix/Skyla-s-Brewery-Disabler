package com.projektskybox.skybrewdisable.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class DisableSplash implements CommandExecutor {

	Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("SkylasBrewingDisabler");

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		boolean splash = plugin.getConfig().getBoolean("splash-potions.enabled");
		boolean delete = plugin.getConfig().getBoolean("splash-potions.delete-item");
		boolean disp = plugin.getConfig().getBoolean("splash-potions.disable-dispensers");
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (label.equalsIgnoreCase("splash-potions")) {
				if (args.length == 0) {
					// /splash-potions
					sender.sendMessage(
							ChatColor.RED + "Usage /splash-potions [enabled/delete-item/can-use-dispensers]");
					return true;
				}
				if (args.length > 0) {

					// /splash-potions enabled
					if (args[0].equalsIgnoreCase("enabled")) {
						if (args.length == 1) {
							sender.sendMessage(ChatColor.RED + "Usage /splash-potions enabled [true/false]");
							return true;
							// splash-potions enabled true
						} else if (args[1].equalsIgnoreCase("true")) {
							if (splash == false) {
								plugin.getConfig().set("splash-potions.enabled", true);
								plugin.saveConfig();
								player.sendMessage(ChatColor.GOLD + "Splash Potions have been enabled!");
								plugin.reloadConfig();
								return true;
							} else {
								player.sendMessage(ChatColor.GOLD + "Splash Potions are already enabled!");
								return true;
							}
							// /splash-potions enabled false
						} else if (args[1].equalsIgnoreCase("false")) {
							if (splash == true) {
								plugin.getConfig().set("splash-potions.enabled", false);
								plugin.saveConfig();
								player.sendMessage(ChatColor.GOLD + "Splash Potions have been disabled!");
								plugin.reloadConfig();
								return true;
							} else {
								player.sendMessage(ChatColor.GOLD + "Splash Potions are already disabled!");
								return true;
							}
						}
						// /splash-potions delete-item
					} else if (args[0].equalsIgnoreCase("delete-item")) {
						if (args.length == 1) {
							player.sendMessage(ChatColor.RED + "Usage: /splash-potions delete-item [true/false]");
							return true;
							// /splash-potions delete-item true
						} else if (args[1].equalsIgnoreCase("true")) {
							if (delete == false) {
								plugin.getConfig().set("splash-potions.delete-item", true);
								plugin.saveConfig();
								player.sendMessage(
										ChatColor.GOLD + "Splash item deleteion after throw has been enabled!");
								plugin.reloadConfig();
								return true;
							} else {
								player.sendMessage(ChatColor.GOLD + "Splash item deletion is already enabled!");
								return true;
							}
							// /splash-potions delete-item false
						} else if (args[1].equalsIgnoreCase("false")) {
							if (delete == true) {
								plugin.getConfig().set("splash-potions.delete-item", false);
								plugin.saveConfig();
								player.sendMessage(
										ChatColor.GOLD + "Splash item deletion after throw has been disabled!");
								plugin.reloadConfig();
								return true;
							} else {
								player.sendMessage(ChatColor.GOLD + "Splash item deletion is already disabled!");
								return true;
							}
						}
						// /splash-potions dispensers
					} else if (args[0].equalsIgnoreCase("can-use-dispensers")) {
						if (args.length == 1) {
							player.sendMessage(
									ChatColor.RED + "Usage: /splash-potions can-use-dispensers [true/false]");
							return true;
						} else if (args[1].equalsIgnoreCase("true")) {
							if (disp == true) {
								plugin.getConfig().set("splash-potions.disable-dispensers", false);
								plugin.saveConfig();
								player.sendMessage(
										ChatColor.GOLD + "The use of splash potions with dispensers has been enabled!");
								plugin.reloadConfig();
								return true;
							} else {
								player.sendMessage(ChatColor.GOLD + "Splash dispensers are already enabled!");
								return true;
							}
						} else if (args[1].equalsIgnoreCase("false")) {
							if (disp == false) {
								plugin.getConfig().set("splash-potions.disable-dispensers", true);
								plugin.saveConfig();
								player.sendMessage(
										ChatColor.GOLD + "The use of spalsh potions with dispensers has been disabled!");
								plugin.reloadConfig();
								return true;
							} else {
								player.sendMessage(ChatColor.GOLD + "Splash dispensers are already disabled!");
								return true;
							}
						}
					} else {
						sender.sendMessage(ChatColor.RED + "Usage /splash-potions [enabled/delete-item/can-use-dispensers]");
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
