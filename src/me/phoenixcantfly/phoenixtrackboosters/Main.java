package me.phoenixcantfly.phoenixtrackboosters;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class Main extends JavaPlugin implements Listener {
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e){
        Player player = e.getPlayer();
        Location playerLocation = player.getLocation();
        Block blockStanding = playerLocation.getBlock().getRelative(BlockFace.DOWN);
        if(blockStanding.getType().equals(Material.EMERALD_BLOCK)) {
            //player.setVelocity(e.getPlayer().getLocation().getDirection().multiply(1.00).setY(1.75));
            player.setVelocity(new Vector(0, 0.4+1.2, 0));
        } else if (blockStanding.getType().equals(Material.SMOOTH_QUARTZ)){
            player.setVelocity(playerLocation.getDirection().multiply(0.75).setY(1.2));
        } else if (blockStanding.getType().equals(Material.GOLD_BLOCK)){
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, (int)(1.0*20), 4, true, false));
        } else {
            //break out
            return;
        }
        //sfx will play for both conditions
        player.playSound(playerLocation, Sound.ITEM_TRIDENT_RIPTIDE_1, 0.7F, 0.75F);
        player.playSound(playerLocation, Sound.ENTITY_WITHER_SHOOT, 0.4F, 0.55F);
        playerLocation.getWorld().spawnParticle(Particle.CLOUD, playerLocation, 50, 0.2F, 1, 0.2F, 0.01F);
    }

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }
}
