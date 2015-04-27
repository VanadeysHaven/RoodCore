package me.cooltimmetje.RoodCore.Tokens;

import me.cooltimmetje.RoodCore.Managers.ConfigManager;
import me.cooltimmetje.RoodCore.Methods;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * This class has been created on 26-4-2015 at 13:30 by cooltimmetje.
 */
public class MassTokensCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("masstokens")) {
            if(p.getName().equals("Cooltimmetje")){
                if(args.length == 1){
                    if(Methods.isInt(args[0])){
                        int amount = Integer.parseInt(args[0]);
                        for(Player pl : Bukkit.getOnlinePlayers()){
                            int curTokens = TokensGiver.tokensAmount.get(pl.getName());
                            curTokens = curTokens + amount;
                            Methods.msgPlayer("&9+" + amount + " tokens! (Some love from " + p.getDisplayName() + "&9 &4\u2665&9)", pl);
                            Methods.sendAction("&9+" + amount + " tokens! (Some love from " + p.getDisplayName() + "&9 &4\u2665&9)", pl);
                            p.playSound(p.getLocation(), Sound.NOTE_PIANO, 100, 1);
                            TokensGiver.tokensAmount.put(pl.getName(), curTokens);
                            ConfigManager.saveData(pl);
                        }
                        Methods.Broadcast("&9Tokens> " + p.getDisplayName() + "&a gave everybody on the server &9" + amount + " tokens! &4\u2665");
                    } else {
                        Methods.msgPlayer("&cPlease provide a number!", p);
                    }
                } else {
                    Methods.msgPlayer("&cWrong arguments", p);
                }
            } else {
                Methods.msgPlayer("&cnee" ,p);
            }
        }
        return true;
    }

}
