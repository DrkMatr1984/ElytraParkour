package me.jet315.elytraparkour.utils;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import me.jet315.elytraparkour.Core;


public class CommandTask extends BukkitRunnable{
	
	private RunLevel level;
	private Player player;
	private String com;
	private Core plugin;
	
	public CommandTask(RunLevel level, Core plugin, Player player, String com){
		this.level = level;
		this.plugin = plugin;
		this.player = player;
		this.com = com;
	}
	
	@Override
	public void run() {
		if(this.level==RunLevel.PLAYER) {
			this.player.performCommand(com);
		}
        if(this.level==RunLevel.OP){
        	if (!this.player.isOp()) {
    			this.player.setOp(true);
    			this.player.performCommand(com);
    			this.player.setOp(false);
    		} else {
    			this.player.performCommand(com);
    		}
        }		
        if(this.level==RunLevel.CONSOLE){
        	this.plugin.getServer().dispatchCommand(this.plugin.getServer().getConsoleSender(), com);
        }
	}
	
	public static enum RunLevel {
	    PLAYER, 
	    OP, 
	    CONSOLE;
	  }
	
}