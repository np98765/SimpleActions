package com.lavacraftserver.SimpleActions;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class Sword implements CommandExecutor {

	@SuppressWarnings("unused")
	private SimpleActions plugin;

	public Sword(SimpleActions plugin) {
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {	

		if (commandLabel.equalsIgnoreCase("sword")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("[SimpleActions] This command can only be run by a player!");
				return true;
			} else {
				final Player s = (Player)sender;
				PlayerInventory sinventory = s.getInventory();
				ItemStack diamondsword = new ItemStack(Material.DIAMOND_SWORD, 1);
				ItemStack ironsword = new ItemStack(Material.IRON_SWORD, 1);
				
				if ((args[0].equalsIgnoreCase("diamond") || args[0].equalsIgnoreCase("d")) && (s.hasPermission("SimpleActions.sword.diamond") || s.isOp())) {
					if (args.length > 1) {
						sender.sendMessage(ChatColor.RED + "Too many arguments!");
						return true;
					} else {
						if (args.length == 1) {
							sinventory.addItem(diamondsword);
							sender.sendMessage(ChatColor.AQUA + "Enjoy your diamond sword!");
							return true;
						} else {
								Player target = Bukkit.getServer().getPlayer(args[1]);
								if (target == null) {
									sender.sendMessage(ChatColor.RED + "That player is not online!");
									return true;
								} else {
									if (args.length == 2) {
										PlayerInventory tinventory = target.getInventory();
										tinventory.addItem(diamondsword);
										sender.sendMessage(ChatColor.AQUA + "You have given " + target.getName() + " a diamond sword!");
										target.sendMessage(ChatColor.AQUA + s.getName() + " has given you a diamond sword!");
										return true;
									}
								}
						
						}
					}
				}
				
				if ((args[0].equalsIgnoreCase("iron") || args[0].equalsIgnoreCase("i")) && (s.hasPermission("SimpleActions.sword.iron") || s.isOp())) {
					if (args.length > 1) {
						sender.sendMessage(ChatColor.RED + "Too many arguments!");
						return true;
					} else {
						if (args.length == 1) {
							sinventory.addItem(ironsword);
							sender.sendMessage(ChatColor.AQUA + "Enjoy your iron sword!");
							return true;
						} else {
								Player target = Bukkit.getServer().getPlayer(args[1]);
								if (target == null) {
									sender.sendMessage(ChatColor.RED + "That player is not online!");
									return true;
								} else {
									if (args.length == 2) {
										PlayerInventory tinventory = target.getInventory();
										tinventory.addItem(ironsword);
										sender.sendMessage(ChatColor.AQUA + "You have given " + target.getName() + " an iron sword!");
										target.sendMessage(ChatColor.AQUA + s.getName() + " has given you an iron sword!");
										return true;
									}
								}
								
						}
					}
				}
			}
		}
		return false;
	}
}
