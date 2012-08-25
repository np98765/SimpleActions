package com.lavacraftserver.SimpleActions;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Cry implements CommandExecutor{

	@SuppressWarnings("unused")
	private SimpleActions plugin;

	public Cry(SimpleActions plugin) {
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (commandLabel.equalsIgnoreCase("cry")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("[SimpleActions] This command can only be run by a player!");
				return true;
			} else {
				final Player s = (Player)sender;
				if (s.hasPermission("SimpleAction.cry") || s.isOp()) {									
					if (args.length >= 1) {
						sender.sendMessage(ChatColor.RED + "Too many arguments!");
						return true;
					} else {
						Bukkit.getServer().broadcastMessage(ChatColor.DARK_RED + s.getName() + ChatColor.YELLOW + " has cried!");
						return true;
					}
				}
			}
		}
		return true;
	}
}

/*
 * 
 * package com.lavacraftserver.SimpleActions;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Cry implements CommandExecutor{

	private SimpleActions plugin;

	public Cry(SimpleActions plugin) {
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		String prefix = plugin.getConfig().getString("prefix");
		if (commandLabel.equalsIgnoreCase("cry")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("[SimpleActions] This command can only be run by a player!");
				return true;
			} else {
				final Player s = (Player)sender;
				if (s.hasPermission("SimpleAction.cry") || s.isOp()) {									
					if (args.length >= 1) {
						sender.sendMessage(ChatColor.RED + "Too many arguments!");
						return true;
					} else {
						if (prefix == null) {
							Bukkit.getServer().broadcastMessage(ChatColor.DARK_RED + s.getName() + ChatColor.YELLOW + " has cried!");
						}
						Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', prefix + ChatColor.DARK_RED + s.getName() + ChatColor.YELLOW + " has cried!"));
						return true;
					}
				}
			}
		}
		return true;
	}
}
*/

