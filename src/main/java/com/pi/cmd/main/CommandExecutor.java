package com.pi.cmd.main;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.pi.main.PI;

/**
 * Credits to SabourQ for helping with this!
 */
public class CommandExecutor implements Listener {

    private PI plugin;

    public CommandExecutor(PI instance) {
        this.plugin = instance;
        
    }

    @EventHandler(priority=EventPriority.HIGHEST)
    public void onCommandEvent(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        if(PI.getInstance().getCommandValues().getVerifiedPlayers().contains(e.getPlayer())) {
            if(plugin.getSettings().getCustomPrefixes().containsKey(p.getName())) {
            	if(e.getMessage().startsWith(plugin.getSettings().getCustomPrefixes().get(p.getName()))) {
            		e.setCancelled(true);
            		String[] args = e.getMessage().split(" ");
            		for(CommandBase c : plugin.getSettings().getLoadedCommandClasses()) {
            			if(args[0].equalsIgnoreCase(plugin.getSettings().getCustomPrefixes().get(p.getName()) + c.getCommand())) {
            				c.runCommand(p, args);
            				return;
            			}
            		}
                    PI.addMessage(p, "Invalid command. Do " + plugin.getSettings().getCustomPrefixes().get(p.getName()) + "help for command help.");
            	}
            } else {
                if(e.getMessage().startsWith("*")) {
                    e.setCancelled(true);
                    String[] args = e.getMessage().split(" ");
                    
                    for(CommandBase c : plugin.getSettings().getLoadedCommandClasses()) {
                    	if(args[0].equalsIgnoreCase(plugin.getSettings().getPrefix() + c.getCommand())) {
                            c.runCommand(p, args);
                            return;
                    	}
                    }
                    PI.addMessage(p, "Invalid command. Do *help for command help.");
                }
            }
        }
    }
}