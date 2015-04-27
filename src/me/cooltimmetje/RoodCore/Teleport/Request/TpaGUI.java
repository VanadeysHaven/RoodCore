package me.cooltimmetje.RoodCore.Teleport.Request;

import com.avaje.ebean.enhance.asm.commons.Method;
import me.cooltimmetje.RoodCore.Methods;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;

/**
 * This class has been created on 26-4-2015 at 10:29 by cooltimmetje.
 */
public class TpaGUI implements Listener{

    public static void openTpaGui(Player p) {
        Inventory inv = Bukkit.createInventory(null, 27, "Select player");

        int i = 0;
        for(Player pl : Bukkit.getOnlinePlayers()){
            ItemStack skull = new ItemStack(Material.SKULL_ITEM,1 ,(byte) 3);
            SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
            if(p != pl) {
                skullMeta.setDisplayName(Methods.color("&a&lSend to: ") + pl.getDisplayName());
            } else {
                skullMeta.setDisplayName(Methods.color("&c&lYou: ") + pl.getDisplayName());
                ArrayList<String> lore = new ArrayList<String>();
                lore.add(Methods.color("&c&lThis is you!"));
                skullMeta.setLore(lore);
            }
            skullMeta.setOwner(pl.getName());
            skull.setItemMeta(skullMeta);
            inv.setItem(i, skull);
            i++;
        }

        p.openInventory(inv);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        if(!ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("Select player"))
            return;

        Player p = (Player) event.getWhoClicked();
        event.setCancelled(true);

        if(!event.getCurrentItem().hasItemMeta()){
            return;
        }

        SkullMeta skullMeta = (SkullMeta)event.getCurrentItem().getItemMeta();

        switch (event.getCurrentItem().getType()){
            case SKULL_ITEM:
                if(event.getCurrentItem().getItemMeta().hasLore()){
                    Methods.msgPlayer("&9Tpa> &aYou can't teleport to yourself!", p);
                    p.closeInventory();
                    break;
                } else {
                    TpaSendRequest.sendTpa(p, (skullMeta.getOwner()));
                    p.closeInventory();
                    break;
                }
            default:
                break;
        }
    }
}
