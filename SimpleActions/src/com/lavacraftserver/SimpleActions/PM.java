package com.lavacraftserver.SimpleActions;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PM implements CommandExecutor {
	
	@SuppressWarnings("unused")
	private SimpleActions plugin;

	public PM(SimpleActions plugin) {
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {	
		if (commandLabel.equalsIgnoreCase("pm")) {
			String message = "";
			for (int i = 1; i < args.length; i++) message += (i > 1 ? " " : "") + args[i];
			if (!(sender instanceof Player)) {
				sender.sendMessage("[SimpleActions] This command can only be run by a player!");
				return true;
			} else {
				final Player s = (Player)sender;
				if (s.hasPermission("SimpleActions.pm") || s.isOp()) {
					if (args.length == 0) {
						s.sendMessage(ChatColor.RED + "Please specify a player!");	
						return true;
					} else {
						Player target = Bukkit.getServer().getPlayer(args[0]);
						if (target == null) {
							sender.sendMessage(ChatColor.RED + "That player is not online!");
							return true;
						} else {
							sender.sendMessage(ChatColor.GRAY + "[Me -> " + target.getName() + "] " + ChatColor.WHITE + message);
							target.sendMessage(ChatColor.GRAY + "[" + s.getName() + " -> Me] " + ChatColor.WHITE + message);
							return true;
						}
					}
				}
			}
		}
		return true;
	}
}
