package me.crazybanana.mccore;

import me.crazybanana.mccore.commands.Mcorereload;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class McCore extends JavaPlugin {
    // Var
    Mcorereload reload = new Mcorereload(this);

    @Override
    public void onEnable() {
        // Config
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        // Plugin Setup
        getCommands();
    }

    private void getCommands() {
        getCommand("mcorereload").setExecutor(reload);
        Bukkit.getConsoleSender().sendMessage(getConfig().getString("Prefix") + "§6 Commands activated");
    }
}
