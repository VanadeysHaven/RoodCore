package me.cooltimmetje.RoodCore;

import com.evilmidget38.UUIDFetcher;
import io.puharesource.mc.titlemanager.api.ActionbarTitleObject;
import io.puharesource.mc.titlemanager.api.TitleObject;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * This class has been created on 10-3-2015 at 17:10 by cooltimmetje.
 */

@SuppressWarnings("unused")
public class Methods {

    public static void createDisplay(Material m, int amount, int data, String name, String lore, Inventory inv, int slotNumber){
        ItemStack item = new ItemStack(m, amount,(byte) data);
        ItemMeta itemMeta = item.getItemMeta();
        if(!(name == null)){
            itemMeta.setDisplayName(name.replace('&', '§'));
        }
        if(!(lore == null)){
            ArrayList<String> Lore = new ArrayList<String>();
            Lore.add(lore.replace('&', '§'));
            itemMeta.setLore(Lore);
        }
        item.setItemMeta(itemMeta);
        inv.setItem(slotNumber - 1, item);
    }

    public static int randomInt(int min, int max){
        Random rand = new Random();

        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

    public static String getGroup(Player p){
        String displayName = p.getDisplayName();

        int i = displayName.indexOf(' ');
        String groupName = displayName.substring(0, i);

        groupName = ChatColor.stripColor(groupName.replace('[', ' ').replace(']', ' ').replace("'", " ").replace("+", "Plus").trim());

        return groupName;
    }

    public static void sendTitle(String title, String subTitle, int fadeIn, int stay, int fadeOut, Player p){
        new TitleObject(title.replace('&', '§'), subTitle.replace('&', '§')).setFadeIn(fadeIn).setStay(stay).setFadeOut(fadeOut).send(p);
    }

    public static void sendMain(String title, int fadeIn, int stay, int fadeOut, Player p){
        new TitleObject(title.replace('&', '§'), TitleObject.TitleType.TITLE).setFadeIn(fadeIn).setStay(stay).setFadeOut(fadeOut).send(p);
    }

    public static void sendSub(String title, int fadeIn, int stay, int fadeOut, Player p){
        new TitleObject(title.replace('&', '§'), TitleObject.TitleType.SUBTITLE).setFadeIn(fadeIn).setStay(stay).setFadeOut(fadeOut).send(p);
    }

    public static void sendAction(String title, Player p){
        new ActionbarTitleObject(title.replace('&', '§')).send(p);
    }

    public static void Broadcast(String msg){
        Bukkit.broadcastMessage(msg.replace('&', '§'));
    }

    public static void msgPlayer(String msg, Player p){
        p.sendMessage(msg.replace('&', '§'));
    }

    public static String getUUID(Player p){
        String name, uuid = null;
        name = p.getName();
        try {
            uuid = new UUIDFetcher(Arrays.asList(name)).call().get(name).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return uuid;
    }

}
