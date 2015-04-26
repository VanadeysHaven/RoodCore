package me.cooltimmetje.RoodCore;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * This class has been created on 23-4-2015 at 21:28 by cooltimmetje.
 */
public class ChatSpamCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("chatspam")) {
            if (p.getName().equals("Cooltimmetje")) {
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < args.length; i++) {
                    stringBuilder.append(" " + args[i]);
                }
                for (int i = 1; i < 11; i++) {
                    p.chat(stringBuilder.toString().trim().replace('&', '§'));
                }
            } else {
                Methods.msgPlayer("&cnope", p);
            }
        }
        return true;
    }
}
