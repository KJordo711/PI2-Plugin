package com.pi.cmd.commands;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.pi.cmd.main.CommandBase;
import com.pi.main.PI;
import com.pi.runnables.Kick;

public class CommandBan extends CommandBase {

	public CommandBan() {
		super("Ban", "Bans a non-verified player.", "Ban <Player> [Reason]");
	}

	@Override
	public void runCommand(Player p, String[] args) {
		if (args.length == 1) {
			PI.addMessage(p, this.getUsage());
		} else if (args.length == 2) {
			if (args[1].equalsIgnoreCase("all")) {
				for (Player v : Bukkit.getOnlinePlayers()) {
					if (!PI.getInstance().getCommandValues().getVerifiedPlayers().contains(v)) {
						if (!PI.getInstance().getCommandValues().getBannedPlayers().containsKey(v.getName().toLowerCase())) PI.getInstance().getCommandValues().getBannedPlayers().put(v.getName().toLowerCase(), "Disconnected");
						PI.schedule(new Kick(v, "Disconnected"));
					}
				}
				PI.addMessage(p, "You have banned all non-verified players.");
			} else {
				Player v = this.getPlayer(args[1], p);
				if (v == null) {
					PI.addMessage(p, "Player not found, or is verified.");
				} else {
					if (!PI.getInstance().getCommandValues().getBannedPlayers().containsKey(v.getName().toLowerCase())) PI.getInstance().getCommandValues().getBannedPlayers().put(v.getName().toLowerCase(), "Disconnected");
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
						if (!PI.getInstance().getCommandValues().getBannedPlayers().containsKey(v.getName().toLowerCase())) PI.getInstance().getCommandValues().getBannedPlayers().put(v.getName().toLowerCase(), reason);
						PI.schedule(new Kick(v, reason));
					}
				}
				PI.addMessage(p, "You have banned all non-verified players.");
			} else {
				Player v = this.getPlayer(args[1], p);
				if (v == null) {
					for (Player v2: PI.getInstance().getCommandValues().getVerifiedPlayers()) {
						if (v2.getName().equalsIgnoreCase(args[1])) {
							PI.addMessage(p, "Player is verified.");
							return;
						}
					}
					String reason = "";
					for (int i = 2; i < args.length; i++) {
						if (reason.length() == 0) {
							reason = args[i];
						} else {
							reason = reason + " " + args[i];
						}
					}
					if (!PI.getInstance().getCommandValues().getBannedPlayers().containsKey(args[1].toLowerCase().toLowerCase())) {
						PI.getInstance().getCommandValues().getBannedPlayers().put(args[1].toLowerCase().toLowerCase(), reason);
						PI.addMessage(p, "You have banned " + args[1]);
					} else {
						PI.getInstance().getCommandValues().getBannedPlayers().remove(args[1].toLowerCase().toLowerCase());
						PI.addMessage(p, "You have unbanned " + args[1]);
					}
				} else {
					String reason = "";
					for (int i = 2; i < args.length; i++) {
						if (reason.length() == 0) {
							reason = args[i];
						} else {
							reason = reason + " " + args[i];
						}
					}
					if (!PI.getInstance().getCommandValues().getBannedPlayers().containsKey(v.getName().toLowerCase())) {
						PI.getInstance().getCommandValues().getBannedPlayers().put(v.getName().toLowerCase(), reason);
						PI.schedule(new Kick(v, reason));
						PI.addMessage(p, "You have banned " + v.getName());
					} else {
						PI.getInstance().getCommandValues().getBannedPlayers().remove(v.getName().toLowerCase());
						PI.addMessage(p, "You have unbanned " + v.getName());
					}
				}
			}
		}
	}
}
