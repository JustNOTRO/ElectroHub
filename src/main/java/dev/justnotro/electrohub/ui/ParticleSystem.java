package dev.justnotro.electrohub.ui;

import dev.justnotro.electrohub.utils.ItemBuilder;
import dev.triumphteam.gui.components.GuiType;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

import java.util.Optional;

public record ParticleSystem (@Getter Player player) {

    public void trailsGUI() {
        Gui gui = new Gui(GuiType.DISPENSER, "§c§lTRAILS WIZARD");
        gui.disableAllInteractions();

        gui.addItem(new GuiItem(new ItemBuilder(Material.WHITE_DYE, "§b§lARCTIC SCENT").create(), event -> {
            System.out.println(false);
            Optional.of(event.getClick())
                .filter(ClickType::isLeftClick)
                .ifPresent(clickType -> {
                    System.out.println(true);
                    player.closeInventory();
                    player.spawnParticle(Particle.SNOWBALL, player.getLocation(), 10, 0, 20, 0);
                });
        }));
        gui.open(player);
    }
}
