package me.cooltimmetje.RoodCore.Teleport.Request;

import me.cooltimmetje.RoodCore.Methods;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * This class has been created on 26-4-2015 at 11:55 by cooltimmetje.
 */
public class TpaDenyCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("tpdeny")) {
            if(TpaData.targetPlayer.containsKey(p.getName())){
                String pDelete = TpaData.targetPlayer.get(p.getName());
                Player pl = Bukkit.getPlayer(pDelete);
                Methods.msgPlayerTag("Tpa", "Request &cdenied&a!", p);
                Methods.msgPlayerTag("Tpa", p.getDisplayName() + " &cdenied your request!", pl);
                TpaData.playerTarget.remove(pDelete);
                TpaData.targetPlayer.remove(p.getName());
            } else {
                Methods.msgPlayerTag("Tpa", "You do not have a pending request.", p);
            }
        }
        return true;
    }

}
