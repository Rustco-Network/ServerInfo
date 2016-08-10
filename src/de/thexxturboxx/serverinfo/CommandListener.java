package de.thexxturboxx.serverinfo;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandListener implements Listener, CommandExecutor {
	
	ServerInfo plugin;
	
	public CommandListener(ServerInfo plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler(priority=EventPriority.HIGH)
	public void onServerCommand(PlayerCommandPreprocessEvent e) {
		String command = e.getMessage().split(" ")[0];
		if(command.startsWith("bukkit:")) {
			command = command.replaceFirst("bukkit:", "");
		}
		if(command.startsWith("spigot:")) {
			command = command.replaceFirst("spigot:", "");
		}
		if((plugin.getConfig().getBoolean("Command.Plugins.Enable")) && ((command.equalsIgnoreCase("plugins")) ||
				(command.equalsIgnoreCase("pl"))) && 
				(!e.getPlayer().hasPermission("serverinfo.plugins"))) {
			e.setCancelled(true);
			e.getPlayer().sendMessage(plugin.getConfig().getString("Command.Plugins.Message"));
		}
		if((plugin.getConfig().getBoolean("Command.Version.Enable")) && ((command.equalsIgnoreCase("version")) ||
				(command.equalsIgnoreCase("about")) ||
				(command.equalsIgnoreCase("ver"))) && 
				(!e.getPlayer().hasPermission("serverinfo.version"))) {
			e.setCancelled(true);
			e.getPlayer().sendMessage(plugin.getConfig().getString("Command.Version.Message"));
		}
		if((plugin.getConfig().getBoolean("Command.Seed.Enable")) && (command.equalsIgnoreCase("seed")) && 
				(!e.getPlayer().hasPermission("serverinfo.seed"))) {
			e.setCancelled(true);
			e.getPlayer().sendMessage(plugin.getConfig().getString("Command.Seed.Message"));
		}
	}
	
	public static String getFinalArg(String[] args, int start) {
		StringBuilder bldr = new StringBuilder();
		for (int i = start; i < args.length; i++) {
			if (i != start) {
				bldr.append(" ");
			}
			bldr.append(args[i]);
		}
		return bldr.toString();
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof ConsoleCommandSender) return true;
		Player p = (Player) sender;
		if((label.equalsIgnoreCase("website") || label.equalsIgnoreCase("homepage")) &&
				plugin.getConfig().getBoolean("Command.Website.Enable")) {
			p.sendMessage(ServerInfo.getPrefix() + plugin.getConfig().getString("Command.Website.Message"));
		} else if(label.equalsIgnoreCase("forum") &&
				plugin.getConfig().getBoolean("Command.Forum.Enable")) {
			p.sendMessage(ServerInfo.getPrefix() + plugin.getConfig().getString("Command.Forum.Message"));
		} else if(label.equalsIgnoreCase("support") &&
				plugin.getConfig().getBoolean("Command.Support.Enable")) {
			p.sendMessage(ServerInfo.getPrefix() + plugin.getConfig().getString("Command.Support.Message1"));
			p.sendMessage(ServerInfo.getPrefix() + plugin.getConfig().getString("Command.Support.Message2"));
		}
		return true;
	}
	
}