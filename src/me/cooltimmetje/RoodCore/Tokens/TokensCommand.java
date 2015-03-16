package me.cooltimmetje.RoodCore.Tokens;

import me.cooltimmetje.RoodCore.Methods;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * This class has been created on 10-3-2015 at 21:31 by cooltimmetje.
 */
public class TokensCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if(cmd.getLabel().equalsIgnoreCase("tokens")){
            if(args.length == 1){
                if(p.hasPermission("roodcore.tokens.other")) {
                    Player pl = Bukkit.getPlayer(args[0]);
                    if (pl != null) {
                        Methods.msgPlayer("&9Tokens> &c" + pl.getName() + " &acurrently has &9" + TokensGiver.tokensAmount.get(pl.getName()) + " tokens&a!", p);
                        return true;
                    } else {
                        Methods.msgPlayer("&9Tokens> &aYou currently have &9" + TokensGiver.tokensAmount.get(p.getName()) + " tokens&a! You'll get more in &9" +
                                TokensGiver.tokensTime.get(p.getName()) + "m&a!", p);
                        return true;
                    }
                } else {
                    Methods.msgPlayer("&9Tokens> &aYou currently have &9" + TokensGiver.tokensAmount.get(p.getName()) + " tokens&a! You'll get more in &9" +
                            TokensGiver.tokensTime.get(p.getName()) + "m&a!", p);
                    return true;
                }
            }
            Methods.msgPlayer("&9Tokens> &aYou currently have &9" + TokensGiver.tokensAmount.get(p.getName()) + " tokens&a! You'll get more in &9" +
                    TokensGiver.tokensTime.get(p.getName()) + "m&a!", p);
            return true;
        }
        return true;
    }

}
