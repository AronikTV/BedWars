package de.AronikTV.bedwars.main;

import java.lang.reflect.Field;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R3.PlayerConnection;



public class TitleAPI {
	
	public static void sendTitle(String title, Player p, String subtitle, int fadein, int fadeout,  int stay) {
		CraftPlayer p1 = (CraftPlayer) p ;
		PlayerConnection Con = p1.getHandle().playerConnection;
		IChatBaseComponent Itle = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + title + "\"}");
		IChatBaseComponent Subitle = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + subtitle + "\"}");
		PacketPlayOutTitle titletime = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TIMES, Itle, fadein, stay, fadeout);
		PacketPlayOutTitle subtitletime = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TIMES, Subitle);
		PacketPlayOutTitle TitlePacket = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, Subitle);
		PacketPlayOutTitle SubPacket = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, Itle);
		Con.sendPacket(titletime);
		Con.sendPacket(subtitletime);
		Con.sendPacket(TitlePacket);
		Con.sendPacket(SubPacket);
	}

	public static void sendActionBar(String ActionBarTitle, Player p) {
		PlayerConnection Con1 = ((CraftPlayer)p).getHandle().playerConnection;
		IChatBaseComponent chat = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + ActionBarTitle + "\"}");
		PacketPlayOutChat ActionBarPacket = new PacketPlayOutChat(chat, (byte) 2);
		
		;
		
		Con1.sendPacket(ActionBarPacket);
		
	}
	
	public static void sendTablist(String Header, String Footer, Player... p) {
		IChatBaseComponent header = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + Header + "\"}");
		IChatBaseComponent footer = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + Footer + "\"}");
		
		PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter();
		
		try {
			Field headerField = packet.getClass().getDeclaredField("a");
			headerField.setAccessible(true);
			headerField.set(packet, header);
			headerField.setAccessible(false);
			
			Field footerField = packet.getClass().getDeclaredField("b");
			footerField.setAccessible(true);
			footerField.set(packet, footer);
			footerField.setAccessible(false);
			
		}catch(Exception ex) {}
		
		for(Player player : p) {
			((CraftPlayer)player).getHandle().playerConnection.sendPacket(packet);
			
		}
	}
}
