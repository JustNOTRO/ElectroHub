package dev.justnotro.electrohub.structs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.ChatColor;

@AllArgsConstructor
public enum Message {

    ;

    @Getter
    private final String defaultMessage;

    public static String fixColor(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
