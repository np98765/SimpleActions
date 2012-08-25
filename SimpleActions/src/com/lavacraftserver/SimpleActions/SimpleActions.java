package com.lavacraftserver.SimpleActions;

import java.io.File;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import com.lavacraftserver.SimpleActions.Lol;

public class SimpleActions extends JavaPlugin implements Listener {
		
	@Override
	public void onEnable() {
		getLogger().info("SimpleActions has been enabled!");
		getServer().getPluginManager().registerEvents(new Bomb(this), this);
		getConfig().options().copyDefaults(true);
		saveConfig();

		getCommand("bomb").setExecutor(new Bomb(this));
		getCommand("cry").setExecutor(new Cry(this));
		getCommand("explode").setExecutor(new Explode(this));
		getCommand("fakedeop").setExecutor(new FakeDeop(this));
		getCommand("fakeop").setExecutor(new FakeOp(this));
		getCommand("fireball").setExecutor(new Fireball(this));
		getCommand("gm").setExecutor(new Gamemode(this));
		getCommand("givexp").setExecutor(new GiveXP(this));
		getCommand("heal").setExecutor(new Heal(this));
		getCommand("hug").setExecutor(new Hug(this));
		getCommand("ignite").setExecutor(new Ignite(this));
		getCommand("lol").setExecutor(new Lol(this));
		getCommand("murder").setExecutor(new Murder(this));
		getCommand("pm").setExecutor(new PM(this));
		getCommand("poke").setExecutor(new Poke(this));
		getCommand("slap").setExecutor(new Slap(this));
		getCommand("soar").setExecutor(new Soar(this));
		getCommand("sword").setExecutor(new Sword(this));
		getCommand("zap").setExecutor(new Zap(this));
	}
	
	@Override
	public void onDisable() {
		getLogger().info("SimpleActions has been disabled.");
	}
	
	public void loadConfig() {
		File file = new File(this.getDataFolder(), "config.yml");
		if(!file.exists()) {
			getConfig().options().copyDefaults(true);
			saveConfig();
		}
	}

}
