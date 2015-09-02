package org.romecraft.Shield;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class Shield extends JavaPlugin implements Listener
{
	private static ArrayList<String> blocking = new ArrayList<String>();
	Plugin plugin;
	ShapedRecipe recipe;
	ItemStack rec;
	public int duration = 10;
	int durability;

	@Override
	public void onEnable() 
	{
//		durability = 100;
//
//		rec = new ItemStack(351);
//		ItemMeta a = rec.getItemMeta();
//		ArrayList<String> temp = new ArrayList<String>();
//
//		temp.add("Durability: " + durability + " / 100 ◄");
//		a.setDisplayName(ChatColor.BOLD + "Scutum");
//		a.setLore(temp);
//
//
//		rec.setItemMeta(a);
//
//		recipe = new ShapedRecipe(rec).shape("bib", "iri", "bib").setIngredient('b', Material.CLAY_BRICK).
//				setIngredient('i', Material.IRON_INGOT).setIngredient('r', Material.REDSTONE);
//		getServer().addRecipe(recipe);

		plugin = this;
	}

	@Override
	public void onDisable() {

	}

//	@EventHandler
//	public void onClick(final PlayerInteractEvent e)
//	{
//		if(blocking.contains(e.getPlayer().getName()))return;
//		boolean d = false; //TODO: Find what d does
//		if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK))
//		{
//			if(e.getPlayer().hasPermission("Shield.block"))
//			{
//				if(e.getItem() != null)
//				{
//					if (e.getItem().getItemMeta().hasLore() &&e.getItem().getItemMeta().getLore().get(e.getItem().getItemMeta().getLore().size()-1).endsWith("◄"))
//					{
//						d = true;
//						if(d == true){
//							blocking.add(e.getPlayer().getName());
//							Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
//								public void run() {
//									blocking.remove(e.getPlayer().getName());
//								}
//							}, duration*20);
//						}
//					}
//				}
//			}
//		}
//	}

	@EventHandler(ignoreCancelled = true)
	public void onDamage(EntityDamageByEntityEvent e){
		if(!(e.getEntity() instanceof Player))return;

		Player p = (Player)e.getEntity();

		if(p.getItemInHand().getTypeId() == 351){
			if(e.getDamager() instanceof Arrow){
				if(blocking.contains(p.getName())){
					int dura = Integer.parseInt(p.getItemInHand().getItemMeta().getLore().get(p.getItemInHand().getItemMeta().getLore().size()-1).split(" ")[1]);
					String durab = p.getItemInHand().getItemMeta().getLore().get(p.getItemInHand().getItemMeta().getLore().size() - 1).split(" ")[2];
					ItemStack rec = p.getItemInHand();
					ItemMeta a = rec.getItemMeta();
					dura --;
					List<String> dattemp = a.getLore().subList(0,a.getLore().size()-1);
					dattemp.add("Durability: " + dura + " / " + durab + " ◄");

					a.setLore(dattemp);
					rec.setItemMeta(a);
					if(dura <= 0){
						p.setItemInHand(null);
					}else{
						p.setItemInHand(rec);
					}
					e.setCancelled(true);
					//playEffects(p);
				}
			}else if(p.getLocation().getDirection().dot(e.getDamager().getLocation().getDirection()) < 0){
				if(blocking.contains(p.getName())){
					int dura = Integer.parseInt(p.getItemInHand().getItemMeta().getLore().get(p.getItemInHand().getItemMeta().getLore().size()-1).split(" ")[1]);
					String durab = p.getItemInHand().getItemMeta().getLore().get(p.getItemInHand().getItemMeta().getLore().size() - 1).split(" ")[3];
					ItemStack rec = p.getItemInHand();
					ItemMeta a = rec.getItemMeta();
					dura --;
					List<String> dattemp = a.getLore().subList(0,a.getLore().size()-1);
					dattemp.add("Durability: " + dura + " / " + durab + " ◄");

					a.setLore(dattemp);


					rec.setItemMeta(a);
					if(dura <= 0){
						p.setItemInHand(null);
					}else{
						p.setItemInHand(rec);
					}
					e.setCancelled(true);
					//playEffects(p);
				}
			}
		}
		if(!p.hasPermission(new Permission("Shield.Sblock")))return;
		if(p.isBlocking()){
			if(p.getLocation().getDirection().dot(e.getDamager().getLocation().getDirection()) < 0){
				e.setCancelled(true);
				//playEffects(p);
				p.getItemInHand().setDurability((short) (p.getItemInHand().getDurability() - 5));
			}
		}

	}
	@EventHandler
	public void craftEvent(CraftItemEvent event){
		if(event.getRecipe().getResult().equals(rec)){
			if(!event.getView().getPlayer().hasPermission("Shield.craft")){
				((Player)event.getView().getPlayer()).sendMessage("You do not have permission to craft a shield");
				event.setCancelled(true);
			}else{
			}
		}
	}
}
