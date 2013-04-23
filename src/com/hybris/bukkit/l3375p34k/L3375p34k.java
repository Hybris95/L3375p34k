package com.hybris.bukkit.l3375p34k;

import org.bukkit.plugin.java.JavaPlugin;
import java.util.logging.Logger;

import org.bukkit.plugin.Plugin;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class L3375p34k extends JavaPlugin{
    
	private Logger log = null;
	private L3375p34kExecutor executor = null;
	
	public void onLoad(){}
	
	public void onEnable(){
		log = getServer().getLogger();
		log.info("[L3375p34k] Enabling...");
		try{
			executor = new L3375p34kExecutor(this);
			getCommand("L5").setExecutor(executor);
			getCommand("LEET").setExecutor(executor);
			getCommand("L337").setExecutor(executor);
			getServer().getPluginManager().registerEvents(executor, this);
			log.info("[L3375p34k] Enabled");
		}
		catch(Exception e){
			log.severe("[L3375p34k] Failed Enabling: " + e.getMessage());
			onDisable();
		}
	}
	
	public void onDisable(){
		if(log != null){
			log.info("[L3375p34k Disabling...");
			executor = null;
			log.info("[L3375p34k] Disabled");
			log = null;
		}
	}
	
	boolean hasPermissions(CommandSender sender, String node){
		return sender.isOp();
	}
	
}
