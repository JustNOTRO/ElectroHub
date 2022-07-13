package dev.justnotro.electrohub;

import dev.justnotro.electrohub.listeners.PlayerJoinListener;
import dev.justnotro.electrohub.listeners.PlayerQuitListener;
import dev.justnotro.electrohub.structs.Message;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Electrohub extends JavaPlugin {

    @Getter
    private static Electrohub instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerQuitListener(), this);
        Bukkit.getConsoleSender().sendMessage(Message.fixColor("&aElectroHub is now enabled!"));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        Bukkit.getConsoleSender().sendMessage(Message.fixColor("&cElectroHub is now disabled!"));
    }
}
