package me.cooltimmetje.RoodCore;

import me.cooltimmetje.RoodCore.GUIs.TimeCommandGUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * This class has been created on 7-3-2015 at 18:54 by cooltimmetje.
 */
public class CooldownList implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if (cmd.getLabel().equalsIgnoreCase("cooldown")) {
            Methods.msgPlayer("&3&m-----&r &9&lCooldowns &3&m-----", p);
            Methods.msgPlayer("&9command &b- &etotal cooldown &b- &2your cooldown", p);
            Methods.msgPlayer("&7&oYour current cooldowns:", p);
            Methods.msgPlayer(TimeCommandGUI.cooldownCheckList(p), p);
            Methods.msgPlayer(StaffPesten.cooldownCheckListTim(), p);
            Methods.msgPlayer(StaffPesten.cooldownCheckListRood(), p);
            Methods.msgPlayer(FireworkRide.cooldownCheckList(p), p);
        }
        return true;
    }

}
