package me.crazybanana.mccore.files;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class MutedFile {
    // Var
    private static File muted;
    private static FileConfiguration customMuted;

    // Code
    public static void setup() {
        muted = new File(Bukkit.getServer().getPluginManager().getPlugin("McCore").getDataFolder(), "muted.yml");

        if(!muted.exists()) {
            try {
                muted.createNewFile();
            } catch (Exception e) {

            }
        }
        customMuted = YamlConfiguration.loadConfiguration(muted);
    }

    public static FileConfiguration get() {
        return customMuted;
    }

    public static void save() {
        try {
            customMuted.save(muted);
        } catch(Exception e) {

        }
    }

    public static void muteReload() {
        customMuted = YamlConfiguration.loadConfiguration(muted);
    }
}
