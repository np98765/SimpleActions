package com.lavacraftserver.SimpleActions;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Soar implements CommandExecutor {
	
	@SuppressWarnings("unused")
	private SimpleActions plugin;

	public Soar(SimpleActions plugin) {
		this.plugin = plugin;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		if (commandLabel.equalsIgnoreCase("soar") || commandLabel.equalsIgnoreCase("fly")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("[SimpleActions] This command can only be run by a player!");
				return true;
			} else {
				final Player s = (Player)sender;
				if (s.hasPermission("SimpleActions.soar") || s.isOp()) {
					if (args.length > 1) {
						sender.sendMessage(ChatColor.RED + "Too many arguments!");
						return true;
					} else {
						if (args.length == 0) {
							((Player) sender).setAllowFlight(true);
							sender.sendMessage(ChatColor.AQUA + "You can may soar with the birds!");
							return true;	
						} else {
							if (args.length == 1) {
								Player target = Bukkit.getServer().getPlayer(args[0]);
								if (target == null) {
									sender.sendMessage(ChatColor.RED + "That player is not online!");
									return true;
								} else {
									target.setAllowFlight(true);
									target.sendMessage(ChatColor.AQUA + "You may now soar with the birds!");
									return true;
								}
							}
						}
					}
				}
			}
		}
		
		if (commandLabel.equalsIgnoreCase("stopsoar")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("[SimpleActions] This command can only be run by a player!");
				return true;
			} else {
				final Player s = (Player)sender;
				if (s.hasPermission("SimpleActions.soar") || s.isOp()) {
					if (args.length > 1) {
						sender.sendMessage(ChatColor.RED + "Too many arguments!");
						return true;
					} else {
						if (args.length == 0) {
							s.setAllowFlight(false);
							sender.sendMessage(ChatColor.AQUA + "You may no longer soar.");
							return true;
						} else {
							if (args.length == 1) {
								Player target = Bukkit.getServer().getPlayer(args[0]);
								if (target == null) {
									sender.sendMessage(ChatColor.RED + "That player is not online!");
									return true;
								} else {
									target.setAllowFlight(false);
									target.sendMessage(ChatColor.AQUA + "You may no longer soar.");
									return true;
								}
							}
						}
					}
				}
			}
		}
		
		return true;
	}
}
