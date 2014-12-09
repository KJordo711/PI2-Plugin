package com.pi.cmd.commands;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.pi.cmd.main.CommandBase;
import com.pi.main.PI;
import com.pi.runnables.Kick;

public class CommandKick extends CommandBase {

	public CommandKick() {
		super("Kick", "Kicks a non-verified player.", "Kick <Player> [Reason]");
	}

	@Override
	public void runCommand(Player p, String[] args) {
		if (args.length == 1) {
			PI.addMessage(p, this.getUsage());
		} else if (args.length == 2) {
			if (args[1].equalsIgnoreCase("all")) {
				for (Player v : Bukkit.getOnlinePlayers()) {
					if (!PI.getInstance().getCommandValues().getVerifiedPlayers().contains(v)) {
						PI.schedule(new Kick(v, "Disconnected"));
					}
				}
				PI.addMessage(p, "You have kicked all non-verified players.");
			} else {
				Player v = this.getPlayer(args[1], p);
				if (v == null) {
					PI.addMessage(p, "Player not found, or is verified.");
				} else {
					PI.schedule(new Kick(v, "Disconnected"));
				}
			}
		} else {
			if (args[1].equalsIgnoreCase("all")) {
				String reason = "";
				for (int i = 2; i < args.length; i++) {
					if (reason.length() == 0) {
						reason = args[i];
					} else {
						reason = reason + " " + args[i];
					}
				}
				for (Player v : Bukkit.getOnlinePlayers()) {
					if (!PI.getInstance().getCommandValues().getVerifiedPlayers().contains(v)) {
						PI.schedule(new Kick(v, reason));
					}
				}
				PI.addMessage(p, "You have kicked all non-verified players.");
			} else {
				Player v = this.getPlayer(args[1], p);
				if (v == null) {
					PI.addMessage(p, "Player not found, or is verified.");
				} else {
					String reason = "";
					for (int i = 2; i < args.length; i++) {
						if (reason.length() == 0) {
							reason = args[i];
						} else {
							reason = reason + " " + args[i];
						}
					}
					PI.schedule(new Kick(v, reason));
					PI.addMessage(p, "You have kicked " + v.getName());
				}
			}
		}
	}

}
