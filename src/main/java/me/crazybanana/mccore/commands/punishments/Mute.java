package me.crazybanana.mccore.commands.punishments;

import me.crazybanana.mccore.McCore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;

public class Mute implements CommandExecutor {
    // Var
    private McCore plugin = null;
    public ArrayList<String> muted = new ArrayList<String>();
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
                muted.add(args[0]);
            } else {
                sender.sendMessage(prefix + "§c Incorrect Usage§6!§d /mute <user>");
            }
        } else {
            sender.sendMessage(plugin.getConfig().getString("Prefix") + "§c You don't have§6 permission§c to run that command!");
        }
        return false;
    }
}