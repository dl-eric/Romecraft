package org.romecraft.Block;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
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
	private ArrayList<String> blocking;

	@Override
	public void onEnable()
	{
		blocking = new ArrayList<String>();
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
	}
	@Override
	public void onDisable()
	{

	}

	@EventHandler
	public void onClick(final PlayerInteractEvent e)
	{
		final String playername = e.getPlayer().getName();
		if (e.getPlayer().isBlocking() && blocking.contains(playername))
			return;
		if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK))
		{
			if(e.getMaterial().equals(Material.GOLD_SWORD) || e.getMaterial().equals(Material.IRON_SWORD) || e.getMaterial().equals(Material.DIAMOND_SWORD) || e.getMaterial().equals(Material.WOOD_SWORD))
			{			
				blocking.add(playername);

				Bukkit.getScheduler().runTaskLater(this, new Runnable() {
					public void run()
					{
						blocking.remove(playername);
					}
				}, 1 * 20); //20 ticks per second. 2*20 = 2 seconds.
			}
		}
	}

	@EventHandler(ignoreCancelled = true)
	public void onDamage(EntityDamageByEntityEvent e)
	{
		if(!(e.getEntity() instanceof Player)) 
			return;

		Player p = (Player) e.getEntity();

		if(blocking.contains(p.getName()))
		{
			if(e.getCause() == DamageCause.ENTITY_ATTACK)
			{
				if (p.getLocation().getDirection().dot(e.getDamager().getLocation().getDirection()) < 0)
				{
					e.setCancelled(true);
				}
			}
		}
	}
}
