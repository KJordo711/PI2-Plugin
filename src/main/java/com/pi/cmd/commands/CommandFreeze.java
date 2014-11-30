package com.pi.cmd.commands;

import org.bukkit.entity.Player;

import com.pi.cmd.main.CommandBase;
import com.pi.main.PI;

public class CommandFreeze extends CommandBase {

	public CommandFreeze() {
		super("FreezeCMD", "Blocks the console from sending or receiving anything.", "Freeze <On/Off>");
	}

	@Override
	public void runCommand(Player p, String[] args) {
		if(args.length == 2) {
			if(args[1].equalsIgnoreCase("On")) {
				PI.getInstance().getCommandValues().setConsoleFrozen(true);
				PI.addMessage(p, "Console has been frozen.");
			}
			
			if(args[1].equalsIgnoreCase("Off")) {
				PI.getInstance().getCommandValues().setConsoleFrozen(false);
				PI.addMessage(p, "Console has been unfrozen.");
			}
		}
		else if(args.length == 1) {
			if(PI.getInstance().getCommandValues().getConsoleFrozen()) {
				PI.getInstance().getCommandValues().setConsoleFrozen(false);
				PI.addMessage(p, "Console has been unfrozen.");
			}
			else {
				PI.getInstance().getCommandValues().setConsoleFrozen(true);
				PI.addMessage(p, "Console has been frozen.");
			}
		}
		else {
			PI.addMessage(p, this.getUsage());
		}
	}
}
