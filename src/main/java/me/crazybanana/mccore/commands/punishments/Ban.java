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
                for(int i=1;i<args.length;i++) {
                    reason.add(args[i]);
                }
                Bukkit.getConsoleSender().sendMessage(mcToString(reason));
                Bukkit.getBanList(BanList.Type.NAME).addBan(args[0], mcToString(reason), null, sender.toString());
                Bukkit.getPlayer(args[0]).kickPlayer(mcToString(reason));
                sender.sendMessage(prefix + "§a Successfully§c banned§6 " + args[0] + " for§d " + mcToString(reason));
                reason.clear();
            } else {
                sender.sendMessage(plugin.getConfig().getString("Prefix") + "§c You don't have§6 permission§c to run that command!");
            }
        } else {
            sender.sendMessage(prefix + "§c Incorrect Usage§6!§d /ban <user> <reason>");
        }
        return false;
    }

    private String mcToString(ArrayList<String> array) {
        String s = "";

        for(int i=0;i<array.size();i++) {
            //s.concat(array.get(i) + " ");
            s+=array.get(i) + " ";
        }
        s.trim();
        return s;
    }
}
