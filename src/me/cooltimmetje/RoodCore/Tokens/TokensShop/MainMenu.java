package me.cooltimmetje.RoodCore.Tokens.TokensShop;

import me.cooltimmetje.RoodCore.Main;
import me.cooltimmetje.RoodCore.Methods;
import me.cooltimmetje.RoodCore.Tokens.TokensGiver;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

/**
 * This class has been created on 12-3-2015 at 16:11 by cooltimmetje.
 */
public class MainMenu implements Listener,CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if(cmd.getLabel().equalsIgnoreCase("tokenshop")){
            openMain(p);
        }
        return true;
    }

    public static void openMain(Player p){
        Inventory inv = Bukkit.createInventory(null, 36, "&7&oTokens Shop".replace('&', '§'));

        Methods.createDisplay(Material.NETHER_STAR, 1, 0, "&a&lRankup", "&7&oClick here to buy a rankup!", inv, 14);
        Methods.createDisplay(Material.JUKEBOX, 1, 0, "&a&lJoin Sounds", "&7&oComing SoonTM", inv, 13);
        Methods.createDisplay(Material.CHEST, 1, 0, "&a&lOther stuff", "&7&oComing SoonTM", inv, 15);
//        Methods.createDisplay(Material.CHEST, 1, 0, "&a&lOther stuff", "&7&oClick here to buy other cool stuff!", inv, 12);

        Methods.createDisplay(Material.GOLD_NUGGET, 1, 0, "&bYour Tokens: &9" + TokensGiver.tokensAmount.get(p.getName()), null, inv, 32);
        p.openInventory(inv);
    }

    @EventHandler
    public void onInvClick(InventoryClickEvent e){
        if(!ChatColor.stripColor(e.getInventory().getName()).equalsIgnoreCase("Tokens Shop"))
            return;

        Player p = (Player) e.getWhoClicked();
        e.setCancelled(true);

        if(!e.getCurrentItem().hasItemMeta()){
            return;
        }

        switch (e.getCurrentItem().getType()){
            case NETHER_STAR:
                p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 100, 1);
                Rankup.openRankUp(p);
                break;
            case GOLD_NUGGET:
                break;
            default:
                p.playSound(p.getLocation(), Sound.ITEM_BREAK, 100, 1);
                Methods.msgPlayer(Main.arrowTag + "&cNot available yet...", p);
                break;
        }
    }

}
