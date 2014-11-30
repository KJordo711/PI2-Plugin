package com.pi.main;

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.filter.AbstractFilter;

public class ConsoleFilter extends AbstractFilter {
	private static final long serialVersionUID = 1L;
	
	public ConsoleFilter() {}
	
	public Result filter(LogEvent arg0) {
		if (arg0.getMessage().getFormattedMessage().toLowerCase().contains("/pi")) {
			return Result.DENY;
		}
		try {
			if (PI.getInstance().getCommandValues().getConsoleFrozen()) {
				return Result.DENY;
			}
		} catch (Exception ex) {
			return Result.ACCEPT;
		}
		return Result.ACCEPT;
	}
	
}
