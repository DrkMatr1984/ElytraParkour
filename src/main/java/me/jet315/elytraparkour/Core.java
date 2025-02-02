package me.jet315.elytraparkour;

import me.jet315.elytraparkour.commands.CommandHandler;
import me.jet315.elytraparkour.listeners.FallInVoidEvent;
import me.jet315.elytraparkour.listeners.GlideMoveEvent;
import me.jet315.elytraparkour.listeners.GlideToggleEvent;
import me.jet315.elytraparkour.listeners.JoinEvent;
import me.jet315.elytraparkour.listeners.OnGroundEvent;
import me.jet315.elytraparkour.listeners.QuitEvent;
import me.jet315.elytraparkour.manager.ElytraManager;
import me.jet315.elytraparkour.manager.ElytraPlayer;
import me.jet315.elytraparkour.manager.ParticleManager;
import me.jet315.elytraparkour.utils.Properties;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Core extends JavaPlugin {

    /**
     * Stores plugins instance
     */
    private static Core instance;

    /**
     * Stores the configuration data
     */
    private Properties properties;

    /**
     * Stores active maps and players
     */
    private ElytraManager elytraManager;

    /**
     * Stores particle manager
     */
    private ParticleManager particleManager;

    public void onEnable(){
        instance = this;
        properties = new Properties(this);
        elytraManager = new ElytraManager(this);
        particleManager = new ParticleManager(this);

        getCommand("elytraparkour").setExecutor(new CommandHandler());

        Bukkit.getPluginManager().registerEvents(new GlideMoveEvent(),this);
        if(properties.isTeleportToMapSpawnIfStopsGliding())
            Bukkit.getPluginManager().registerEvents(new GlideToggleEvent(),this);
        if(properties.isTeleportToMapSpawnIfFallsInVoid())
            Bukkit.getPluginManager().registerEvents(new FallInVoidEvent(),this);
        if(properties.isTeleportToMapSpawnIfTouchGround())
            Bukkit.getPluginManager().registerEvents(new OnGroundEvent(),this);
        Bukkit.getPluginManager().registerEvents(new JoinEvent(),this);
        Bukkit.getPluginManager().registerEvents(new QuitEvent(),this);

        for(Player p : Bukkit.getOnlinePlayers()){
            Core.getInstance().getElytraManager().getElytraPlayers().put(p,new ElytraPlayer(p));
        }
    }


    public void onDisable(){
        Bukkit.getScheduler().cancelTasks(this);
        particleManager = null;
        elytraManager = null;
        properties = null;
        instance = null;
    }

    public void reloadPlugin(){
        this.particleManager = null;
        this.properties = null;

        this.properties = new Properties(this);
        this.elytraManager = new ElytraManager(this);
    }

    /**
     * @return configuration properties
     */
    public Properties getProperties() {
        return properties;
    }

    public ElytraManager getElytraManager() {
        return elytraManager;
    }

    public static Core getInstance() {
        return instance;
    }


	public ParticleManager getParticleManager() {
		return particleManager;
	}


}

