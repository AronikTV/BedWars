package de.AronikTV.bedwars.listener;

import de.AronikTV.bedwars.game.GameManager;
import de.AronikTV.bedwars.main.BedWars;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;


public class EntityDamageListener implements Listener {

	@EventHandler
	public void onEntityDamage(EntityDamageEvent e) {
		GameManager gameManager = BedWars.getInstance().getGameManager();
		
		if(e.getEntity() instanceof Villager) {
			Villager v = (Villager) e.getEntity();
			
			if((v.getCustomName() != null) && v.getCustomName().equals("�b�lShop"))
				e.setCancelled(true);
		} else if(e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			
			if(!(gameManager.isRunning()) || !(gameManager.getInGame().contains(p.getUniqueId())) || gameManager.isEnding())
				e.setCancelled(true);
		}
	}
	
}
