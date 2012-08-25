package com.lavacraftserver.SimpleActions;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Ignite implements CommandExecutor {

	private SimpleActions plugin;

	public Ignite(SimpleActions plugin) {
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		int firetime = plugin.getConfig().getInt("firelength");
		
		if (commandLabel.equalsIgnoreCase("ignite")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("[SimpleActions] This command can only be run by a player!");
				return true;
			} else {
				final Player s = (Player)sender;
				if (s.hasPermission("SimpleActions.ignite") || s.isOp()) {
					if (args.length > 2) {
						sender.sendMessage(ChatColor.RED + "Too many arguments!");
						return true;
					}
					if (args.length == 0) {
						sender.sendMessage(ChatColor.RED + "Please specify a player!");
						return true;
					} else {
						Player target = Bukkit.getServer().getPlayer(args[0]);
						if (target == null) {
							sender.sendMessage(ChatColor.RED + "That player is not online!");
							return true;
						}
						if (args.length == 1) {
							target.setFireTicks(firetime * 20);
							sender.sendMessage(ChatColor.DARK_RED + target.getName() + ChatColor.YELLOW + " has been ignited by " + ChatColor.DARK_RED + s.getName() + ChatColor.YELLOW + " for " + ChatColor.DARK_RED + "5" + ChatColor.YELLOW + " seconds!");
							return true;
						}
						if (args.length == 2) {
							firetime = Integer.parseInt(args[1]);
							target.setFireTicks(firetime * 20);
							sender.sendMessage(ChatColor.DARK_RED + target.getName() + ChatColor.YELLOW + " has been ignited by " + ChatColor.DARK_RED + s.getName() + ChatColor.YELLOW + " for " + ChatColor.DARK_RED + firetime + ChatColor.YELLOW + " seconds!");
							return true;
						}
					}
				}
			}
		}
		return true;	
	}
}
