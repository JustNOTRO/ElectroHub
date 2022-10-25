package dev.justnotro.electrohub.listeners;

import dev.justnotro.electrohub.Electrohub;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveListener implements Listener {

    @EventHandler
    public void onPlayerMoveEvent(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        if (player.getGameMode().equals(GameMode.CREATIVE)) return;
        if (player.isOnGround() || event.getTo().getBlock().isLiquid() && player.hasMetadata("doubleJump")) {
            player.removeMetadata("doubleJump", Electrohub.getInstance());
            player.setAllowFlight(true);
        }
    }

    @EventHandler
    public void onPlayerParticleEvent(PlayerMoveEvent event) {
        Player player = event.getPlayer();

    }
}
