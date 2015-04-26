package me.cooltimmetje.RoodCore.Teleport.Request;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * This class has been created on 26-4-2015 at 10:28 by cooltimmetje.
 */
public class TpaCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("tpanew")){

        }
        return true;
    }

}
