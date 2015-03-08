package me.cooltimmetje.RoodCore;

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
            Main.msgPlayer("&3&m-----&r &9&lCooldowns &3&m-----", p);
            Main.msgPlayer("&9command &b- &etotal cooldown &b- &2your cooldown", p);
            Main.msgPlayer("&7&oYour current cooldowns:", p);
            Main.msgPlayer(TimeCommandGUI.cooldownCheckList(p), p);
        }
        return true;
    }

}
