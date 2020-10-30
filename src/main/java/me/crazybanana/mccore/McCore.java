package me.crazybanana.mccore;

import me.crazybanana.mccore.commands.punishments.Ban;
import me.crazybanana.mccore.commands.punishments.Ipban;
import me.crazybanana.mccore.commands.punishments.Mute;
import me.crazybanana.mccore.commands.staff.Announce;
import me.crazybanana.mccore.commands.staff.Mcorereload;
import me.crazybanana.mccore.files.MutedFile;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerCommandSendEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class McCore extends JavaPlugin implements Listener {
    // Var
    Mcorereload reload = new Mcorereload(this);
    Ban ban = new Ban(this);
    Ipban ipban = new Ipban(this);
    Mute mute = new Mute(this);
    Announce announce = new Announce(this);

    //ArrayList<Player> playerList = new ArrayList<>();
    List<String> playerList = new ArrayList<>();
    List<String> muted = new ArrayList<>();

    public String normal = "§" + getConfig().getString("Normal");
    public String highlight = "§" + getConfig().getString("Highlighted");
    public String success = "§" + getConfig().getString("Success");
    public String error = "§" + getConfig().getString("Error");

    @Override
    public void onEnable() {
        playerList.add("place_holder (Do Not Change)");

        // Config
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        // Custom Config
        MutedFile.setup();
        MutedFile.get().addDefault("Muted", playerList);
        MutedFile.get().options().copyDefaults(true);
        MutedFile.save();
        playerList.clear();

        // Events Setup
        getServer().getPluginManager().registerEvents(this, this);

        // Command Setup
        getCommand("mcorereload").setExecutor(reload);
        getCommand("ban").setExecutor(ban);
        getCommand("ipban").setExecutor(ipban);
        getCommand("mute").setExecutor(mute);
        getCommand("announce").setExecutor(announce);
        Bukkit.getConsoleSender().sendMessage(getConfig().getString("Prefix") + success + " Activated");
    }

    // Events
    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Bukkit.getConsoleSender().sendMessage(getConfig().getString("Prefix") + e.getPlayer().toString());
        Bukkit.getConsoleSender().sendMessage(getConfig().getString("Prefix") + e.getPlayer().getUniqueId().toString());
        muted = MutedFile.get().getStringList("Muted");
        for(int i=0;i<muted.size();i++) {
            Bukkit.getConsoleSender().sendMessage(getConfig().getString("Prefix") + muted.get(i));
            if(muted.get(i) == e.getPlayer().getUniqueId().toString()) {
                e.setCancelled(true);
                e.getPlayer().sendMessage(getConfig().getString("Prefix") + error + " You are muted" + normal + "!");
            }
        }
        muted.clear();
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent e) {

    }
}
