package dev.justnotro.electrohub.electrohub;

import dev.justnotro.electrohub.listeners.PlayerJoinListener;
import dev.justnotro.electrohub.listeners.PlayerQuitListener;
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

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
