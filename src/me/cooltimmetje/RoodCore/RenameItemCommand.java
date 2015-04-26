package me.cooltimmetje.RoodCore;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * This class has been created on 16-3-2015 at 19:08 by cooltimmetje.
 */
public class RenameItemCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if(cmd.getName().equalsIgnoreCase("rename")){
            if(p.hasPermission("roodcore.rename")){
                if(args.length >= 1){
                    String name = Methods.buildArg(args, 0);
                    ItemStack i = p.getItemInHand();
                    ItemMeta im = i.getItemMeta();
                    im.setDisplayName(name.replace('&', '§'));
                    i.setItemMeta(im);
                    Methods.msgPlayer("&9Rename> &aThe item in your hand has been renamed to: &f" + name, p);
                } else {
                    Methods.msgPlayer("&9Rename> &aYou need to specify a name! &oColor codes are supported!", p);
                }
            } else {
                    Methods.msgPlayer("&9Rename> &aYou need to be &8[&5Makkum&8] &aor higher to do this.", p);
            }
        }
        return true;
    }

}
