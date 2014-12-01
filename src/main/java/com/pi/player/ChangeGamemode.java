package com.pi.player;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class ChangeGamemode implements Runnable {

	private Player p;
	private GameMode gm;
	
	public ChangeGamemode(Player p, GameMode gm) {
		this.p = p;
		this.gm = gm;
	}
	
	@Override
	public void run() {
		p.setGameMode(gm);
	}

}
