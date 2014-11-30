package com.pi.cmd.commands;

import java.util.List;

import org.bukkit.entity.Player;

import com.pi.cmd.main.CommandBase;
import com.pi.cmd.main.CommandLoader;
import com.pi.main.PI;

public class CommandHelp extends CommandBase {

	public CommandHelp() {
		super("Help", "Shows all help commands.", "Help <PageNumber/Command>");
	}

	@Override
	public void runCommand(Player p, String[] args) {
        if (args.length == 1) {
			PI.addMessage(p, "========PIR Help Menu (1 / " + getMaxHelpPages(PI.getInstance().getSettings().getLoadedCommandClasses()) + ")========");
			for(int i = 0; i < PI.getInstance().getSettings().getLoadedCommandClasses().size() && i < 7; i++) {
                PI.addMessage(p, PI.getInstance().getSettings().getLoadedCommandClasses().get(i).getCommand() + " - " + PI.getInstance().getSettings().getLoadedCommandClasses().get(i).getDescription());
            }
		} else if (args.length == 2) {
			for (CommandBase cb : CommandLoader.commandsArray) {
				if (cb.getCommand().equalsIgnoreCase(args[1])) {
					PI.addMessage(p, "-------" + cb.getCommand()
							+ " Help-------");
					PI.addMessage(p, "Command: " + cb.getCommand());
					PI.addMessage(p, "Description: " + cb.getDescription());
					PI.addMessage(p, "Usage: " + cb.getUsage());
					return;
				}
			}
			if(isInteger(args[1])) {
                int helpPage = Integer.parseInt(args[1]);
                int oneLess = helpPage - 1;
                int startValue = oneLess * 7;
                int endValue = helpPage * 7;
                PI.addMessage(p, "========PIR Help Menu (" + helpPage + "/ " + getMaxHelpPages(PI.getInstance().getSettings().getLoadedCommandClasses()) + ")========");
                for(int i = startValue; i < endValue; i++) {
                    PI.addMessage(p, PI.getInstance().getSettings().getLoadedCommandClasses().get(i).getCommand() + " - " + PI.getInstance().getSettings().getLoadedCommandClasses().get(i).getDescription());
                }
            } else {
            	PI.addMessage(p, "Unknown command.");
            }
		} else {
			PI.addMessage(p, "========PIR Help Menu (1 / " + getMaxHelpPages(PI.getInstance().getSettings().getLoadedCommandClasses()) + ")========");
			for(int i = 0; i < 7; i++) {
				if((PI.getInstance().getSettings().getLoadedCommandClasses().size() - 1) >= i) {
					PI.addMessage(p, PI.getInstance().getSettings().getLoadedCommandClasses().get(i).getCommand() + " - " + PI.getInstance().getSettings().getLoadedCommandClasses().get(i).getDescription());
				} else {
					break;
				}
			}
		}
	}

    boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException e) {
            return false;
        }
        return true;
    }

    int getMaxHelpPages(List<CommandBase> list) {
    	if (list.size() % 7 == 0) {
    		return list.size() / 7;
    	} else {
    		return list.size() / 7 + 1;
    	}
    }
}