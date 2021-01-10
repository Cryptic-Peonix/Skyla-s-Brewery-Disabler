package com.projektskybox.skybrewdisable.listeners;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionType;
import org.bukkit.scheduler.BukkitRunnable;

//import com.projektskybox.skybrewdisable.P;

public class PotionDrinkListener implements Listener {

	Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("SkylasBrewingDisabler");
	// P plug = P.getInstance();

	@EventHandler
	public void onDrinkEvent(PlayerItemConsumeEvent event) {
		
		boolean enabled = plugin.getConfig().getBoolean("drinking-potions.enabled");
		boolean delete = plugin.getConfig().getBoolean("drinking-potions.delete-item");
		boolean wasPotInMainHand = false;
		List<String> drinkDeny = plugin.getConfig().getStringList("drinking-potions.deny-drink-messages");
		String onDelete = plugin.getConfig().getString("drinking-potions.item-deletion-message");

		World world = event.getPlayer().getWorld();

		Random random = new Random();

		// Potion types
		boolean nightVision = plugin.getConfig().getBoolean("drinking-potions.types-to-disable.night-vision");
		boolean invis = plugin.getConfig().getBoolean("drinking-potions.types-to-disable.invis");
		boolean jump = plugin.getConfig().getBoolean("drinking-potions.types-to-disable.jump-boost");
		boolean fire = plugin.getConfig().getBoolean("drinking-potions.types-to-disable.fire-res");
		boolean speed = plugin.getConfig().getBoolean("drinking-potions.types-to-disable.speed");
		boolean slow = plugin.getConfig().getBoolean("drinking-potions.types-to-disable.slowness");
		boolean waterBreathing = plugin.getConfig().getBoolean("drinking-potions.types-to-disable.water-breathing");
		boolean healing = plugin.getConfig().getBoolean("drinking-potions.types-to-disable.healing");
		boolean damage = plugin.getConfig().getBoolean("drinking-potions.types-to-disable.damage");
		boolean poison = plugin.getConfig().getBoolean("drinking-potions.types-to-disable.poison");
		boolean regen = plugin.getConfig().getBoolean("drinking-potions.types-to-disable.regen");
		boolean strength = plugin.getConfig().getBoolean("drinking-potions.types-to-disable.strength");
		boolean weakness = plugin.getConfig().getBoolean("drinking-potions.types-to-disable.weakness");
		boolean luck = plugin.getConfig().getBoolean("drinking-potions.types-to-disable.luck");
		boolean turtle = plugin.getConfig().getBoolean("drinking-potions.types-to-disable.turtle-master");
		boolean slowFall = plugin.getConfig().getBoolean("drinking-potions.types-to-disable.slow-fall");

		if (enabled == false) {
			ItemStack potion = new ItemStack(event.getItem());
			// Check if pot was in main hand
			if (event.getPlayer().getInventory().getItemInMainHand().equals(potion)) {
				wasPotInMainHand = true;
			}
			if (potion.getType() == Material.POTION) {
				PotionMeta meta = (PotionMeta) potion.getItemMeta();
				ArrayList<PotionType> types = new ArrayList<PotionType>();
				if (nightVision == true) {
					types.add(PotionType.NIGHT_VISION);
				}
				if (invis == true) {
					types.add(PotionType.INVISIBILITY);
				}
				if (jump == true) {
					types.add(PotionType.JUMP);
				}
				if (fire == true) {
					types.add(PotionType.FIRE_RESISTANCE);
				}
				if (speed == true) {
					types.add(PotionType.SPEED);
				}
				if (slow == true) {
					types.add(PotionType.SLOWNESS);
				}
				if (waterBreathing == true) {
					types.add(PotionType.WATER_BREATHING);
				}
				if (healing == true) {
					types.add(PotionType.INSTANT_HEAL);
				}
				if (damage == true) {
					types.add(PotionType.INSTANT_DAMAGE);
				}
				if (poison == true) {
					types.add(PotionType.POISON);
				}
				if (regen == true) {
					types.add(PotionType.REGEN);
				}
				if (strength == true) {
					types.add(PotionType.STRENGTH);
				}
				if (weakness == true) {
					types.add(PotionType.WEAKNESS);
				}
				if (luck == true) {
					types.add(PotionType.LUCK);
				}
				if (turtle == true) {
					types.add(PotionType.TURTLE_MASTER);
				}
				if (slowFall == true) {
					types.add(PotionType.SLOW_FALLING);
				}
				for (int i = 0; i < types.size(); i++) {
					if (meta.getBasePotionData().getType() == types.get(i)) {
						event.setCancelled(true);
						String message = drinkDeny.get(random.nextInt(drinkDeny.size()));
						event.getPlayer().sendMessage(message);
						if (delete == true) {
							int slot = event.getPlayer().getInventory().getHeldItemSlot();
							// Delays the potion deletion and second message by 1 second
							BukkitRunnable deleteMsg = new BukkitRunnable() {

								@Override
								public void run() {
									event.getPlayer().getInventory().setHeldItemSlot(slot);
									boolean potInMHand = false;
									boolean potInOffHand = false;
									if (event.getPlayer().getInventory().getItemInMainHand().equals(potion)) {
										potInMHand = true;
									} else if (event.getPlayer().getInventory().getItemInOffHand().equals(potion)) {
										potInOffHand = true;
									}
									
									if (potInMHand == true) {
										event.getPlayer().getInventory().setItemInMainHand(new ItemStack(Material.AIR));
									} else if(potInOffHand == true) {
										event.getPlayer().getInventory().setItemInOffHand(new ItemStack(Material.AIR));
									}
									event.getPlayer().sendMessage(ChatColor.RED + onDelete);
									// plays a glass breaking sfx at the players loaction
									world.playSound(event.getPlayer().getLocation(), Sound.BLOCK_GLASS_BREAK, 10,
											1);
								}

							};
							deleteMsg.runTaskLater(plugin, 20 * 1);
							return;
						} else {
							// If the pot was in the main hand, the potion drops and displays a message
							if (wasPotInMainHand == true) {
								event.getPlayer().dropItem(true);
								event.getPlayer().sendMessage(ChatColor.RED + "You throw the bottle away...");
							} else {
								event.getPlayer().sendMessage(ChatColor.RED + "You deicde not to drink it...");
							}
						}
					}
				}
			}
		}
	}
}
