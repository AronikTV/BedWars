package de.AronikTV.bedwars.listener;

import de.AronikTV.bedwars.game.GameManager;
import de.AronikTV.bedwars.game.ScoreboardUtils;
import de.AronikTV.bedwars.main.BedWars;
import de.AronikTV.bedwars.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;


public class PlayerQuitListener implements Listener {

	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		BedWars bedWars = BedWars.getInstance();
		GameManager gameManager = bedWars.getGameManager();
		Utils utils = bedWars.getUtils();
		ScoreboardUtils scoreboardUtils = bedWars.getScoreboardUtils();
		
		Player p = e.getPlayer();
		String prefix = utils.getPrefix();
		
		if(scoreboardUtils.getScoreboards().containsKey(p.getName()))
			scoreboardUtils.getScoreboards().remove(p.getName());
		
		gameManager.kickPlayerFromGame(p);
		
		if(gameManager.isRunning()) {
			if(gameManager.getInGame().contains(p.getUniqueId()))
				e.setQuitMessage(prefix + "�7Der Spieler �b" + p.getDisplayName() + " �7hat das Spiel verlassen");
			else
				e.setQuitMessage(null);
		} else
			e.setQuitMessage(prefix + "�7Der Spieler �b" + p.getName() + " �7hat das Spiel verlassen �8(�b" + (Bukkit.getOnlinePlayers().size() - 1) + "�8/�b" + (gameManager.getTeams().size() * gameManager.getTeams().get(0).getTeamSize()) + "�8)");
	}
	
}
