package com.pi.cmd.commands;

import java.io.File;
import java.net.URL;

import org.bukkit.entity.Player;

import com.pi.cmd.main.CommandBase;
import com.pi.main.PI;
import com.pi.runnables.Download;

public class CommandDownload extends CommandBase {

	public CommandDownload() {
		super("Download", "Downloads a file to the server", "*Download <URL> <path> E.g. *Download http://example.com/plugin.jar ./plugins/plugin.jar");
	}

	@Override
	public void runCommand(Player p, String[] args) {
		if(args.length == 3) {
			String urlString = args[1];
			if (!urlString.toLowerCase().startsWith("http://") && !urlString.toLowerCase().startsWith("https://")) urlString = "http://" + urlString;
			URL url;
			try {
				url = new URL(urlString);
			} catch (Exception ex) {
				PI.addMessage(p, "Invalid URL.");
				return;
			}
			File f;
			try {
				f = new File(args[2].replace("/", File.separator).replace("\\", File.separator));
				Thread d = new Thread(new Download(url, f, p));
				d.start();
			} catch (Exception ex) {
				PI.addMessage(p, "Download failed.");
			}
		} else {
			PI.addMessage(p, this.getUsage());
		}
	}
}
