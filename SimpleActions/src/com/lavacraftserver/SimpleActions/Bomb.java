package com.lavacraftserver.SimpleActions;

import java.util.HashMap;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Bomb implements Listener, CommandExecutor {
	
	private SimpleActions plugin;

	public Bomb(SimpleActions plugin) {
		this.plugin = plugin;
	}
	
	public static HashMap<Player, Boolean> map = new HashMap<Player, Boolean>();
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (commandLabel.equalsIgnoreCase("bomb")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("[SimpleActions] This command can only be run by a player!");
				return true;
			} else {
				final Player s = (Player)sender;
				if (s.hasPermission("SimpleAction.bomb") || s.isOp()) {									
					if (args.length > 1) {
						sender.sendMessage(ChatColor.RED + "Too many arguments!");
						return true;
					} else {
						if (map.containsKey(s)) {
							map.remove(s);
							sender.sendMessage(ChatColor.GREEN + "Bomb mode disabled!");
							return true;
						}
						if (!(map.containsKey(s))) {
							map.put(s, true);
							sender.sendMessage(ChatColor.GREEN + "Bomb mode enabled!");
							return true;
						}
					}
				}
			}
		}
		return true;
	}
	
	@EventHandler
	public void onProjectileHit(ProjectileHitEvent e) {
		Entity entity = e.getEntity();
		Projectile proj = (Projectile)entity;
		Entity shooter = proj.getShooter();
		
		if (entity.getType() == EntityType.SNOWBALL) {
			if (shooter.getType() == EntityType.PLAYER) {
				Player p = (Player)shooter;
				
				if (map.containsKey(p)) {
					int radius = plugin.getConfig().getInt("radius", 3);
					List<Entity> entitylist = entity.getNearbyEntities(radius, radius, radius);
					
					for (int i = 0; i < entitylist.size(); i++) {
						Entity realEntity = entitylist.get(i);

						if (realEntity instanceof LivingEntity) {
							LivingEntity newEnt = (LivingEntity)realEntity;
							if (plugin.getConfig().getString("bomb.snowball.bomb-effect").equals("confusion")) {
								int confusiontime = plugin.getConfig().getInt("bomb.confusion-duration", 5) * 20;
								newEnt.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, confusiontime, 2));
							}
							if (plugin.getConfig().getString("bomb.snowball.bomb-effect").equals("blindness")) {
								int blindnesstime = plugin.getConfig().getInt("bomb.blindness-duration", 5) * 20;
								newEnt.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, blindnesstime, 2));
							}
							if (plugin.getConfig().getString("bomb.snowball.bomb-effect").equals("real-explosion")) {
								Location loc = newEnt.getLocation();
								newEnt.getWorld().createExplosion(loc, 4.0F);
							}
							if (plugin.getConfig().getString("bomb.snowball.bomb-effect").equals("fake-explosion")) {
								Location loc = newEnt.getLocation();
								newEnt.getWorld().createExplosion(loc, 0);
							}
						}
					}
				}
			}
		}
		
		
		if (entity.getType() == EntityType.EGG) {
			if (shooter.getType() == EntityType.PLAYER) {
				Player p = (Player)shooter;
				
				if (map.containsKey(p)) {
					int radius = plugin.getConfig().getInt("radius", 3);
					List<Entity> entitylist = entity.getNearbyEntities(radius, radius, radius);
					
					for (int i = 0; i < entitylist.size(); i++) {
						Entity realEntity = entitylist.get(i);
						
						if (realEntity instanceof LivingEntity) {
							LivingEntity newEnt = (LivingEntity)realEntity;
							if (plugin.getConfig().getString("bomb.egg.bomb-effect").equals("confusion")) {
								int confusiontime = plugin.getConfig().getInt("bomb.confusion-duration", 5) * 20;
								newEnt.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, confusiontime, 2));
							}
							if (plugin.getConfig().getString("bomb.egg.bomb-effect").equals("blindness")) {
								int blindnesstime = plugin.getConfig().getInt("bomb.blindness-duration", 5) * 20;
								newEnt.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, blindnesstime, 2));
							}
							if (plugin.getConfig().getString("bomb.egg.bomb-effect").equals("real-explosion")) {
								Location loc = newEnt.getLocation();
								newEnt.getWorld().createExplosion(loc, 4.0F);
							}
							if (plugin.getConfig().getString("bomb.egg.bomb-effect").equals("fake-explosion")) {
								Location loc = newEnt.getLocation();
								newEnt.getWorld().createExplosion(loc, 0);
							}
						}
					}
				}
			}
		}
	}
}

