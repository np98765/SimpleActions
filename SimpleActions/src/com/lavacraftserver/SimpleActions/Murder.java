package com.lavacraftserver.SimpleActions;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Murder implements CommandExecutor {

	@SuppressWarnings("unused")
	private SimpleActions plugin;

	public Murder(SimpleActions plugin) {
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (commandLabel.equalsIgnoreCase("murder")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("[SimpleActions] This command can only be run by a player!");
				return true;
			} else {
				final Player s = (Player)sender;
				if (s.hasPermission("SimpleActions.murder") || s.isOp()) {			
					if (args.length > 1) {
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
							target.setHealth(0);
							Bukkit.getServer().broadcastMessage(ChatColor.DARK_RED + target.getName() + ChatColor.YELLOW + " has been murdered by " + ChatColor.DARK_RED + s.getName() + ChatColor.YELLOW + "!");
							return true;
						}
					}
				}
			}
		}
		return true;
	}
}
