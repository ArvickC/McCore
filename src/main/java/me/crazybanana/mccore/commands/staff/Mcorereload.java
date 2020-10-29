package me.crazybanana.mccore.commands.staff;

import me.crazybanana.mccore.McCore;
import me.crazybanana.mccore.files.MutedFile;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Mcorereload implements CommandExecutor {
    // Var
    private McCore plugin = null;

    // Constructor
    public Mcorereload(McCore plugin) {
        this.plugin = plugin;
    }

    // Command
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender.hasPermission("mc.staff.reload") || sender.hasPermission("mc.staff.*") || sender.hasPermission("mc.rank.manager")) {
            Bukkit.getServer().getPluginManager().disablePlugin(plugin);
            Bukkit.getServer().getPluginManager().enablePlugin(plugin);
            plugin.reloadConfig();
            MutedFile.muteReload();
            sender.sendMessage(plugin.getConfig().getString("Prefix") + "§6 Plugin Reloaded");
        } else {
            sender.sendMessage(plugin.getConfig().getString("Prefix") + "§c You don't have§6 permission§c to run that command!");
        }
        return false;
    }
}
