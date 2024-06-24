package dev.tranducminh.joinplus;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class JoinPlus extends JavaPlugin implements Listener {

    private FileConfiguration config;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        config = getConfig();
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (config.getBoolean("JoinMessage.Enabled")) {
            String joinMessage = config.getString("JoinMessage.Message");
            String playerName = event.getPlayer().getName();
            joinMessage = joinMessage.replace("%player_name%", playerName);
            event.setJoinMessage(ChatColor.translateAlternateColorCodes('&', joinMessage));
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        if (config.getBoolean("QuitMessage.Enabled")) {
            String quitMessage = config.getString("QuitMessage.Message");
            String playerName = event.getPlayer().getName();
            quitMessage = quitMessage.replace("%player_name%", playerName);
            event.setQuitMessage(ChatColor.translateAlternateColorCodes('&', quitMessage));
        }
    }
}