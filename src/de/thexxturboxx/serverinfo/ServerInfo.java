package de.thexxturboxx.serverinfo;

import java.io.File;

import org.bukkit.BanList.Type;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class ServerInfo extends JavaPlugin {
	
	public static ServerInfo instance;
	public static File path = new File("plugins/ServerInfo"), dataPath;
	
	public static ServerInfo getInstance() {
		return instance;
	}
	
	@Override
	public void onEnable() {
		loadConfiguration();
		initStandard("Command.Plugins.Enable", true);
		initStandard("Command.Version.Enable", true);
		initStandard("Command.Seed.Enable", true);
		initStandard("Command.Plugins.Message", ChatColor.WHITE + "Plugins (3): " + ChatColor.GREEN +
				"Was geht dich das an?" + ChatColor.WHITE + ", " + ChatColor.GREEN +
				"Das zeigen wir dir nicht!" + ChatColor.WHITE + ", " + ChatColor.GREEN + "NEIN!");
		initStandard("Command.Version.Message", ChatColor.WHITE + "Spigot 1.8.8 " +
				ChatColor.MAGIC + "||" + ChatColor.AQUA + " ThexXTURBOXx und Sensetox-Edit " +
				ChatColor.WHITE + ChatColor.MAGIC + "||");
		initStandard("Command.Seed.Message", ChatColor.WHITE + "Seed: Den sagen wir dir nicht! :P");
		initStandard("Command.Website.Enable", true);
		initStandard("Command.Forum.Enable", true);
		initStandard("Command.Support.Enable", true);
		initStandard("Command.Website.Message", ChatColor.GOLD + "Webseite: http://rustico-network.tk/");
		initStandard("Command.Forum.Message", ChatColor.GOLD + "Forum: http://rustico.tk/");
		initStandard("Command.Support.Message1", ChatColor.GOLD + "Technischer Support: networkrustico@gmail.com");
		initStandard("Command.Support.Message2", ChatColor.GOLD + "Sonstiger Support: rustico.sup@web.de");
		getCommand("website").setExecutor(new CommandListener(this));
		getCommand("homepage").setExecutor(new CommandListener(this));
		getCommand("forum").setExecutor(new CommandListener(this));
		getCommand("support").setExecutor(new CommandListener(this));
		getServer().getPluginManager().registerEvents(new CommandListener(this), this);
	}
	
	@Override
	public void onDisable() {
		
	}
	
	public void set(String key, Object value) {
		getConfig().set(key, value);
		saveConfig();
	}
	
	public void loadConfiguration() {
		getConfig().options().copyDefaults(true);
		saveConfig();
	}
	
	public void initStandard(String key, Object standardValue) {
		if(!getConfig().isSet(key)) {
			getConfig().set(key, standardValue);
			saveConfig();
		}
	}
	
	public void simplePardon(String name) {
		getServer().getBanList(Type.NAME).pardon(name);
	}
	
	public void simpleBan(String name, String reason) {
		getServer().getBanList(Type.NAME).addBan(name, reason, null, null);
	}
	
	public static File getPluginPath() {
		return path;
	}
	
	public static File getDataPath() {
		return dataPath;
	}
	
	public static String getPrefix() {
		return ChatColor.GRAY + "[" + ChatColor.BLUE + "ServerInfo" + ChatColor.GRAY + "] ";
	}
	
	public static double round(double value, int decimal) {
	    return (double) Math.round(value * Math.pow(10d, decimal)) / Math.pow(10d, decimal);
	}
	
}