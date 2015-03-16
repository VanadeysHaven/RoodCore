package me.cooltimmetje.RoodCore;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

/**
 * This class has been created on 14-3-2015 at 21:22 by cooltimmetje.
 */
public class ScoreboardToggle implements CommandExecutor {

    public static ArrayList<String> myRoodOff = new ArrayList<String>();

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        final Player p = (Player) sender;
        if(cmd.getLabel().equalsIgnoreCase("myrood")){
            if(myRoodOff.contains(p.getName())){
                myRoodOff.remove(p.getName());
                Methods.loadScoreboard(p);
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
                    @Override
                    public void run() {
                        Methods.updateScoreboard(p);
                    }
                }, 60);
            } else {
                myRoodOff.add(p.getName());
                Methods.removeScoreboard(p);
            }
        }
        return true;
    }

}
