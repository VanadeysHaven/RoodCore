package me.cooltimmetje.RoodCoreOld.JoinSounds;

import me.cooltimmetje.RoodCoreOld.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;

/**
 * This class has been created on 10-3-2015 at 15:44 by cooltimmetje.
 */
public class ShopGUI implements Listener {

    public static HashMap<String, Integer> joinSound = new HashMap<String, Integer>();

    public static void openShop(Player p){
        Inventory inv = Bukkit.createInventory(null, 27, "&7&oJoin Sounds".replace('&', '§'));

        Main.createDisplay(Material.RECORD_11, 1, 0, "&2&oDVBBS and Borgeous &b- &e&oTsunami", "&9Cost: 75 tokens", inv, 12);
        Main.createDisplay(Material.GOLD_RECORD, 1, 0, "&2&oDarude &b- &e&oSandstorm", "&c&oCooltimmetje Exclusive", inv, 13);

        Main.createDisplay(Material.GREEN_RECORD, 1, 0, "&2&oK3 &b- &e&oMama se, Mama sa", "&9Cost: 75 tokens", inv, 15);
        Main.createDisplay(Material.RECORD_3, 1, 0, "&2&oNew Kids &b- &e&oTurbo (ft. DJ Paul Elstak)", "&9Cost: 75 tokens", inv, 16);

        p.openInventory(inv);
    }

    @EventHandler
    @SuppressWarnings("unused")
    public void onInventoryClick(InventoryClickEvent event){

        if(!ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("Join Sounds"))
            return;

        Player p = (Player) event.getWhoClicked();
        event.setCancelled(true);

        if(!event.getCurrentItem().hasItemMeta()){
            return;
        }

        switch (event.getCurrentItem().getType()){
            default:
                break;
        }
    }

}
