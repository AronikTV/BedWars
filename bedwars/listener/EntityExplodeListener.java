package de.AronikTV.bedwars.listener;

import de.AronikTV.bedwars.game.GameManager;
import de.AronikTV.bedwars.main.BedWars;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;


public class EntityExplodeListener implements Listener {

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onEntityExplode(EntityExplodeEvent e) {
		GameManager gameManager = BedWars.getInstance().getGameManager();
		
		if(e.getEntity() instanceof TNTPrimed) {
			e.setCancelled(true);
			e.setYield(0F);
			
			for (Player all : Bukkit.getOnlinePlayers()) {
				all.playSound(e.getLocation(), Sound.EXPLODE, 5, 5);
				all.playEffect(e.getLocation(), Effect.EXPLOSION_LARGE, 100);
				
				if(((int) all.getLocation().distanceSquared(e.getLocation())) < 10) {
					all.setVelocity(all.getLocation().getDirection().multiply(-1.4).setY(0.5));
					all.damage((10 - all.getLocation().distanceSquared(e.getLocation())) * 1.5);
				}
			}
			
			for (Block b : e.blockList()) {
				if(gameManager.getBuildedBlocks().contains(b.getLocation())) {
					b.setType(Material.AIR);
					
					gameManager.getBuildedBlocks().remove(b.getLocation());
				}
			}
		}
	}
	
}
