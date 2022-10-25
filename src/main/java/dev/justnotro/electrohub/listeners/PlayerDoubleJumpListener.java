package dev.justnotro.electrohub.listeners;

import dev.justnotro.electrohub.Electrohub;
import org.bukkit.GameMode;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.util.Vector;


public class PlayerDoubleJumpListener implements Listener {

    @EventHandler
    public void onPlayerToggleFlight(PlayerToggleFlightEvent event) {
        ConfigurationSection doubleJump = Electrohub.getInstance().getConfig().getConfigurationSection("physics.double-jump");
        ConfigurationSection soundSection = Electrohub.getInstance().getConfig().getConfigurationSection("sound.commands");

        Player player = event.getPlayer();
        Vector vector = player.getLocation().getDirection().multiply(1.5).setY(doubleJump.getInt("velocity-strength"));

        if (!doubleJump.getBoolean("enabled")) return;
        if (player.getGameMode().equals(GameMode.CREATIVE) || player.getGameMode().equals(GameMode.SPECTATOR)) return;
        if (player.hasMetadata("doubleJump")) return;

        event.setCancelled(true);
        player.setAllowFlight(false);
        player.setMetadata("doubleJump", Electrohub.getInstance().doubleJump(true));

        player.setVelocity(vector);
        player.spawnParticle(Particle.valueOf(doubleJump.getString("particle")), player.getLocation(), 10);
        player.playSound(player.getLocation(), Sound.valueOf(soundSection.getString("double-jump")), 1, 1);
    }
}