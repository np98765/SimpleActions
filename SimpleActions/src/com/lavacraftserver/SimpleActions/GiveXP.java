package com.lavacraftserver.SimpleActions;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GiveXP implements CommandExecutor {
	
	private SimpleActions plugin;

	public GiveXP(SimpleActions plugin) {
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (commandLabel.equalsIgnoreCase("givexp")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("[SimpleActions] This command can only be run by a player!");
				return true;
			} else {
				final Player s = (Player)sender;
				if (s.hasPermission("SimpleActions.giveXP") || s.isOp()) {
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
							int experience = plugin.getConfig().getInt("defaultXP");
							target.giveExp(experience);
							sender.sendMessage(ChatColor.YELLOW + "You have given " + target.getName() + ChatColor.GREEN + " " + experience + ChatColor.YELLOW + " XP !");
							target.sendMessage(ChatColor.YELLOW + "You have received " + ChatColor.GREEN + experience + ChatColor.YELLOW + " XP from " + s.getName() + "!");
							return true;
						}
						if (args.length == 2) {
							int experience = Integer.parseInt(args[1]);
							target.giveExp(experience);
							sender.sendMessage(ChatColor.YELLOW + "You have given" + target.getName() + ChatColor.GREEN + experience + ChatColor.YELLOW + " XP !");
							target.sendMessage(ChatColor.YELLOW + "You have received" + ChatColor.GREEN + experience + ChatColor.YELLOW + " XP from " + s.getName() + "!");
							return true;
						}
					}
				}
			}
		}
		return true;
	}
}
