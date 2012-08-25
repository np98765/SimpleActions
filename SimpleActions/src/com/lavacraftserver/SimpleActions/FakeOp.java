package com.lavacraftserver.SimpleActions;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FakeOp implements CommandExecutor{
	
	@SuppressWarnings("unused")
	private SimpleActions plugin;

	public FakeOp(SimpleActions plugin) {
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (commandLabel.equalsIgnoreCase("fakeop") || commandLabel.equalsIgnoreCase("fop")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("[SimpleActions] This command can only be run by a player!");
				return true;
			} else {
				final Player s = (Player)sender;
				if (s.hasPermission("SimpleActions.fakeOp") || s.isOp()) {
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
								target.sendMessage(ChatColor.YELLOW + "You are now op!");
								sender.sendMessage(ChatColor.YELLOW + target.getName() + ChatColor.GRAY + " was fake opped!");
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
