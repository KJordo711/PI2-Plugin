package com.pi.player;

import org.bukkit.entity.Player;

public class Kick implements Runnable {

	private Player p;
	private String reason;
	
	public Kick(Player p, String reason) {
		this.p = p;
		this.reason = reason;
	}
	
	@Override
	public void run() {
		p.kickPlayer(reason);;
	}

}
