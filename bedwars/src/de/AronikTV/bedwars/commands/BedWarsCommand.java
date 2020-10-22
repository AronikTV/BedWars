package de.AronikTV.bedwars.commands;

import java.util.List;

import de.AronikTV.bedwars.game.GameManager;
import de.AronikTV.bedwars.main.BedWars;
import de.AronikTV.bedwars.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import de.AronikTV.bedwars.utils.GameFileUtils;


public class BedWarsCommand implements CommandExecutor {

	private BedWars bedWars;
	private Utils utils;
	
	
	public BedWarsCommand() {
		this.bedWars = BedWars.getInstance();
		this.utils = bedWars.getUtils();
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		GameFileUtils gameFileUtils = bedWars.getGameFileUtils();
		GameManager gameManager = bedWars.getGameManager();
		
		if(sender instanceof Player) {
			Player p = (Player) sender;
			String prefix = utils.getPrefix();
			
			if(p.hasPermission("bedwars.setup")) {
				if(args.length >= 1) {
					if(args[0].equalsIgnoreCase("setlobby")) {
						gameFileUtils.setLocation("Lobby", p.getLocation());
						
						p.sendMessage(prefix + "�7Die Position �bLobby �7wurde �aerfolgreich �7gesetzt!");
					} else if(args[0].equalsIgnoreCase("createShop")) {
						Villager v = (Villager) p.getWorld().spawnEntity(gameFileUtils.getBetterLocation(p.getLocation()), EntityType.VILLAGER);
						
						v.setCustomName("�b�lSh�f�lop");
						v.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 255));
						v.setVelocity(new Vector(0, 0, 0));
						
						p.sendMessage(prefix + "�7Der �bShop �7wurde �aerfolgreich �7erstellt!");
					} else if(args.length >= 2) {
						if(args[0].equalsIgnoreCase("createMap")) {
							List<String> maps = gameFileUtils.getConfig().getStringList("Maps");
							
							if(!(maps.contains(args[1].toUpperCase()))) {
								maps.add(args[1].toUpperCase());
								gameFileUtils.getConfig().set("Maps", maps);
								gameFileUtils.saveFiles();
								
								p.sendMessage(prefix + "�7Die Map �b" + args[1].toUpperCase() + " �7wurde �aerfolgreich �7erstellt");
							} else
								p.sendMessage(prefix + "�7Die Map �b" + args[1].toUpperCase() + " �7existiert bereits!");
						} else if(args[0].equalsIgnoreCase("deleteMap")) {
							List<String> maps = gameFileUtils.getConfig().getStringList("Maps");
							
							if(maps.contains(args[1].toUpperCase())) {
								maps.remove(args[1].toUpperCase());
								gameFileUtils.getConfig().set("Maps", maps);
								
								String path = "Locations." + args[1].toUpperCase();
								
								gameFileUtils.getConfig().set(path, null);
								gameFileUtils.saveFiles();
								
								p.sendMessage(prefix + "�7Die Map �b" + args[1].toUpperCase() + " �7wurde �aerfolgreich �7gel�scht");
							} else
								p.sendMessage(prefix + "�7Die Map �b" + args[1].toUpperCase() + " �7existiert nicht!");
						} else if(args.length >= 3) {
							if(args[0].equalsIgnoreCase("setSpawn")) {
								if(gameFileUtils.getConfig().getStringList("Maps").contains(args[1].toUpperCase())) {
									if(gameManager.getTeamByName(args[2]) != null) {
										gameFileUtils.setLocation(gameManager.getMap(), "Spawn" + args[2].toUpperCase(), p.getLocation());
										
										
										p.sendMessage(prefix + "�7Die Position �bSpawn-" + args[2].toUpperCase() + " �7wurde �aerfolgreich �7gesetzt!");
									} else
										p.sendMessage(prefix + "�7Das Team �e" + args[2].toUpperCase() + " �7existiert nicht!");
								} else
									p.sendMessage(prefix + "�7Die Map �b" + args[1].toUpperCase() + " �7existiert �cnicht");
							} else if(args[0].equalsIgnoreCase("createSpawner")) {
								if(gameFileUtils.getConfig().getStringList("Maps").contains(args[1].toUpperCase())) {
									if(args[2].equalsIgnoreCase("BRONZE")) {
										int count = 0;
										
										if(gameFileUtils.getConfig().contains("Locations." + args[1].toUpperCase() + ".BronzeCount"))
											count = gameFileUtils.getConfig().getInt("Locations." + args[1].toUpperCase() + ".BronzeCount");
										
										gameFileUtils.getConfig().set("Locations." + args[1].toUpperCase() + ".BronzeCount", count + 1);
										gameFileUtils.saveFiles();
										
										gameFileUtils.setBlockLocation(args[1].toUpperCase(), "Bronze-" + (count + 1), p.getLocation());
										
										p.sendMessage(prefix + "�7Die Position �bBronze-" + (count + 1) + " �7wurde �aerfolgreich �7gesetzt!");
									} else if(args[2].equalsIgnoreCase("IRON")) {
										int count = 0;
										
										if(gameFileUtils.getConfig().contains("Locations." + args[1].toUpperCase() + ".IronCount"))
											count = gameFileUtils.getConfig().getInt("Locations." + args[1].toUpperCase() + ".IronCount");
										
										gameFileUtils.getConfig().set("Locations." + args[1].toUpperCase() + ".IronCount", count + 1);
										gameFileUtils.saveFiles();
										
										gameFileUtils.setBlockLocation(args[1].toUpperCase(), "Iron-" + (count + 1), p.getLocation());
										
										p.sendMessage(prefix + "�7Die Position �bIron-" + (count + 1) + " �7wurde �aerfolgreich �7gesetzt!");
									} else if(args[2].equalsIgnoreCase("GOLD")) {
										int count = 0;
										
										if(gameFileUtils.getConfig().contains("Locations." + args[1].toUpperCase() + ".GoldCount"))
											count = gameFileUtils.getConfig().getInt("Locations." + args[1].toUpperCase() + ".GoldCount");
										
										gameFileUtils.getConfig().set("Locations." + args[1].toUpperCase() + ".GoldCount", count + 1);
										gameFileUtils.saveFiles();
										
										gameFileUtils.setBlockLocation(args[1].toUpperCase(), "Gold-" + (count + 1), p.getLocation());
										
										p.sendMessage(prefix + "�7Die Position �bGold-" + (count + 1) + " �7wurde �aerfolgreich �7gesetzt");
									}
								} else
									p.sendMessage(prefix + "�7Die Map �e" + args[1].toUpperCase() + " �7existiert nicht!");
							} else if(args.length >= 4) {
								if(args[0].equalsIgnoreCase("setBed")) {
									if(gameFileUtils.getConfig().getStringList("Maps").contains(args[1].toUpperCase())) {
										if(gameManager.getTeamByName(args[2]) != null) {
											if(args[3].equalsIgnoreCase("NORTH") || args[3].equalsIgnoreCase("EAST") || args[3].equalsIgnoreCase("SOUTH") || args[3].equalsIgnoreCase("WEST")) {
												gameFileUtils.setBedLocation(gameManager.getMap(), "Bed" + args[2].toUpperCase(), p.getLocation(), args[3].toUpperCase());
												
												p.sendMessage(prefix + "�7Die Position �bBed-" + args[2].toUpperCase() + "�7wurde �aerfolgreich �7gesetzt!");
											} else {
												p.sendMessage(prefix + "�7Das Facing �e" + args[3].toUpperCase() + " �7existiert �cnicht!");
												p.sendMessage(prefix + "�7Alle �bFacings�7: �bNORTH �7| �bEAST �7| �bSOUTH �7| �bWEST�7!");
											}
										} else
											p.sendMessage(prefix + "�7Das Team �b" + args[2].toUpperCase() + " �7existiert nicht!");
									} else
										p.sendMessage(prefix + "�7Die Map �b" + args[1].toUpperCase() + " �7existiert nicht!");
								} else
									sendHelp(p);
							} else
								sendHelp(p);
						} else
							sendHelp(p);
					} else
						sendHelp(p);
				} else
					sendHelp(p);
			} else
				p.sendMessage(prefix + "�4Keine Rechte");
		} else
			utils.sendConsole("�cNur Spieler UHR!!!!");
		
		return true;
	}

	private void sendHelp(Player p) {
		String prefix = utils.getPrefix();
		
		p.sendMessage(prefix + "Betwars Commands �");
		p.sendMessage("�b/bedwars �setLobby�");
		p.sendMessage("�b/bedwars �createShop�");
		p.sendMessage("�b/bedwars �createMap|deleteMap� �Name�");
		p.sendMessage("�b/bedwars �setSpawn� �Map� �TeamName�");
		p.sendMessage("�b/bedwars �createSpawner� �Map� �BRONZE | IRON | GOLD�");
		p.sendMessage("�b/bedwars �setBed� �Map� �TeamName� �NORTH |�EAST |�SOUTH | WEST�");
	}

}
