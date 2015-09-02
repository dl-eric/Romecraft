package org.romecraft.Block;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.plugin.java.JavaPlugin;

public class Block extends JavaPlugin implements Listener
{
	@Override
	public void onEnable()
	{
		
	}
	@Override
	public void onDisable()
	{

	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onDamage(EntityDamageByEntityEvent e)
	{
		Player p = (Player) e.getEntity();

		if(e.getCause() == DamageCause.ENTITY_ATTACK && p.isBlocking())
		{
			e.setCancelled(true);
		}
	}
}
