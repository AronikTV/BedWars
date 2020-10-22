package de.AronikTV.bedwars.listener;

import de.AronikTV.bedwars.game.GameManager;
import de.AronikTV.bedwars.main.BedWars;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class FoodLevelChangeListener implements Listener {

	@EventHandler
	public void onFoodLevelChange(FoodLevelChangeEvent e) {
		GameManager gameManager = BedWars.getInstance().getGameManager();
		
		if(e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			
			if(!(gameManager.isRunning()) || !(gameManager.getInGame().contains(p.getUniqueId())) || gameManager.isEnding()) {
				e.setCancelled(true);
				p.setFoodLevel(20);
			}
		}
	}
	
}
