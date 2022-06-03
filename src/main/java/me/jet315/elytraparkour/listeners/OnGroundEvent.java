package me.jet315.elytraparkour.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import me.jet315.elytraparkour.Core;

public class OnGroundEvent implements Listener {
	
	@EventHandler
    public void onMove(PlayerMoveEvent e) {
		if(Core.getInstance().getElytraManager().getElytraPlayers().containsKey(e.getPlayer()) && Core.getInstance().getElytraManager().getElytraPlayers().get(e.getPlayer()).isInMap()){
			Player p = e.getPlayer();
			if (!p.isFlying() && p.getLocation().subtract(0, 0.1, 0).getBlock().getType().isSolid()) {
                if(Core.getInstance().getProperties().isTeleportToMapSpawnIfTouchGround()){
                    p.teleport(Core.getInstance().getElytraManager().getElytraPlayers().get(p).getMap().getSpawnLocation());
                    Core.getInstance().getElytraManager().getElytraPlayers().get(p).setInMap(false);
                }
                if(!Core.getInstance().getProperties().getFallsInVoidMessage().equalsIgnoreCase("none")){
                    p.sendMessage(Core.getInstance().getProperties().getTouchGroundMessage().replaceAll("%PREFIX%",Core.getInstance().getProperties().getPluginsPrefix()));
                }
            }
        }
	}
		
	
}








