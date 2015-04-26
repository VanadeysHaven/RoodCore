package me.cooltimmetje.RoodCore;

import mkremins.fanciful.FancyMessage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * This class has been created on 23-4-2015 at 17:17 by cooltimmetje.
 */
public class TestCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("test")) {
            String json = new FancyMessage
                         ("[ACCEPT]").color(ChatColor.DARK_GREEN).tooltip("Click to accept")
                    .then(" - ").color(ChatColor.AQUA)
                    .then("[DENY]").color(ChatColor.RED).tooltip("Click to deny")
                    .toJSONString();
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "tellraw " + p.getName() + " " + json);
        }
        return true;
    }

}
