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
                sender.sendMessage(prefix + "§6 " + args[0] + "§d Muted§6!");
                Bukkit.getPlayer(args[0]).sendMessage(prefix + "§c You have been muted by§d " + sender.getName());
            } else {
                sender.sendMessage(prefix + "§c Incorrect Usage§6!§d /mute <user>");
            }
        } else {
            sender.sendMessage(plugin.getConfig().getString("Prefix") + "§c You don't have§6 permission§c to run that command!");
        }
        return false;
    }
}