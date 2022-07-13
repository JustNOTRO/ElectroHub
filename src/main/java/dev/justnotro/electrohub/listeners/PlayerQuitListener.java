package dev.justnotro.electrohub.listeners;

import dev.justnotro.electrohub.structs.Message;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent event) {

        Player player = event.getPlayer();

        event.setQuitMessage(Message.fixColor("&7[&c-&7] " + player.getDisplayName()));
    }
}
