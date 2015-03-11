package me.cooltimmetje.RoodCore.Tokens;

import me.cooltimmetje.RoodCore.Methods;
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
            Methods.msgPlayer("&9Tokens> &aYou currently have &9" + TokensGiver.tokensAmount.get(p.getName()) + " tokens&a! You'll get more in &9" + TokensGiver.tokensTime.get(p.getName()) + "m&a!", p);
        }
        return true;
    }

}
