package me.jet315.elytraparkour.utils;

import me.jet315.elytraparkour.Core;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;

import com.cryptomorin.xseries.XSound;

public class Properties extends DataFile{

    /**
     * Plugins prefix
     */
    private String pluginsPrefix = "plugins prefix";
    /**
     * The particle types for the different rings
     */
    private Particle firstRingParticles = Particle.VILLAGER_HAPPY;
    private Particle defaultRingParticles = Particle.CLOUD;
    private Particle lastRingParticles = Particle.FLAME;
    private Particle testingRingParticle = Particle.FLAME;
    private int numberOfParticlesToSpawnPerRing = 30;
    private int particleSpawnDelay = 30;
    private int additionalSpawnRings = 0;
    private int additionalPlayerRings = 2;

    /**
     * Boosters
     */
    private double firstRingBoost = 2;
    private double defaultRingBoost = 1;
    private double lastRingBoost = 0;

    private Sound firstRingSound = Sound.BLOCK_NOTE_BLOCK_PLING;
    private Sound defaultRingSound = Sound.BLOCK_NOTE_BLOCK_PLING;
    private Sound lastRingSound = Sound.BLOCK_NOTE_BLOCK_PLING;

    private Particle firstRingFeetParticles = Particle.FLAME;
    private Particle defaultRingFeetParticles = Particle.DRAGON_BREATH;
    private Particle lastRingFeetParticles = Particle.VILLAGER_HAPPY;


    private boolean teleportToMapSpawnAtLastRing = true;
    private String messageToSendWhenReachLastRing = "Fini!";
    private List<String> commandsToRunWhenReachLastRing = new ArrayList<String>();

    private boolean teleportToMapSpawnIfStopsGliding = true;
    private String stopGlidingMessage = "Keep moving!";
    
    private boolean teleportToMapSpawnIfFallsInVoid = true;
    private String fallsInVoidMessage = "Fell in void!";
    
    private boolean teleportToMapSpawnIfTouchGround = true;
    private String touchGroundMessage = "Touched Ground!";
    
    private String noPermissions = "No perms!";

    private String firstRingMessage = "First ring!";

    public Properties(Core instance){
        super(instance);
        loadProperties();
    }

    private void loadProperties(){
        pluginsPrefix = ChatColor.translateAlternateColorCodes('&',config.getString("PluginsPrefix"));

        firstRingParticles = Particle.valueOf(config.getString("FirstRingParticles"));
        defaultRingParticles = Particle.valueOf(config.getString("DefaultRingParticles"));
        lastRingParticles = Particle.valueOf(config.getString("LastRingParticles"));
        testingRingParticle = Particle.valueOf(config.getString("TestingRingParticle"));

        numberOfParticlesToSpawnPerRing = config.getInt("NumberOfParticlesPerRing");
        particleSpawnDelay = config.getInt("SpawningParticlesDelay");
        additionalSpawnRings = config.getInt("AdditionalSpawnRings");

        additionalPlayerRings = config.getInt("RingsInfrontOfPlayer");
        
        firstRingBoost = config.getDouble("FirstRingBoost");
        defaultRingBoost = config.getDouble("defaultRingBoost");
        lastRingBoost = config.getDouble("lastRingBoost");
        
        try {
        	firstRingSound = XSound.matchXSound(config.getString("FirstRingSound")).get().parseSound();
        }catch (IllegalArgumentException e) {
        	firstRingSound = Sound.BLOCK_NOTE_BLOCK_PLING;
        }
        try {
        	defaultRingSound = XSound.matchXSound(config.getString("DefaultRingSound")).get().parseSound();
        }catch (IllegalArgumentException e) {
        	defaultRingSound = Sound.BLOCK_NOTE_BLOCK_PLING;
        }
        try {
        	lastRingSound = XSound.matchXSound(config.getString("LastRingSound")).get().parseSound();
        }catch (IllegalArgumentException e) {
        	lastRingSound = Sound.BLOCK_NOTE_BLOCK_PLING;
        }
        
        try {
        	firstRingFeetParticles = Particle.valueOf(config.getString("FirstRingFeetParticles"));
        }catch (IllegalArgumentException e) {
        	firstRingFeetParticles = Particle.FLAME;
        }
        try {
        	defaultRingFeetParticles = Particle.valueOf(config.getString("DefaultRingFeetParticles"));
        }catch (IllegalArgumentException e) {
        	defaultRingFeetParticles = Particle.DRAGON_BREATH;
        }
        try {
        	lastRingFeetParticles = Particle.valueOf(config.getString("LastRingFeetParticles"));
        }catch (IllegalArgumentException e) {
        	lastRingFeetParticles = Particle.VILLAGER_HAPPY;
        }

        teleportToMapSpawnAtLastRing = config.getBoolean("TeleportToMapSpawnAtLastRing");
        messageToSendWhenReachLastRing = ChatColor.translateAlternateColorCodes('&',config.getString("MessageToSendWhenReachedLastRing"));
        if(config.getStringList("CommandsToRunWhenPlayerReachedLastRing")!=null)
            commandsToRunWhenReachLastRing = config.getStringList("CommandsToRunWhenPlayerReachedLastRing");

        teleportToMapSpawnIfStopsGliding = config.getBoolean("TeleportToMapSpawnIfStopsGliding");
        stopGlidingMessage = ChatColor.translateAlternateColorCodes('&',config.getString("StopGlidingMessage"));
        
        teleportToMapSpawnIfFallsInVoid = config.getBoolean("TeleportToMapSpawnIfFallsInVoid");
        fallsInVoidMessage = ChatColor.translateAlternateColorCodes('&',config.getString("FallsInVoidMessage"));
        
        teleportToMapSpawnIfTouchGround = config.getBoolean("TeleportToMapSpawnIfTouchGround");
        touchGroundMessage = ChatColor.translateAlternateColorCodes('&',config.getString("TouchGroundMessage"));

        noPermissions = ChatColor.translateAlternateColorCodes('&',config.getString("NoPermissions"));

        firstRingMessage = ChatColor.translateAlternateColorCodes('&',config.getString("FirstRingMessage"));

    }

    /**
     * @returns the config.yml configuration
     */
    public FileConfiguration getPropertiesFile(){
        return instance.getConfig();
    }

    public Particle getFirstRingParticles() {
        return firstRingParticles;
    }

    public Particle getDefaultRingParticles() {
        return defaultRingParticles;
    }

    public Particle getLastRingParticles() {
        return lastRingParticles;
    }

    public int getNumberOfParticlesToSpawnPerRing() {
        return numberOfParticlesToSpawnPerRing;
    }

    public int getParticleSpawnDelay() {
        return particleSpawnDelay;
    }

    public int getAdditionalSpawnRings() {
        return additionalSpawnRings;
    }

    public int getAdditionalPlayerRings() {
        return additionalPlayerRings;
    }

    public Particle getTestingRingParticle() {
        return testingRingParticle;
    }

    public String getPluginsPrefix() {
        return pluginsPrefix;
    }

    public double getFirstRingBoost() {
        return firstRingBoost;
    }

    public double getDefaultRingBoost() {
        return defaultRingBoost;
    }

    public double getLastRingBoost() {
        return lastRingBoost;
    }

    public boolean isTeleportToMapSpawnAtLastRing() {
        return teleportToMapSpawnAtLastRing;
    }

    public Sound getFirstRingSound() {
        return firstRingSound;
    }

    public Sound getDefaultRingSound() {
        return defaultRingSound;
    }

    public Sound getLastRingSound() {
        return lastRingSound;
    }

    public Particle getFirstRingFeetParticles() {
        return firstRingFeetParticles;
    }

    public Particle getDefaultRingFeetParticles() {
        return defaultRingFeetParticles;
    }

    public Particle getLastRingFeetParticles() {
        return lastRingFeetParticles;
    }

    public boolean isTeleportToMapSpawnIfStopsGliding() {
        return teleportToMapSpawnIfStopsGliding;
    }

    public String getMessageToSendWhenReachLastRing() {
        return messageToSendWhenReachLastRing;
    }

    public String getStopGlidingMessage() {
        return stopGlidingMessage;
    }

    public String getNoPermissions() {
        return noPermissions;
    }

    public String getFirstRingMessage() {
        return firstRingMessage;
    }

	public boolean isTeleportToMapSpawnIfFallsInVoid() {
		return teleportToMapSpawnIfFallsInVoid;
	}

	public String getFallsInVoidMessage() {
		return fallsInVoidMessage;
	}

	public boolean isTeleportToMapSpawnIfTouchGround() {
		return teleportToMapSpawnIfTouchGround;
	}

	public String getTouchGroundMessage() {
		return touchGroundMessage;
	}

	public List<String> getCommandsToRunWhenReachLastRing() {
		return commandsToRunWhenReachLastRing;
	}
}
