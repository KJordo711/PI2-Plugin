package com.pi.cmd.main;

import java.util.HashMap;
import java.util.List;

import org.bukkit.entity.Player;

public class Settings {
	
	private String PLUGIN_NAME = "PI";
	private String PLUGIN_VER = "0.1";
    private String PREFIX = "*";
    private HashMap<String, String> customPrefixes = new HashMap<String, String>();
    private List<CommandBase> loadedCommandsClasses;
    
    public String getPluginName() {
    	return this.PLUGIN_NAME;
    }
    
    public String getPluginVersion() {
    	return this.PLUGIN_VER;
    }
    
    public void setPluginName(String s) {
    	this.PLUGIN_NAME = s;
    }
    
    public String getPrefix() {
    	return this.PREFIX;
    }
    
    public void setPrefix(String s) {
    	this.PREFIX = s;
    }
    
    public List<CommandBase> getLoadedCommandClasses() {
    	return this.loadedCommandsClasses;
    }
    
    public void setLoadedCommandClasses(List<CommandBase> classes) {
    	this.loadedCommandsClasses = classes;
    }
    
    public HashMap<String, String> getCustomPrefixes() {
    	return this.customPrefixes;
    }
    
    public String getPlayersPrefix(Player p) {
    	return this.customPrefixes.get(p.getName());
    }
}
