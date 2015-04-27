package me.cooltimmetje.RoodCore.Teleport.Request;

import me.cooltimmetje.RoodCore.Methods;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * This class has been created on 26-4-2015 at 12:10 by cooltimmetje.
 */
public class TpaAcceptCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("tpaccept")) {
            if(TpaData.targetPlayer.containsKey(p.getName())){
                TpaTeleport.tpaPlayer(Bukkit.getPlayer(TpaData.targetPlayer.get(p.getName())), Bukkit.getPlayer(p.getName()));
            } else {
                Methods.msgPlayerTag("Tpa", "You do not have a pending request.", p);
            }
        }
        return true;
    }

}
