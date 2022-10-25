package dev.justnotro.electrohub.models.items;

import dev.justnotro.electrohub.structs.Message;
import dev.justnotro.electrohub.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class WizardItem {

    private static ItemStack item = new ItemBuilder(Material.BLAZE_ROD, Message.fixColor("&c&lParticles Wizard")).create();

    public static ItemStack getItem() {

        return item;
    }
}
