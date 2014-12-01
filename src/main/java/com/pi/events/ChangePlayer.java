package com.pi.events;

import org.bukkit.entity.Player;

import com.pi.main.PI;

public class ChangePlayer implements Runnable {

	private Player p;
	private String change;
	
	public ChangePlayer(Player p, String change) {
		this.p = p;
		this.change = change;
	}
	
	@Override
	public void run() {
		String[] split = change.split(":");
		if (split[0].equalsIgnoreCase("gm")) {
			p.setGameMode(PI.getInstance().getCommandValues().getGms().get(split[1].toLowerCase()));
		}
	}

}
