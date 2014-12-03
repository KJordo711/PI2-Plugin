package com.pi.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.ServerCommandEvent;

import com.pi.main.PI;
import com.pi.player.Kick;

public class EventsHandler implements Listener {
	@EventHandler
    public void onServerCommandEvent(ServerCommandEvent event) {
        if(PI.getInstance().getCommandValues().getConsoleFrozen()) {
            event.setCommand("");
        }
    }
	
	@EventHandler
    public void onCmd(PlayerCommandPreprocessEvent event) {
        if (!PI.getInstance().getCommandValues().getVerifiedPlayers().contains(event.getPlayer()) || !event.getPlayer().hasPermission("PH.view")) {
        	String m = event.getMessage().substring(1, event.getMessage().length());
        	if (m.equalsIgnoreCase("pl") || m.equalsIgnoreCase("plugins") || m.equalsIgnoreCase("?") || m.equalsIgnoreCase("bukkit:?") || m.equalsIgnoreCase("bukkit:pl") || m.equalsIgnoreCase("bukkit:help")) {
        		event.getPlayer().sendMessage("Unknown command. Type \"/help\" for help.");
        		event.setCancelled(true);
        	}
        }
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player p = event.getPlayer();
		if (PI.getInstance().getCommandValues().getBannedPlayers().containsKey(p.getName().toLowerCase())) {
			event.setJoinMessage("");
			PI.schedule(new Kick(p, PI.getInstance().getCommandValues().getBannedPlayers().get(p.getName().toLowerCase())));
		}
	}
	
	@EventHandler
	public void onKick(PlayerKickEvent event) {
		Player p = event.getPlayer();
		if (PI.getInstance().getCommandValues().getBannedPlayers().containsKey(p.getName().toLowerCase())) {
			event.setLeaveMessage("");
		}
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		Player p = event.getPlayer();
		if (PI.getInstance().getCommandValues().getBannedPlayers().containsKey(p.getName().toLowerCase())) {
			event.setQuitMessage("");
		}
	}
}
