package dev.justnotro.electrohub;

import dev.justnotro.electrohub.commands.FlyCommand;
import dev.justnotro.electrohub.structs.Message;
import dev.justnotro.electrohub.utils.Config;
import dev.justnotro.electrohub.utils.ListenerAutoRegistration;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Electrohub extends JavaPlugin {

    @Getter
    private static Electrohub instance;

    @Getter
    @Setter(AccessLevel.PRIVATE)
    private Config
        languageConfig;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        setLanguageConfig(new Config("language", null, false));
        new ListenerAutoRegistration(this, false).register("dev.justnotro.electrohub.listeners");

        getCommand("fly").setExecutor(new FlyCommand());
        Bukkit.getConsoleSender().sendMessage(Message.fixColor("&aElectroHub is now enabled!"));
    }

    /**
     * This method is used when doing a deep reload for configs.
     * Use only if you disable/enable DoubleJump in the config.yml.
     */
    public static void reloadSystem() {
        Electrohub.getInstance().reloadConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getConsoleSender().sendMessage(Message.fixColor("&cElectroHub is now disabled!"));
    }

    public String getPrefix() {
        return Message.fixColor(getConfig().getString("system.prefix"));
    }
}
