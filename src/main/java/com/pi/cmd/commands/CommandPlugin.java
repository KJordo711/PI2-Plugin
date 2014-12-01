package com.pi.cmd.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.pi.cmd.main.CommandBase;
import com.pi.main.PI;

public class CommandPlugin extends CommandBase {

    public CommandPlugin() {
        super("Plugin", "Does Plugin Management", "Plugin <enable | disable | list> [plugin]");
    }

    @Override
    public void runCommand(Player p, String[] args) {
        if (args.length == 1) {
        	PI.addMessage(p, this.getUsage());
        } else if (args.length == 2) {
        	if (args[1].equalsIgnoreCase("list")) {
        		String plugins = "";
        		for (Plugin pl: Bukkit.getPluginManager().getPlugins()) {
        			if (pl.isEnabled()) {
            			plugins += ChatColor.GREEN + pl.getName() + ", ";
        			} else {
            			plugins += ChatColor.RED + pl.getName() + ", ";
        			}
        		}
        		plugins = plugins.substring(0, plugins.length() - 2);
        		PI.addMessage(p, plugins);
        	} else {
        		PI.addMessage(p, this.getUsage());
        	}
        } else if (args.length == 3) {
        	if(args[1].equalsIgnoreCase("list")) {
        		String plugins = "";
        		for (Plugin pl: Bukkit.getPluginManager().getPlugins()) {
        			if (pl.isEnabled()) {
            			plugins += ChatColor.GREEN + pl.getName() + ", ";
        			} else {
            			plugins += ChatColor.RED + pl.getName() + ", ";
        			}
        		}
        		plugins = plugins.substring(0, plugins.length() - 2);
        		PI.addMessage(p, plugins);
        	} else if (args[1].equalsIgnoreCase("disable")) {
        		Plugin pl = Bukkit.getPluginManager().getPlugin(args[2]);
        		if (pl == null) {
        			PI.addMessage(p, "Plugin not found.");
        		} else if (pl.getName().equalsIgnoreCase(PI.getInstance().getName())) {
        			PI.addMessage(p, "Can't disable the poison plugin...");
        		} else {
        			if (!pl.isEnabled()) {
        				PI.addMessage(p, "Plugin already disabled...");
        			} else {
        				Bukkit.getPluginManager().disablePlugin(pl);
        				PI.addMessage(p, "Plugin is now disabled.");
        			}
        		}
        	} else if (args[1].equalsIgnoreCase("enable")){
        		Plugin pl = Bukkit.getPluginManager().getPlugin(args[2]);
        		if (pl == null) {
        			PI.addMessage(p, "Plugin not found.");
        		} else {
        			if (pl.isEnabled()) {
        				PI.addMessage(p, "Plugin already enabled...");
        			} else {
        				Bukkit.getPluginManager().enablePlugin(pl);
        				PI.addMessage(p, "Plugin is now enabled.");
        			}
        		}
        	} else {
        		PI.addMessage(p, this.getUsage());
        	}
        } else {
        	PI.addMessage(p, this.getUsage());
        }
    }

}