package dev.justnotro.electrohub.structs;

import dev.justnotro.electrohub.Electrohub;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

/**
 * Language class used to setup all default messages and messages utilities, this class mainly
 * helps to send messages to users, and load messages from local language.yml config.
 * To simplify this class you use Message.MESSAGE_YOU_WANT.sendMessage(CommandSender, withPrefix);
 */
@AllArgsConstructor
public enum Message {

    ;
    @Getter
    private final String defaultMessage;

    public static String fixColor(String message) {

        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public String getMessage() {
        return Electrohub.getInstance().getLanguageConfig().getString(this.name());
    }

    public void sendMessage(CommandSender sender, boolean withPrefix) {
        
    }
}
