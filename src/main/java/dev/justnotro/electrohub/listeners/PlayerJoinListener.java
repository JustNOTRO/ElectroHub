package dev.justnotro.electrohub.listeners;

import dev.justnotro.electrohub.models.items.WizardItem;
import dev.justnotro.electrohub.structs.Message;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        event.setJoinMessage(Message.fixColor("&7[&2+&7] " + player.getDisplayName()));
        event.getPlayer().getInventory().setItem(0, WizardItem.getItem());

        player.setAllowFlight(true);
    }
}
