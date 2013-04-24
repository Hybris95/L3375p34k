/*
    L3375p34k - The plugin for CraftBukkit that allows you to talk in Leet
    Copyright (C) 2013  Hybris95

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package com.hybris.bukkit.l3375p34k;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.Command;

import org.bukkit.entity.Player;
import org.bukkit.entity.HumanEntity;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;
import java.util.HashMap;

class L3375p34kExecutor implements CommandExecutor, Listener{

    private L3375p34k plugin;
	private ArrayList<String> leets;
	private HashMap<Character, String> dictionary;

	L3375p34kExecutor(L3375p34k plugin){
		this.plugin = plugin;
		this.leets = new ArrayList<String>();
		this.dictionary = new HashMap<Character, String>();
		this.dictionary.put('a',"4");
		this.dictionary.put('b',"|3");
		this.dictionary.put('c',"<");
		this.dictionary.put('d',"[)");
		this.dictionary.put('e',"3");
		this.dictionary.put('f',"|=");
		this.dictionary.put('g',"6");
		this.dictionary.put('h',"#");
		this.dictionary.put('i',"!");
		this.dictionary.put('j',";");
		this.dictionary.put('k',"X");
		this.dictionary.put('l',"1");
		this.dictionary.put('m',"/^^\\");
		this.dictionary.put('n',"/V");
		this.dictionary.put('o',"0");
		this.dictionary.put('p',"|o");
		this.dictionary.put('q',"0.");
		this.dictionary.put('r',"/2");
		this.dictionary.put('s',"5");
		this.dictionary.put('t',"7");
		this.dictionary.put('u',"(_)");
		this.dictionary.put('v',"\\/");
		this.dictionary.put('w',"\\X/");
		this.dictionary.put('x',"><");
		this.dictionary.put('y',"'/");
		this.dictionary.put('z',">_");
	}
	
	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void onStdSpeak(AsyncPlayerChatEvent e)
	{
		// Check if the sender is a leet
		Player sender = e.getPlayer();
		if(this.leets.contains(sender.getName()))
		{
			String message = e.getMessage();
			if(!message.startsWith("/"))// TOCHECK if commands pass here
			{
				onLeetSpeak(e.getMessage(), sender);
				e.setCancelled(true);
			}
		}
	}
	
	private void onLeetSpeak(String leetString, Player sender)
	{
		// Recover the list of online players
		Player[] onlinePlayers = this.plugin.getServer().getOnlinePlayers();
		String senderName = sender.getName();
		
		// Parse every connected player
		for(int i = 0; i < onlinePlayers.length; i++)
		{
			Player onlinePlayer = onlinePlayers[i];
			String onlinePlayerName = onlinePlayer.getName();
			// Check if the parsed player is a leet
			if(!this.leets.contains(onlinePlayerName))
			{
				// Send to everynormal people the coded message
				sendMessage(senderName, onlinePlayer, encoded(leetString));
			}
			else
			{
				// Send to everyleet people the decoded message except the sender
				if(onlinePlayerName.equals(senderName))
				{
					sendMessage(senderName, onlinePlayer, encoded(leetString));
				}
				sendMessage(senderName, onlinePlayer, leetString);
			}
		}
	}
	
	private void sendMessage(String senderName, Player receiver, String message)
	{
		receiver.sendMessage("<"+senderName+"> "+message);
	}
	
	private String encoded(String leetString)
	{
		String encodedString = "";
		
		// Encode the string to leet !
		char[] characters = leetString.toCharArray();
		for(int i = 0; i < characters.length; i++)
		{
			char character = characters[i];
			char loweredChar = Character.toLowerCase(character);
			if(this.dictionary.containsKey(loweredChar))
			{
				encodedString += this.dictionary.get(loweredChar);
			}
			else
			{
				encodedString += character;
			}
		}
		
		return encodedString;
	}

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args){

		if(label.equals("l5") || label.equals("leet") || label.equals("l337")){
			if(sender instanceof Player && this.plugin.hasPermissions(sender, "leet"))
			{
				if(args.length > 0)
				{
					String firstArgument = args[0];
					if(firstArgument.equalsIgnoreCase("list"))
					{
						StringBuilder concatenedList = new StringBuilder("[L3375p34k] L337s: ");
						Object[] values = this.leets.toArray();
						for(int i = 0; i < values.length; i++)
						{
							concatenedList.append(values[i]);
							if(i < (values.length - 1))
							{
								concatenedList.append(", ");
							}
						}
						sender.sendMessage(concatenedList.toString());
						return true;
					}
				}
				
				// Activate leet mode if not any other /leet command
				Player player = (Player)sender;
				String name = player.getName();
				if(this.leets.contains(name))
				{
					this.leets.remove(name);
					player.sendMessage("[L3375p34k] You are not a leet anymore.");
				}
				else
				{
					this.leets.add(name);
					player.sendMessage("[L3375p34k] You are a leet.");
				}
				return true;
			}
			else{return false;} // Unauthorized
		}
		else{return false;} // Should never happen

	}

}
