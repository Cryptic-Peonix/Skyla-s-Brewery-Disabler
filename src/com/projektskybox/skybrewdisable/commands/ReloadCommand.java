package com.projektskybox.skybrewdisable.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

public class ReloadCommand implements CommandExecutor {

	Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("SkylasBrewingDisabler");
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(label.equalsIgnoreCase("skybrewdisable") || label.equalsIgnoreCase("sbd")) {
			if(!sender.hasPermission("skybrewdisable.reload")) {
				sender.sendMessage(ChatColor.DARK_RED + "You cannot run this command");
				return true;
			}
			if(args.length == 0) {
				// /skybrewdisable
				sender.sendMessage(ChatColor.RED + "Usage: /skybrewdisable reload");
				return true;
			}
			if(args.length > 0) {
				// /skybrewdisable reload
				if(args[0].equalsIgnoreCase("reload")) {
					plugin.saveDefaultConfig();
					plugin.reloadConfig();
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("reload-message")));
					return true;
				} else {
					sender.sendMessage(ChatColor.RED + "Usage: /skybrewdisable reload");
					return true;
				}
			}
		}
		return false;
	}
}
