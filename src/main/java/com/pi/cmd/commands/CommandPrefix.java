package com.pi.cmd.commands;

import org.bukkit.entity.Player;

import com.pi.cmd.main.CommandBase;
import com.pi.main.PI;

public class CommandPrefix extends CommandBase {

	public CommandPrefix() {
		super("Prefix", "Allows you to change the command prefix.", "Prefix <prefix>");
	}

	@Override
	public void runCommand(Player p, String[] args) {
		try {
			if(args.length == 1) {

				if(PI.getInstance().getSettings().getCustomPrefixes().get(p.getName()) != null) {
					PI.addMessage(p, "Prefix: " + PI.getInstance().getSettings().getCustomPrefixes().get(p.getName()));
				} else {
					PI.addMessage(p, "Prefix: " + PI.getInstance().getSettings().getPrefix());
				}
			} else if(args.length == 2) {
				String prefix = args[1];
				if(PI.getInstance().getSettings().getCustomPrefixes().containsKey(p.getName())) {
					PI.getInstance().getSettings().getCustomPrefixes().remove(p.getName());
				}
				
				PI.addMessage(p, "You have changed the command prefix to " + args[1]);
				PI.getInstance().getSettings().getCustomPrefixes().put(p.getName(), prefix);
			} else {
				PI.addMessage(p, this.getUsage());
			}
		} catch (Exception e) {
			PI.addMessage(p, this.getUsage());
		}
	}
}
