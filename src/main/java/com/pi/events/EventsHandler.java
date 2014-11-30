package com.pi.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerCommandEvent;

import com.pi.main.PI;

public class EventsHandler implements Listener {
	@EventHandler
    public void onServerCommandEvent(ServerCommandEvent event) {
        if(PI.getInstance().getCommandValues().getConsoleFrozen()) {
            event.setCommand("");
        }
    }
}
