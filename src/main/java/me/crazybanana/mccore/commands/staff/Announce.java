package me.crazybanana.mccore.commands.staff;

import me.crazybanana.mccore.McCore;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Announce implements CommandExecutor {
    // Var
    private McCore plugin = null;
    private ArrayList<Player> players = new ArrayList<>();
    private String message = "";
    private String prefix = "";

    // Constructor
    public Announce(McCore plugin) {
        this.plugin = plugin;
        prefix = plugin.getConfig().getString("Prefix");
    }

    // Var
    public String normal = "§" + plugin.getConfig().getString("Normal");
    public String highlight = "§" + plugin.getConfig().getString("Highlighted");
    public String success = "§" + plugin.getConfig().getString("Success");
    public String error = "§" + plugin.getConfig().getString("Error");

    // Command
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender.hasPermission("mc.staff.*") || sender.hasPermission("mc.staff.announce") || sender.hasPermission("mc.rank.senioradmin")) {
            if(args.length > 0) {
                players = (ArrayList<Player>) Bukkit.getOnlinePlayers();

                for(int i=0;i<args.length;i++) {
                    message+=args[i] + " ";
                    message.trim();
                }

                for(int i=0;i<players.size();i++) {
                    if(plugin.getConfig().getBoolean("Announcement-Headers-and-Closers")) {
                        players.get(i).sendMessage(plugin.getConfig().getString("Announcement-Header"));
                    }

                    players.get(i).sendMessage(plugin.getConfig().getString("Announce-Prefix") + " " + message);

                    if(plugin.getConfig().getBoolean("Announcement-Headers-and-Closers")) {
                        players.get(i).sendMessage(plugin.getConfig().getString("Announcement-Closer"));
                    }
                }
                message = " ";
                players.clear();
            } else {
                sender.sendMessage(prefix + error + " Incorrect Usage" + normal + "!" + highlight + " /announce <message>");
            }
        } else {
            sender.sendMessage(plugin.getConfig().getString("Prefix") + error + " You don't have" + normal + " permission" + error + " to run that command!");
        }
        return false;
    }
}
