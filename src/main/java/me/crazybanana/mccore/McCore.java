package me.crazybanana.mccore;

import me.crazybanana.mccore.commands.punishments.Ban;
import me.crazybanana.mccore.commands.punishments.Ipban;
import me.crazybanana.mccore.commands.staff.Mcorereload;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class McCore extends JavaPlugin {
    // Var
    Mcorereload reload = new Mcorereload(this);
    Ban ban = new Ban(this);
    Ipban ipban = new Ipban(this);

    @Override
    public void onEnable() {
        // Config
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        // Command Setup
        getCommand("mcorereload").setExecutor(reload);
        getCommand("ban").setExecutor(ban);
        getCommand("ipban").setExecutor(ipban);
        Bukkit.getConsoleSender().sendMessage(getConfig().getString("Prefix") + "§6 Commands activated");
    }
}
