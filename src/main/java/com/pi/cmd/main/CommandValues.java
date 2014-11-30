package com.pi.cmd.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class CommandValues {

	private List<Player> verifiedPlayers = new ArrayList<Player>();
    private boolean isConsoleFrozen = false;
    private HashMap<String, GameMode> gms = new HashMap<String, GameMode>();
    
    public List<Player> getVerifiedPlayers() {
		return this.verifiedPlayers;
	}
    
    public HashMap<String, GameMode> getGms() {
    	return this.gms;
    }
	
	public boolean getConsoleFrozen() {
		return this.isConsoleFrozen;
	}
	
	public void setConsoleFrozen(boolean set) {
		this.isConsoleFrozen = set;
	}
}