package com.lavacraftserver.SimpleActions;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Slap implements CommandExecutor {
	
	@SuppressWarnings("unused")
	private SimpleActions plugin;

	public Slap(SimpleActions plugin) {
		this.plugin = plugin;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

		if (commandLabel.equalsIgnoreCase("slap")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("[SimpleActions] This command can only be run by a player!");
				return true;
			} else {
				final Player s = (Player)sender;
				if (s.hasPermission("SimpleActions.slap") || s.isOp()) {
					if (args.length > 1) {
						sender.sendMessage(ChatColor.RED + "Too many arguments!");
						return true;
					} else {
						if (args.length == 0) {
							sender.sendMessage(ChatColor.RED + "Please specify a player!");
							return true;
						}
						if (args.length == 1) {
							Player target = Bukkit.getServer().getPlayer(args[0]);
							if (target == null) {
								sender.sendMessage(ChatColor.RED + "That player is not online!");
								return true;
							} else {
								int health = target.getHealth();
								target.damage(1);
								target.setHealth(health);
								Bukkit.getServer().broadcastMessage(ChatColor.DARK_RED + s.getName() + ChatColor.YELLOW + " has slapped " + ChatColor.DARK_RED + target.getName() + ChatColor.YELLOW + "!");
								return true;
							}
						}
					}
				}
			}
		}
		return true;
	}
}
