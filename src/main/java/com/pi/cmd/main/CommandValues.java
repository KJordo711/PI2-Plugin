package com.pi.cmd.main;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

public class CommandValues {

	private List<Player> verifiedPlayers = new ArrayList<Player>();
    private boolean isConsoleFrozen = false;
    
    public List<Player> getVerifiedPlayers() {
		return this.verifiedPlayers;
	}
	
	public boolean getConsoleFrozen() {
		return this.isConsoleFrozen;
	}
	
	public void setConsoleFrozen(boolean set) {
		this.isConsoleFrozen = set;
	}
}