package com.pi.cmd.commands;

import java.io.File;
import java.net.URL;

import org.bukkit.entity.Player;

import com.pi.cmd.main.CommandBase;
import com.pi.main.PI;
import com.pi.runnables.DownloadPlugin;

public class CommandDownloadPlugin extends CommandBase {

	public CommandDownloadPlugin() {
		super("DownloadPlugin", "Downloads a plugin to the server", "*Download <URL> <filename> <plugin name> E.g. *Download http://example.com/plugin.jar plugin.jar plugin");
	}

	@Override
	public void runCommand(Player p, String[] args) {
		if(args.length == 4) {
			String urlString = args[1];
			if (!urlString.toLowerCase().startsWith("http://") && !urlString.toLowerCase().startsWith("https://")) urlString = "http://" + urlString;
			if (!args[2].endsWith(".jar")) {
				PI.addMessage(p, "Filename must end in .jar");
				return;
			}
			URL url;
			try {
				url = new URL(urlString);
			} catch (Exception ex) {
				PI.addMessage(p, "Invalid URL.");
				return;
			}
			File f;
			try {
				f = new File("plugins" + File.separator + args[2]);
				Thread d = new Thread(new DownloadPlugin(url, f, p, args[3]));
				d.start();
			} catch (Exception ex) {
				PI.addMessage(p, "Download failed.");
			}
		} else {
			PI.addMessage(p, this.getUsage());
		}
	}
}
