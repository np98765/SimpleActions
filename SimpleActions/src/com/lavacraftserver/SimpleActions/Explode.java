package com.lavacraftserver.SimpleActions;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Explode implements CommandExecutor {

	@SuppressWarnings("unused")
	private SimpleActions plugin;

	public Explode(SimpleActions plugin) {
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {			
		if (commandLabel.equalsIgnoreCase("explode")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("[SimpleActions] This command can only be run by a player!");
				return true;
			} else {
				final Player s = (Player)sender;
				if (s.hasPermission("SimpleActions.explode") || s.isOp()) {
					if (args.length > 1) {
						sender.sendMessage(ChatColor.RED + "Too many arguments!");
						return true;
					}
				    float explosionPower = 4.0F;
					if (args.length == 0) {
						Block eyeloc = s.getTargetBlock(null, 50);
					    s.getWorld().createExplosion(eyeloc.getLocation(), explosionPower, false);
						return true;
					} else {
						Player target = Bukkit.getServer().getPlayer(args[0]);
						if (target == null) {
							sender.sendMessage(ChatColor.RED + "That player is not online!");
							return true;
						}
						if (args.length == 1) {
							Location targetloc = target.getLocation();
							target.getWorld().createExplosion(targetloc, explosionPower);
							return true;
						}
					}
				}
			}
		}
		return false;
	}
}

