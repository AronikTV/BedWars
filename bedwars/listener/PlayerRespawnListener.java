package de.AronikTV.bedwars.listener;

import de.AronikTV.bedwars.game.GameManager;
import de.AronikTV.bedwars.main.BedWars;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.util.Vector;


public class PlayerRespawnListener implements Listener {

	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent e) {
		BedWars bedWars = BedWars.getInstance();
		GameManager gameManager = bedWars.getGameManager();
		
		Player p = e.getPlayer();
		
		if(gameManager.getInGame().contains(p.getUniqueId()))
			e.setRespawnLocation(gameManager.getTeam(p).getSpawnLocation());
		else if(!(gameManager.isRunning()))
			e.setRespawnLocation(bedWars.getGameFileUtils().getLocation("Lobby"));
		else
			e.setRespawnLocation(gameManager.getTeams().get(0).getSpawnLocation());
		
		p.setVelocity(new Vector(0, 0, 0));
	}
	
}
