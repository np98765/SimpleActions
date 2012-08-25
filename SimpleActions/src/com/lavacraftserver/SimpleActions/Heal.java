package com.lavacraftserver.SimpleActions;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Heal implements CommandExecutor {

	@SuppressWarnings("unused")
	private SimpleActions plugin;

	public Heal(SimpleActions plugin) {
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {	
		if (commandLabel.equalsIgnoreCase("heal")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("[SimpleActions] This command can only be run by a player!");
				return true;
			} else {
				final Player s = (Player)sender;
				if (s.hasPermission("SimpleActions.heal") || s.isOp()) {
					if (args.length == 0) {
						s.setHealth(20);
						s.sendMessage(ChatColor.AQUA + "You have been healed!");	
						return true;
					} else {
						Player target = Bukkit.getServer().getPlayer(args[0]);
						if (target == null) {
							sender.sendMessage(ChatColor.RED + "That player is not online!");
							return true;
						} else {
							if (args.length == 1) {
								target.setHealth(20);
								sender.sendMessage(ChatColor.AQUA + "You have healed " + target.getName() + "!");
								target.sendMessage(ChatColor.AQUA + "You have been healed by " + s.getName() + "!");
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
