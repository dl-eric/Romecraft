package org.romecraft.Block;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Block extends JavaPlugin implements Listener
{
	
	@Override
	public void onEnable()
	{
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
	}
	@Override
	public void onDisable()
	{

	}
	
	@EventHandler
	public void onClick(final PlayerInteractEvent e)
	{
		if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK))
		{
			
		}
	}
	
	@EventHandler(ignoreCancelled = true)
	public void onDamage(EntityDamageByEntityEvent e)
	{
		Player p = (Player) e.getEntity();

		if(e.getCause() == DamageCause.ENTITY_ATTACK && p.isBlocking())
		{
			if (p.getLocation().getDirection().dot(e.getDamager().getLocation().getDirection()) < 0)
			{
				e.setCancelled(true);
			}
		}
	}
}
