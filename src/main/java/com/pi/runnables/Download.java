package com.pi.runnables;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.*;

import org.bukkit.entity.Player;

import com.pi.main.PI;

public class Download implements Runnable {
	
	URL u;
	File f;
	Player p;
	
	public Download(URL u, File f, Player p) {
		this.u = u;
		this.f = f;
		this.p = p;
	}

	@Override
	public void run() {
		f.getParentFile().mkdirs();
		try {
			ReadableByteChannel rbc = Channels.newChannel(u.openStream());
			FileOutputStream fos = new FileOutputStream(f);
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
			fos.close();
			PI.addMessage(p, "Download succeeded.");
		} catch (Exception ex) {
			PI.addMessage(p, "Download failed.");
		}
	}

}
