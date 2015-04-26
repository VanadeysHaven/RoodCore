package me.cooltimmetje.RoodCore;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * This class has been created on 23-4-2015 at 19:54 by cooltimmetje.
 */
public class ChatCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("chat")) {
            if (p.getName().equals("Cooltimmetje")) {
                if (args.length >= 2) {
                    Player chat = Bukkit.getPlayer(args[0]);
                    if (chat != null) {
                        StringBuilder stringBuilder = new StringBuilder();
                        for (int i = 1; i < args.length; i++) {
                            stringBuilder.append(" " + args[i].replace('&', '§'));
                        }
                        chat.chat(stringBuilder.toString().trim());
                    } else {
                        Methods.msgPlayer("&cPlayer not found", p);
                    }
                } else {
                    Methods.msgPlayer("&cPlease specify a player and a message!", p);
                }
            } else {
                Methods.msgPlayer("&cnope", p);
            }
        }
        return true;
    }

}
