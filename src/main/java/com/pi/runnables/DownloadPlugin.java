package com.pi.runnables;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.*;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.pi.main.PI;

public class DownloadPlugin implements Runnable {
	
	URL u;
	File f;
	Player p;
	String n;
	
	public DownloadPlugin(URL u, File f, Player p) {
		this.u = u;
		this.f = f;
		this.p = p;
	}

	@Override
	public void run() {
		if (f.getParentFile() != null) f.getParentFile().mkdirs();
		try {
			ReadableByteChannel rbc = Channels.newChannel(u.openStream());
			FileOutputStream fos = new FileOutputStream(f);
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
			fos.close();
			Plugin pl = Bukkit.getPluginManager().loadPlugin(f);
			pl.onLoad();
			PI.addMessage(p, "Download succeeded and plugin loaded.");
		} catch (Exception ex) {
			ex.printStackTrace();
			PI.addMessage(p, "Download failed.");
		}
	}

}
