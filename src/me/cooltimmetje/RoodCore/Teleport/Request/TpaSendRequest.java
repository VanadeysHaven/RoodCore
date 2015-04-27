package me.cooltimmetje.RoodCore.Teleport.Request;

import me.cooltimmetje.RoodCore.Methods;
import mkremins.fanciful.FancyMessage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * This class has been created on 26-4-2015 at 10:34 by cooltimmetje.
 */
public class TpaSendRequest {

    public static void sendTpa(Player p, String targetName) {
        Player target = Bukkit.getPlayer(targetName);
        if(target != null){
            if(TpaData.playerTarget.containsKey(p.getName())){
                String pDelete = p.getName();
                String tDelete = TpaData.playerTarget.get(p.getName());
                TpaData.playerTarget.remove(pDelete);
                TpaData.targetPlayer.remove(tDelete);
            }
            TpaData.playerTarget.put(p.getName(), target.getName());
            TpaData.targetPlayer.put(target.getName(), p.getName());
            Methods.sendTitle("&eYou got a teleport request!", "&eOpen your chat to &2accept &eor &cdeny&e!", 20, 40, 20, target);
            Methods.sendTitle("&eRequest sent!", "&eWait for them to &2accept &eor &cdeny &eit!", 20, 40, 20, p);
            Methods.msgPlayerTag("Tpa", p.getDisplayName() + " &asent you a teleport request!", target);
            String json = new FancyMessage
                    ("Choose One: ").color(ChatColor.AQUA)
                    .then("[ACCEPT]").color(ChatColor.DARK_GREEN).tooltip("Click to accept").command("/tpaccept")
                    .then(" - ").color(ChatColor.AQUA)
                    .then("[DENY]").color(ChatColor.RED).tooltip("Click to deny").command("/tpdeny")
                    .toJSONString();
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "tellraw " + target.getName() + " " + json);
        } else {
            Methods.msgPlayerTag("Tpa", "That player is not online!", p);
        }
    }
}
