package me.crazybanana.mccore.commands.punishments;

import me.crazybanana.mccore.McCore;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;

public class Ban implements CommandExecutor {
    // Var
    private McCore plugin = null;
    private ArrayList<String> reason = new ArrayList<String>();
    String prefix;

    // Constructor
    public Ban(McCore plugin) {
        this.plugin = plugin;
        prefix = plugin.getConfig().getString("Prefix");
    }

    // Command
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length > 1) {
            if(sender.hasPermission("mc.punish.*") || sender.hasPermission("mc.punish.ban") || sender.hasPermission("mc.rank.senioradmin")) {
                for(int i=0; i<=args.length-1;i++) {
                    reason.add(args[i+1]);
                }
                sender.sendMessage(prefix + "§6 " + mcToString(reason));
            }
        } else {
            sender.sendMessage(prefix + "§c Incorrect Usage§6!§d /ban <user> <reason>");
        }
        return false;
    }

    private String mcToString(ArrayList array) {
        String s = "";

        for(int i=0;i<=array.size();i++) {
            s.concat(array.get(i).toString() + " ");
        }
        s.trim();
        return s;
    }
}
