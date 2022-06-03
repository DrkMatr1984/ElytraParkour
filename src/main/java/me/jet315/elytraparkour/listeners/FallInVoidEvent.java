package me.jet315.elytraparkour.listeners;

import me.jet315.elytraparkour.Core;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class FallInVoidEvent implements Listener {
    
	@EventHandler(priority = EventPriority.HIGHEST)
    public void onMove (PlayerMoveEvent e) {
            if(e.getTo().getY() <= 0) {
            	Player p = e.getPlayer();
            	if(Core.getInstance().getElytraManager().getElytraPlayers().containsKey(p) && Core.getInstance().getElytraManager().getElytraPlayers().get(p).isInMap()){
                    if(Core.getInstance().getProperties().isTeleportToMapSpawnIfFallsInVoid()){
                        p.teleport(Core.getInstance().getElytraManager().getElytraPlayers().get(p).getMap().getSpawnLocation());
                        Core.getInstance().getElytraManager().getElytraPlayers().get(p).setInMap(false);
                    }
                    if(!Core.getInstance().getProperties().getFallsInVoidMessage().equalsIgnoreCase("none")){
                        p.sendMessage(Core.getInstance().getProperties().getFallsInVoidMessage().replaceAll("%PREFIX%",Core.getInstance().getProperties().getPluginsPrefix()));
                    }
                }
            }
        }        
}