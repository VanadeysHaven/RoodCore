package me.cooltimmetje.RoodCore;

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

import java.util.HashMap;

/**
 * This class has been created on 7-3-2015 at 16:44 by cooltimmetje.
 */
@SuppressWarnings("unused")
public class TimeCommandGUI implements CommandExecutor,Listener {

    public static HashMap<Integer, Long> timeDataMap = new HashMap<Integer, Long>();
    public static HashMap<Integer, String> timeNameMap = new HashMap<Integer, String>();
    public static HashMap<String, Long> cooldown = new HashMap<String, Long>();
    public static int cdtime = 180;

    public void timeGUI(Player p) {
        if(timeDataMap.isEmpty()){
            timeDataMap.put(5,(long) 0);
            timeDataMap.put(4,(long) 6000);
            timeDataMap.put(1,(long) 12000);
            timeDataMap.put(15,(long) 18000);
        }

        if(timeNameMap.isEmpty()){
            timeNameMap.put(5, "day");
            timeNameMap.put(4, "noon");
            timeNameMap.put(1, "dusk");
            timeNameMap.put(15, "midnight");
        }
        Inventory time = Bukkit.createInventory(null, 18, "&7&oTime and Weather".replace('&', '§'));

        String timeName = null;
        int timeData = 0;

        long currentTime = Bukkit.getWorld("Survival").getTime();
        if (currentTime >= 21000 || currentTime < 3000) {
            timeName = "day";
            timeData = 5;
        } else if (currentTime >= 3000 && currentTime < 9000) {
            timeName = "noon";
            timeData = 4;
        } else if (currentTime >= 9000 && currentTime < 15000) {
            timeName = "dusk";
            timeData = 1;
        } else if (currentTime >= 15000 && currentTime < 21000) {
            timeName = "midnight";
            timeData = 15;
        }

        Main.createDisplay(Material.STAINED_GLASS_PANE, 1, 5, "&a&lSet time to: &bday", null, time, 1);
        Main.createDisplay(Material.STAINED_GLASS_PANE, 1, 4, "&a&lSet time to: &bnoon", null, time, 2);
        Main.createDisplay(Material.STAINED_GLASS_PANE, 1, 1, "&a&lSet time to: &bdusk", null, time, 3);
        Main.createDisplay(Material.STAINED_GLASS_PANE, 1, 15, "&a&lSet time to: &bmidnight", null, time, 4);

        if (timeData == 5) {
            Main.createDisplay(Material.STAINED_GLASS_PANE, 1, 5, "&a&lSet time to: &bday", "&c&lCurrent Time", time, 1);
        } else if (timeData == 4) {
            Main.createDisplay(Material.STAINED_GLASS_PANE, 1, 4, "&a&lSet time to: &bnoon", "&c&lCurrent Time", time, 2);
        } else if (timeData == 1) {
            Main.createDisplay(Material.STAINED_GLASS_PANE, 1, 1, "&a&lSet time to: &bdusk", "&c&lCurrent Time", time, 3);
        } else if (timeData == 15) {
            Main.createDisplay(Material.STAINED_GLASS_PANE, 1, 15, "&a&lSet time to: &bmidnight", "&c&lCurrent Time", time, 4);
        }

        Main.createDisplay(Material.WATCH, 1, 0, "&a&lCurrent time: &b" + timeName, "&7&o(" + Bukkit.getWorld("Survival").getTime() + ")", time, 10);

        Main.createDisplay(Material.BUCKET, 1, 0, "&a&lSet weather to: &bclear", null, time, 8);
        Main.createDisplay(Material.WATER_BUCKET, 1, 0, "&a&lSet weather to: &brain", null, time, 9);

        if(Bukkit.getWorld("Survival").isThundering()){
            Main.createDisplay(Material.WATER_BUCKET, 1, 0, "&a&lCurrent weather: &brain", null, time, 18);
            Main.createDisplay(Material.WATER_BUCKET, 1, 0, "&a&lSet weather to: &brain", "&c&lCurrent Weather", time, 9);
        } else {
            Main.createDisplay(Material.BUCKET, 1, 0, "&a&lCurrent weather: &bclear", null, time, 18);
            Main.createDisplay(Material.BUCKET, 1, 0, "&a&lSet weather to: &bclear", "&c&lCurrent Weather", time, 8);
        }

        p.openInventory(time);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        Player p = (Player) sender;
        boolean cooldownOK = cooldownCheck(p);
        if(cooldownOK){
            timeGUI(p);
        }
        return true;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){

        if(!ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("Time and Weather"))
            return;

        Player p = (Player) event.getWhoClicked();
        event.setCancelled(true);

        if(!event.getCurrentItem().hasItemMeta()){
            return;
        }

        switch (event.getCurrentItem().getType()){
            case STAINED_GLASS_PANE:
                Bukkit.getWorld("Survival").setTime(timeDataMap.get((int) event.getCurrentItem().getDurability()));
                p.closeInventory();
                Main.Broadcast("&9Time and Weather> " + p.getDisplayName() + " &achanged the time to &b" + (timeNameMap.get((int)event.getCurrentItem().getDurability())) + "&a.");
                cooldown.put(p.getName(), System.currentTimeMillis());
                break;
            case WATER_BUCKET:
                if(event.getSlot() != 18){
                    Bukkit.getWorld("Survival").setStorm(true);
                    Main.Broadcast("&9Time and Weather> " + p.getDisplayName() + " &achanged the weather to &brain&a.");
                    cooldown.put(p.getName(), System.currentTimeMillis());
                } else {
                    break;
                }
                break;
            case BUCKET:
                if(event.getSlot() != 18){
                    Bukkit.getWorld("Survival").setThundering(false);
                    Bukkit.getWorld("Survival").setStorm(false);
                    Main.Broadcast("&9Time and Weather> " + p.getDisplayName() + " &achanged the weather to &bclear&a.");
                    cooldown.put(p.getName(), System.currentTimeMillis());
                } else {
                    break;
                }
            default:
                break;
        }
    }

    public boolean cooldownCheck(Player p){
       long lastUse = 0;
        if(cooldown.containsKey(p.getName())){
            lastUse = cooldown.get(p.getName());
        }

        int cdmillis = cdtime * 1000;
        if(System.currentTimeMillis() - lastUse >= cdmillis){
            return true;
        } else {
            int seconds = (int) (cdtime - ((System.currentTimeMillis() - lastUse) / 1000));
            int minutes = 0;
            while(seconds >= 60){
                minutes = minutes + 1;
                seconds = seconds - 60;
            }
            Main.msgPlayer("&9Time and Weather> &aThis command is still on cooldown for you. You can use it again in: &c"+ minutes + "m" + seconds + "s", p);
            return false;
        }
    }

    public static String cooldownCheckList(Player p){
        String command = "/time";

        int seconds = cdtime;
        int minutes = 0;
        while(seconds >= 60){
            minutes = minutes + 1;
            seconds = seconds - 60;
        }

        String totalTime = minutes + "m" + seconds + "s";

        long lastUse = 0;
        if(cooldown.containsKey(p.getName())){
            lastUse = cooldown.get(p.getName());
        }

        int cdmillis = cdtime * 1000;
        String timeLeft = "lol";
        if(System.currentTimeMillis() - lastUse >= cdmillis){
            timeLeft = "&2Ready!";
        } else {
            seconds = (int) (cdtime - ((System.currentTimeMillis() - lastUse) / 1000));
            minutes = 0;
            while(seconds >= 60){
                minutes = minutes + 1;
                seconds = seconds - 60;
                timeLeft = "&c" + minutes + "m" + seconds + "s";
            }
        }

        return "&9" + command + " &b- &e" + totalTime + " &b- " + timeLeft;
    }

}
