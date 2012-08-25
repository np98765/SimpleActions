package com.lavacraftserver.SimpleActions;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Lol implements CommandExecutor {

	@SuppressWarnings("unused")
	private SimpleActions plugin;

	public Lol(SimpleActions plugin) {
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {	
		if (commandLabel.equalsIgnoreCase("lol")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("[SimpleActions] This command can only be run by a player!");
				return true;
			} else {
				final Player s = (Player)sender;
				if (s.hasPermission("SimpleActions.lol") || s.isOp()) {
					if (args.length > 1) {
						sender.sendMessage(ChatColor.RED + "Too many arguments!");
						return true;
					}
					if (args.length == 0) {
						Bukkit.getServer().broadcastMessage(ChatColor.DARK_RED + sender.getName() + ChatColor.YELLOW + " has laughed out loud!");
						return true;
					} else {
						Player target = Bukkit.getServer().getPlayer(args[0]);
						if (target == null) {
							sender.sendMessage(ChatColor.RED + "That player is not online!");
							return true;
						}
						if (args.length == 1) {
							Bukkit.getServer().broadcastMessage(ChatColor.DARK_RED + sender.getName() + ChatColor.YELLOW + " has laughed out loud at " + ChatColor.DARK_RED + target.getName() + ChatColor.YELLOW + "!");
							return true;
						}
					}
				}
			}
		}
		return true;
	}

}
