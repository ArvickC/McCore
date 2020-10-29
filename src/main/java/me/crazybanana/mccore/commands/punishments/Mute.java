package me.crazybanana.mccore.commands.punishments;

import me.crazybanana.mccore.McCore;
import me.crazybanana.mccore.files.MutedFile;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Mute implements CommandExecutor {
    // Var
    private McCore plugin = null;
    private List<String> muted = new ArrayList<>();
    private String prefix;

    // Constructor
    public Mute(McCore plugin) {
        this.plugin = plugin;
        prefix = plugin.getConfig().getString("Prefix");
    }

    // Var
    public String normal = "§" + plugin.getConfig().getConfigurationSection("Color").getString("Normal");
    public String highlight = "§" + plugin.getConfig().getConfigurationSection("Color").getString("Highlighted");
    public String success = "§" + plugin.getConfig().getConfigurationSection("Color").getString("Success");
    public String error = "§" + plugin.getConfig().getConfigurationSection("Color").getString("Error");

    // Command
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender.hasPermission("mc.punish.mute") || sender.hasPermission("mc.punish.*") || sender.hasPermission("mc.rank.senioradmin")) {
            if(args.length == 1) {
                muted = MutedFile.get().getConfigurationSection("").getStringList("Muted");
                muted.add(Bukkit.getPlayer(args[0]).getUniqueId().toString());
                MutedFile.get().set("Muted", muted);
                MutedFile.save();
                muted.clear();
                sender.sendMessage(prefix + normal + " " + args[0] + highlight + " Muted" + normal + "!");
                Bukkit.getPlayer(args[0]).sendMessage(prefix + error + " You have been muted by" + highlight + " " + sender.getName());
            } else {
                sender.sendMessage(prefix + error + " Incorrect Usage" + normal + "!" + highlight + " /mute <user>");
            }
        } else {
            sender.sendMessage(plugin.getConfig().getString("Prefix") + error + " You don't have" + normal + " permission" + error + " to run that command!");
        }
        return false;
    }
}