package de.AronikTV.bedwars.listener;

import de.AronikTV.bedwars.game.GameManager;
import de.AronikTV.bedwars.main.BedWars;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;


public class EntityDamageByEntityListener implements Listener {

	@EventHandler
	public void onDamageByEntity(EntityDamageByEntityEvent e) {
		GameManager gameManager = BedWars.getInstance().getGameManager();
		
		if(e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			
			if (e.getDamager() instanceof Player) {
				Player damager = (Player) e.getDamager();
				
				if(gameManager.isRunning() && gameManager.getInGame().contains(p.getUniqueId()) && gameManager.getInGame().contains(damager.getUniqueId()) && gameManager.getTeam(p).getTeamName().equals(gameManager.getTeam(damager).getTeamName()))
					e.setCancelled(true);
			}
		}
	}
	
}
