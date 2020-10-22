package de.AronikTV.bedwars.commands;

import de.AronikTV.bedwars.game.GameManager;
import de.AronikTV.bedwars.main.BedWars;
import de.AronikTV.bedwars.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StartCommand implements CommandExecutor {

	private BedWars bedWars;
	private Utils utils;
	
	public StartCommand() {
		this.bedWars = BedWars.getInstance();
		this.utils = bedWars.getUtils();
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		GameManager gameManager = bedWars.getGameManager();
		
		if(sender instanceof Player) {
			Player p = (Player) sender;
			String prefix = utils.getPrefix();
			
			if(p.hasPermission("bedwars.start")) {
				if(!(gameManager.isRunning()) && !(gameManager.isGameStarting())) {
					gameManager.setIndex(10);
					
					p.sendMessage(prefix + "�7Das Spiel wird �bgestartet!");
				} else
					p.sendMessage(prefix + "�7Das Spiel �bstartet �7bereits!");
			} else
				p.sendMessage(prefix + "�4Keine Rechte");
		} else
			utils.sendConsole("�cNur Spieler UHR!!!");
		
		return true;
	}

}
