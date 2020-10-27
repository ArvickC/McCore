package me.crazybanana.mccore;

import me.crazybanana.mccore.commands.punishments.Ban;
import me.crazybanana.mccore.commands.punishments.Ipban;
import me.crazybanana.mccore.commands.punishments.Mute;
import me.crazybanana.mccore.commands.staff.Mcorereload;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class McCore extends JavaPlugin {
    // Var
    Mcorereload reload = new Mcorereload(this);
    Ban ban = new Ban(this);
    Ipban ipban = new Ipban(this);
    Mute mute = new Mute(this);

    @Override
    public void onEnable() {
        // Config
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        // Command Setup
        getCommand("mcorereload").setExecutor(reload);
        getCommand("ban").setExecutor(ban);
        getCommand("ipban").setExecutor(ipban);
        getCommand("mute").setExecutor(mute);
        Bukkit.getConsoleSender().sendMessage(getConfig().getString("Prefix") + "§6 Commands activated");
    }

    // Events
    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        if(mute.muted.contains(e.getPlayer().toString())) {
            e.setCancelled(true);
        }
    }
}
