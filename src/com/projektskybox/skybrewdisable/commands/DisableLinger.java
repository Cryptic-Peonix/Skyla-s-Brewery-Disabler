package com.projektskybox.skybrewdisable.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class DisableLinger implements CommandExecutor {

	Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("SkylasBrewingDisabler");

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		boolean linger = plugin.getConfig().getBoolean("lingering-potions.enabled");
		boolean delete = plugin.getConfig().getBoolean("lingering-potions.delete-item");
		boolean disp = plugin.getConfig().getBoolean("lingering-potions.disable-dispensers");
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (label.equalsIgnoreCase("linger-potions")) {
				if (args.length == 0) {
					// /linger-potions
					sender.sendMessage(
							ChatColor.RED + "Usage /linger-potions [enabled/delete-item/can-use-dispensers]");
					return true;
				}
				if (args.length > 0) {

					// /linger-potions enabled
					if (args[0].equalsIgnoreCase("enabled")) {
						if (args.length == 1) {
							player.sendMessage(ChatColor.RED + "Usage /linger-potions [enabled/delete-item]");
							return true;
							// linger-potions enabled true
						} else if (args[1].equalsIgnoreCase("true")) {
							if (linger == false) {
								plugin.getConfig().set("lingering-potions.enabled", true);
								plugin.saveConfig();
								player.sendMessage(ChatColor.GOLD + "Lingering Potions have been enabled!");
								plugin.reloadConfig();
								return true;
							} else {
								player.sendMessage(ChatColor.GOLD + "Lingering Potions are already enabled!");
								return true;
							}
							// /linger-potions enabled false
						} else if (args[1].equalsIgnoreCase("false")) {
							if (linger == true) {
								plugin.getConfig().set("lingering-potions.enabled", false);
								plugin.saveConfig();
								player.sendMessage(ChatColor.GOLD + "Lingering Potions have been disabled!");
								plugin.reloadConfig();
								return true;
							} else {
								player.sendMessage(ChatColor.GOLD + "Lingering Potions are already disabled!");
								return true;
							}
						}
						// /linger-potions delete-item
					} else if (args[0].equalsIgnoreCase("delete-item")) {
						if (args.length == 1) {
							player.sendMessage(ChatColor.RED + "Usage: /linger-potions delete-item [true/false]");
							return true;
							// /linger-potions delete-item true
						} else if (args[1].equalsIgnoreCase("true")) {
							if (delete == false) {
								plugin.getConfig().set("lingering-potions.delete-item", true);
								plugin.saveConfig();
								player.sendMessage(ChatColor.GOLD + "Lingering item deleteion after throw has been enabled!");
								plugin.reloadConfig();
								return true;
							} else {
								player.sendMessage(ChatColor.GOLD + "Lingering item deletion is already enabled!");
								return true;
							}
							// /linger-potions delete-item false
						} else if (args[1].equalsIgnoreCase("false")) {
							if (delete == true) {
								plugin.getConfig().set("lingering-potions.delete-item", false);
								plugin.saveConfig();
								player.sendMessage(ChatColor.GOLD + "Lingering item deletion after throw has been disabled!");
								plugin.reloadConfig();
								return true;
							} else {
								player.sendMessage(ChatColor.GOLD + "Lingering item deletion is already disabled!");
								return true;
							}
						}
						// /linger-potions dispensers
					} else if (args[0].equalsIgnoreCase("can-use-dispensers")) {
						if (args.length == 1) {
							player.sendMessage(
									ChatColor.RED + "Usage: /linger-potions can-use-dispensers [true/false]");
						} else if (args[1].equalsIgnoreCase("true")) {
							if (disp == true) {
								plugin.getConfig().set("lingering-potions.disable-dispensers", false);
								plugin.saveConfig();
								player.sendMessage(ChatColor.GOLD + "The use of lingering potions with dispensers has been enabled!");
								plugin.reloadConfig();
								return true;
							} else {
								player.sendMessage(ChatColor.GOLD + "Linger dispensers are already enabled!");
								return true;
							}
						} else if (args[1].equalsIgnoreCase("false")) {
							if (disp == false) {
								plugin.getConfig().set("lingering-potions.disable-dispensers", true);
								plugin.saveConfig();
								player.sendMessage(ChatColor.GOLD + "The use of lingering potions with dispensers has been disabled");
								plugin.reloadConfig();
								return true;
							} else {
								player.sendMessage(ChatColor.GOLD + "Lingering dispensers are already disabled!");
								return true;
							}
						}
					} else {
						sender.sendMessage(
								ChatColor.RED + "Usage /linger-potions [enabled/delete-item/can-use-dispensers]");
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
