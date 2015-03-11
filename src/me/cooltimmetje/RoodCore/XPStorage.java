package me.cooltimmetje.RoodCore;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

@SuppressWarnings("unused")
public class XPStorage implements Listener {

    @EventHandler
    public void onItemInteract(PlayerInteractEvent event){
        Player p = event.getPlayer();
        if(p.getItemInHand().getType() == Material.GLASS_BOTTLE){
            if(event.getAction() == Action.RIGHT_CLICK_AIR){
                if(event.getPlayer().isSneaking() == true){
                    event.setCancelled(true);
                    if(p.getLevel() >= 1){
                        p.getInventory().removeItem(new ItemStack(Material.GLASS_BOTTLE, 1));
                        p.getInventory().addItem(new ItemStack(Material.EXP_BOTTLE, 1));
                        Methods.msgPlayer("&9XP> &aYou putted a level in to the bottle.", p);
                        p.setLevel(p.getLevel() - 1);
                    } else {
                        Methods.msgPlayer("&9XP> &a&oYou need at least 1 level to store it.", p);
                        return;
                    }
                } else {
                    return;
                }
            } else {
                return;
            }
        } else if (p.getItemInHand().getType() == Material.EXP_BOTTLE) {
            if(event.getAction() == Action.RIGHT_CLICK_AIR){
                event.setCancelled(true);
                p.getInventory().removeItem(new ItemStack(Material.EXP_BOTTLE, 1));
                p.getInventory().addItem(new ItemStack(Material.GLASS_BOTTLE, 1));
                Methods.msgPlayer("&9XP> &aYou took a level out of the bottle.", p);
                p.setLevel(p.getLevel() + 1);
            } else {
                return;
            }

        }

    }
}