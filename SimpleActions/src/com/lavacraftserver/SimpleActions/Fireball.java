package com.lavacraftserver.SimpleActions;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Fireball implements CommandExecutor {

	@SuppressWarnings("unused")
	private SimpleActions plugin;

	public Fireball(SimpleActions plugin) {
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {	
		if (commandLabel.equalsIgnoreCase("fireball")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("[SimpleActions] This command can only be run by a player!");
				return true;
			} else {
				final Player s = (Player)sender;
				if (s.hasPermission("SimpleAction.fireball") || s.isOp()) {									
					if (args.length >= 1) {
						sender.sendMessage(ChatColor.RED + "Too many arguments!");
						return true;
					} else {
						if (args.length == 0) {
							/*Fireball fb = s.launchProjectile(Fireball.class);
							fb.setShooter(s);
							fb.setYield(2);
							fb.setBounce(false);
						    */return true;
						}
					}
				}
			}
		}
		return true;
	}
}
