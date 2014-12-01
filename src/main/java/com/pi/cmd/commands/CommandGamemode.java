package com.pi.cmd.commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import com.pi.cmd.main.CommandBase;
import com.pi.events.ChangePlayer;
import com.pi.main.PI;

public class CommandGamemode extends CommandBase {

	public CommandGamemode() {
		super("GM", "Gamemode yourself or other players.", "GM <0/1/2/3/creative/survival/adventure/spectator> [player]");
	}
 
	@Override
	public void runCommand(Player p, String[] args) {
		String mode = args[1];
		if (PI.getInstance().getCommandValues().getGms().containsKey(mode.toLowerCase())) {
			GameMode gm = PI.getInstance().getCommandValues().getGms().get(mode.toLowerCase());
			if(args.length == 2) {
				Bukkit.getScheduler().scheduleSyncDelayedTask(PI.getInstance(), new ChangePlayer(p, "gm:" + mode)); 
				PI.addMessage(p, "You are now gamemode " + mode);
	        } else if (args.length == 3) {
				Player v = this.getPlayer(args[1], p);
				if (v == null) {
					PI.addMessage(p, "Player not found, or is verified.");
				}
				else {
					v.setGameMode(gm);
					PI.addMessage(p, v.getName() + " is now gamemode " + mode);
				}
			}
			else {
				PI.addMessage(p, this.getUsage());
			}
		} else {
			PI.addMessage(p, "Unknown gamemode.");
		}
	}
}