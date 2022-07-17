package dev.justnotro.electrohub.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

public class PlayerInteractListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {

        Player player = event.getPlayer();
        Vector vector = player.getLocation().getDirection().multiply(1).setY(1);

        if (!event.getAction().equals(Action.PHYSICAL)) return;
        if (event.getClickedBlock().getType().equals(Material.STONE_PRESSURE_PLATE)) System.out.println(true);
        player.setVelocity(vector);

    }
}
