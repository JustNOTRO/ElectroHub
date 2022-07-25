package dev.justnotro.electrohub.listeners;

import dev.justnotro.electrohub.Electrohub;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

public class PlayerInteractListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        ConfigurationSection soundSection = Electrohub.getInstance().getConfig().getConfigurationSection("sound.commands");
        ConfigurationSection padSection = Electrohub.getInstance().getConfig().getConfigurationSection("launch-pad.pad");

        Player player = event.getPlayer();
        Vector vector = player.getLocation().getDirection().multiply(1).setY(padSection.getInt("velocity-strength"));

        if (!padSection.getBoolean("enabled")) return;
        if (!event.getAction().equals(Action.PHYSICAL)) return;
        if (!event.getClickedBlock().getType().equals(Material.STONE_PRESSURE_PLATE)) return;

        player.setVelocity(vector);
        player.playSound(player.getLocation(), Sound.valueOf(soundSection.getString("launch-pad")), 1, 1);
    }
}
