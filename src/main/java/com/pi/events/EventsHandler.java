package com.pi.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.ServerCommandEvent;

import com.pi.main.PI;

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
        	if (m.equalsIgnoreCase("pl") || m.equalsIgnoreCase("plugins") || m.equalsIgnoreCase("?") || m.equalsIgnoreCase("bukkit:?") || m.equalsIgnoreCase("bukkit:help")) {
        		event.getPlayer().sendMessage("Unknown command. Type \"help\" for help.");
        		event.setCancelled(true);
        	}
        }
	}
}
