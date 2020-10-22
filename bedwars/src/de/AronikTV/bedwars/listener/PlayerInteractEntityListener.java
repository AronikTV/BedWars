package de.AronikTV.bedwars.listener;

import de.AronikTV.bedwars.main.BedWars;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;


public class PlayerInteractEntityListener implements Listener {

	@EventHandler
	public void onPlayerInteractEntity(PlayerInteractEntityEvent e) {
		if(e.getRightClicked() instanceof Villager) {
			Villager v = (Villager) e.getRightClicked();
			
			if((v.getCustomName() != null) && v.getCustomName().equals("�b�lSh�f�lop")) {
				e.setCancelled(true);
				BedWars.getInstance().getShopManager().openShopPage(e.getPlayer(), 0);
			}
		}
	}
	
}
