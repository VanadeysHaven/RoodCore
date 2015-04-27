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
 * This class has been created on 26-4-2015 at 14:24 by cooltimmetje.
 */
public class GiveTokensCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("givetokens")) {
            if(p.getName().equals("Cooltimmetje")){
                if(args.length >= 3){
                    Player pl = Bukkit.getPlayer(args[0]);
                    if(pl != null) {
                        if (Methods.isInt(args[1])) {
                            int amount = Integer.parseInt(args[1]);
                            StringBuilder sb = new StringBuilder();
                            for(int i=2; i<args.length; i++) {
                                sb.append(" " + args[i]);
                            }
                            int curTokens = TokensGiver.tokensAmount.get(p.getName());
                            curTokens = curTokens + amount;
                            Methods.msgPlayer("&9+" + amount + " tokens! (" + sb.toString().trim() +"&9)", pl);
                            Methods.sendAction("&9+" + amount + " tokens! (" + sb.toString().trim() +"&9)", pl);
                            pl.playSound(p.getLocation(), Sound.NOTE_PIANO, 100, 1);
                            TokensGiver.tokensAmount.put(p.getName(), curTokens);
                            ConfigManager.saveData(pl);
                            Methods.msgPlayer("&9Tokens> " + p.getDisplayName() + "&a gave you &9" + amount + " tokens&a!", pl);
                            Methods.msgPlayer("&9Tokens> " + "&aYou gave " + pl.getDisplayName() + " &9" + amount + " tokens&a!", p);
                        } else {
                            Methods.msgPlayer("&cPlease provide a number!", p);
                        }
                    } else {
                        Methods.msgPlayer("&cInvailid player", p);
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
