package dev.justnotro.electrohub.commands;

import dev.justnotro.electrohub.Electrohub;
import dev.justnotro.electrohub.structs.Message;
import dev.justnotro.electrohub.utils.CommandAutoRegistration;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@CommandAutoRegistration.Command(value = "fly")
public class FlyCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage(Electrohub.getInstance().getPrefix() + Message.fixColor("&cOnly a player can execute that command&7."));
            return true;
        }

        if (!player.hasPermission("electrohub.fly")) {
            player.sendMessage(Electrohub.getInstance().getPrefix() + Message.fixColor("§cYou don't have permission to execute that command§7."));
            return true;
        }

        String isEnabled = player.getAllowFlight() ? "§c§lDisabled§7." : "§a§lEnabled§7.";
        player.setAllowFlight(!player.getAllowFlight());
        player.sendMessage(Electrohub.getInstance().getPrefix() + "§7Flight has been: " + isEnabled);

        return true;
    }
}
