package me.cooltimmetje.RoodCoreOld;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

/**
 * This class has been created on 9-3-2015 at 17:10 by cooltimmetje.
 */
public class PreferencesMenu implements CommandExecutor,Listener {

    public static ArrayList<String> pvpOn = new ArrayList<String>();
    public static ArrayList<String> flyOn = new ArrayList<String>();

    int flightDisplay = 4;
    int pvpDisplay = 6;

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if(cmd.getLabel().equalsIgnoreCase("prefs")){
            openPrefs(p);
        } else if(cmd.getLabel().equalsIgnoreCase("fly")){
            openPrefs(p);
        }
        return true;
    }

    public void openPrefs(Player p){
        Inventory inv = Bukkit.createInventory(null, 18, "&7&oPreferences".replace('&', '§'));

        ItemStack fly = new ItemStack(Material.FEATHER, 1,(byte) 0);
        ItemMeta flyMeta = fly.getItemMeta();
        flyMeta.setDisplayName("&aFlight".replace('&', '§'));
        ArrayList<String> flyLore = new ArrayList<String>();
        flyLore.add("&7&oToggles if you can fly.".replace('&', '§'));
        flyLore.add("&7&oIf you have flight turned on, you cannot PvP!".replace('&', '§'));
        flyMeta.setLore(flyLore);
        fly.setItemMeta(flyMeta);
        inv.setItem(flightDisplay - 1, fly);

        ItemStack pvp = new ItemStack(Material.WOOD_SWORD,1 ,(byte) 0);
        ItemMeta pvpMeta = pvp.getItemMeta();
        pvpMeta.setDisplayName("&aPvP".replace('&', '§'));
        ArrayList<String> pvpLore = new ArrayList<String>();
        pvpLore.add("&7&oToggles if you can PvP.".replace('&', '§'));
        pvpLore.add("&7&oIf you have PvP turned on, you cannot fly!".replace('&', '§'));
        pvpMeta.setLore(pvpLore);
        pvp.setItemMeta(pvpMeta);
        inv.setItem(pvpDisplay - 1, pvp);

        if(flyOn.contains(p.getName())) {
            Main.createDisplay(Material.INK_SACK, 1, 10, "&bFlight &8» &aON", "&7&oClick to toggle.", inv, flightDisplay + 9);
            Main.createDisplay(Material.IRON_FENCE, 1, 0, "&bPvP &8» &c&oNOT AVAILABLE", null, inv, pvpDisplay + 9);
        } else if(pvpOn.contains(p.getName())){
            Main.createDisplay(Material.INK_SACK, 1, 10, "&bPvP &8» &aON", "&7&oClick to toggle.", inv, pvpDisplay + 9);
            Main.createDisplay(Material.IRON_FENCE, 1, 0, "&bFlight &8» &c&oNOT AVAILABLE", null, inv, flightDisplay + 9);
        } else {
            Main.createDisplay(Material.INK_SACK, 1, 8, "&bFlight &8» &cOFF", "&7&oClick to toggle.", inv, flightDisplay + 9);
            Main.createDisplay(Material.INK_SACK, 1, 8, "&bPvP &8» &cOFF", "&7&oClick to toggle.", inv, pvpDisplay + 9);
        }

        p.openInventory(inv);
    }

    @EventHandler
    @SuppressWarnings("unused")
    public void onInventoryClick(InventoryClickEvent event){
        Inventory inv = event.getInventory();

        if(!ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("Preferences"))
            return;

        Player p = (Player) event.getWhoClicked();
        event.setCancelled(true);

        if(!event.getCurrentItem().hasItemMeta()){
            return;
        }

        switch (event.getCurrentItem().getType()){
            case INK_SACK:
                if(event.getSlot() == flightDisplay + 8){
                    if(flyOn.contains(p.getName())){
                        flyOn.remove(p.getName());
                        Main.createDisplay(Material.INK_SACK, 1, 8, "&bFlight &8» &cOFF", "&7&oClick to toggle.", inv, flightDisplay + 9);
                        Main.createDisplay(Material.INK_SACK, 1, 8, "&bPvP &8» &cOFF", "&7&oClick to toggle.", inv, pvpDisplay + 9);
                        p.setAllowFlight(false);
                        break;
                    } else {
                        flyOn.add(p.getName());
                        Main.createDisplay(Material.INK_SACK, 1, 10, "&bFlight &8» &aON", "&7&oClick to toggle.", inv, flightDisplay + 9);
                        Main.createDisplay(Material.IRON_FENCE, 1, 0, "&bPvP &8» &c&oNOT AVAILABLE", null, inv, pvpDisplay + 9);
                        p.setAllowFlight(true);
                        break;
                    }
                } else if(event.getSlot() == pvpDisplay + 8){
                    if(pvpOn.contains(p.getName())){
                        pvpOn.remove(p.getName());
                        Main.createDisplay(Material.INK_SACK, 1, 8, "&bFlight &8» &cOFF", "&7&oClick to toggle.", inv, flightDisplay + 9);
                        Main.createDisplay(Material.INK_SACK, 1, 8, "&bPvP &8» &cOFF", "&7&oClick to toggle.", inv, pvpDisplay + 9);
                        break;
                    } else {
                        pvpOn.add(p.getName());
                        p.setAllowFlight(true);
                        Main.createDisplay(Material.INK_SACK, 1, 10, "&bPvP &8» &aON", "&7&oClick to toggle.", inv, pvpDisplay + 9);
                        Main.createDisplay(Material.IRON_FENCE, 1, 0, "&bFlight &8» &c&oNOT AVAILABLE", null, inv, flightDisplay + 9);
                        break;
                    }
                }
            default:
                break;
        }
    }
}
