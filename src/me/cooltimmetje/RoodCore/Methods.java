package me.cooltimmetje.RoodCore;

import com.evilmidget38.UUIDFetcher;
import io.puharesource.mc.titlemanager.api.ActionbarTitleObject;
import io.puharesource.mc.titlemanager.api.TabTitleObject;
import io.puharesource.mc.titlemanager.api.TitleObject;
import me.cooltimmetje.RoodCore.Managers.ResourcePackManager;
import me.cooltimmetje.RoodCore.Tokens.TokensGiver;
import me.cooltimmetje.RoodCore.Tokens.TokensShop.Rankup;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scoreboard.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * This class has been created on 10-3-2015 at 17:10 by cooltimmetje.
 */

@SuppressWarnings("unused")
public class Methods {

    public static ScoreboardManager sbManager = Bukkit.getScoreboardManager();

    public static float getPercentage(int n, int total) {
        float proportion = ((float) n) / ((float) total);
        return proportion * 100;
    }

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

    public static ItemStack createItem(Material m, int amount, int data, String name, String lore){
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
        return item;
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

    public static void updateScoreboard(Player p){
        if(!ScoreboardToggle.myRoodOff.contains(p.getName())) {
            Scoreboard board = sbManager.getNewScoreboard();

            Objective obj = board.registerNewObjective("myrood", "dummy");
            obj.setDisplaySlot(DisplaySlot.SIDEBAR);
            obj.setDisplayName("&c&lMy&4&lR00D".replace('&', '§'));

            Score name = obj.getScore(p.getDisplayName());
            name.setScore(13);

            Score space1 = obj.getScore(" ");
            space1.setScore(12);

            Score tokensTitle = obj.getScore("&9&lTokens: &9&o".replace('&', '§'));
            tokensTitle.setScore(11);

            if(TokensGiver.tokensAmount.get(p.getName()) <= Rankup.rankCost) {
                Score tokensAmount = obj.getScore("&9".replace('&', '§') + TokensGiver.tokensAmount.get(p.getName()) + "&b - &9&o(more in: ".replace('&', '§')
                        + TokensGiver.tokensTime.get(p.getName()) + "m)");
                tokensAmount.setScore(10);
            } else {
                Score tokensAmount = obj.getScore("&9".replace('&', '§') + TokensGiver.tokensAmount.get(p.getName()) + "&b - &2&lRANKUP READY!".replace('&', '§'));
                tokensAmount.setScore(10);
            }

            Score space2 = obj.getScore("  ");
            space2.setScore(9);

            Score killedMobsTitle = obj.getScore("&3&lKilled mobs: &3&o(".replace('&', '§') + TokensGiver.nightTokens + " tokens)");
            killedMobsTitle.setScore(8);

            if (!TokensGiver.killedNight.containsKey(p.getName())) {
                TokensGiver.killedNight.put(p.getName(), 0);
            }
            Score killedMobs = obj.getScore("&3".replace('&', '§') + TokensGiver.killedNight.get(p.getName()) + "/" + TokensGiver.nightKills);
            killedMobs.setScore(7);

            Score space3 = obj.getScore("   ");
            space3.setScore(6);

            Score resourceTitle = obj.getScore("&d&lSelected RP:".replace('&', '§'));
            resourceTitle.setScore(5);

            Score resource = obj.getScore("&d".replace('&', '§') + ResourcePackManager.playerPack.get(p.getName()));
            resource.setScore(4);

            Score space4 = obj.getScore("    ");
            space4.setScore(3);

            Score onlinePlayersTitle = obj.getScore("&a&lOnline players:".replace('&', '§'));
            onlinePlayersTitle.setScore(2);

            Score onlinePlayersAmount = obj.getScore("&a".replace('&', '§') + Bukkit.getOnlinePlayers().size());
            onlinePlayersAmount.setScore(1);

            p.setScoreboard(board);
        }
    }

    public static void loadScoreboard(Player p){
        if(!ScoreboardToggle.myRoodOff.contains(p.getName())) {
            Scoreboard board = sbManager.getNewScoreboard();

            Objective obj = board.registerNewObjective("myrood", "dummy");
            obj.setDisplaySlot(DisplaySlot.SIDEBAR);
            obj.setDisplayName("&c&lMy&4&lR00D &7&o(loading...)".replace('&', '§'));

            Score loading = obj.getScore("&7&oLoading scoreboard...".replace('&', '§'));
            loading.setScore(2);

            Score please = obj.getScore("&7&oPlease wait...".replace('&', '§'));
            please.setScore(1);

            p.setScoreboard(board);
        }
    }

    public static void removeScoreboard(Player p){
        p.setScoreboard(sbManager.getNewScoreboard());
    }

    public static void updateTab(Player p, int tokens){
        new TabTitleObject("&aWelcome to &c&lThe &4#&lTeamR00D &c&lNetwork!".replace('&', '§'), p.getDisplayName() + " &b- &9".replace('&', '§') + tokens + " tokens").send(p);
        p.setPlayerListName(p.getDisplayName());
    }

    public static boolean isInt(String str){
        try{
            int num = Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
