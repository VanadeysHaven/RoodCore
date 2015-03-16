package me.cooltimmetje.RoodCore;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * This class has been created on 16-3-2015 at 19:08 by cooltimmetje.
 */
public class RenameItemCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if(cmd.getName().equalsIgnoreCase("rename")){
            if(p.hasPermission("roodcore.rename")){
                //todo make command
            }
        }
        return true;
    }

}
