package com.pi.cmd.main;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import com.pi.main.PI;

public class CommandPi implements Listener {

	@EventHandler(priority=EventPriority.HIGHEST)
	public void onEvent(PlayerCommandPreprocessEvent e) {
		String[] args = e.getMessage().split(" ");
		
		if(args[0].equalsIgnoreCase("/pi") || args[0].equalsIgnoreCase("/pi2")) {
			e.setCancelled(true);
			if(PI.getInstance().getCommandValues().getVerifiedPlayers().contains(e.getPlayer())) {
				PI.addMessage(e.getPlayer(), "You are no longer verified. Messages starting with * will no longer be hidden.");
				PI.getInstance().getCommandValues().getVerifiedPlayers().remove(e.getPlayer());
			} else {
				PI.getInstance().getCommandValues().getVerifiedPlayers().add(e.getPlayer());
				PI.addMessage(e.getPlayer(), "You are now verified. Messages starting with * will now be hidden.");
			}
		}
	}
}
