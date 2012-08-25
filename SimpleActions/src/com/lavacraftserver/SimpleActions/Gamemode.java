package com.lavacraftserver.SimpleActions;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Gamemode implements CommandExecutor{
	
	@SuppressWarnings("unused")
	private SimpleActions plugin;

	public Gamemode(SimpleActions plugin) {
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (commandLabel.equalsIgnoreCase("gm")) {
			GameMode creative = GameMode.CREATIVE;
			GameMode survival = GameMode.SURVIVAL;
			if (!(sender instanceof Player)) {
				sender.sendMessage("[SimpleActions] This command can only be run by a player!");
				return true;
			} else {
				final Player s = (Player)sender;
				if (s.hasPermission("SimpleActions.gm") || s.isOp()) {
					if (args.length > 1) {
						sender.sendMessage(ChatColor.RED + "Too many arguments!");
						return true;
					} else {
						if (args.length == 0) {
							if (s.getGameMode() == GameMode.CREATIVE) {
								s.setGameMode(survival);
								sender.sendMessage(ChatColor.GRAY + "You are now in survival mode!");	
								return true;
							}
							if (s.getGameMode() == GameMode.SURVIVAL) {
								s.setGameMode(creative);
								sender.sendMessage(ChatColor.GRAY + "You are now in creative mode!");
								return true;
							}
						}
						if (args.length == 1) {
							Player target = Bukkit.getServer().getPlayer(args[0]);
							if (target == null) {
								sender.sendMessage(ChatColor.RED + "That player is not online!");
								return true;
							} else {
								if (target.getGameMode() == GameMode.CREATIVE) {
									target.setGameMode(survival);
									sender.sendMessage(ChatColor.GRAY + "You are now in survival mode!");
									return true;
								}
								if (target.getGameMode() == GameMode.SURVIVAL) {
									target.setGameMode(creative);
									sender.sendMessage(ChatColor.GRAY + "You are now in creative mode!");
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
