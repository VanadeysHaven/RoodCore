package me.cooltimmetje.RoodCore;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * This class has been created on 17-3-2015 at 21:22 by cooltimmetje.
 */
public class ChatLowerCaseRood implements Listener{

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event){
        if(event.getPlayer().getName().equals("ThoThoKill")) {
            event.setMessage(event.getMessage().toLowerCase().replace("xd", "xD"));
        }
    }

}
