package it.codedev.nofallkitspawn;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class NoFallKitSpawn extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(this, this);

        getLogger().info("Plugin enabled!");
        getLogger().info("------------------------");
        getLogger().info("Created by: CodeDevv_");
        getLogger().info("Version: 0.2");
        getLogger().info("------------------------");
    }

    @EventHandler
    public void antiFall(EntityDamageEvent e) {
        if(!e.getCause().equals(EntityDamageEvent.DamageCause.FALL))
            return;
        if(!(e.getEntity() instanceof Player))
            return;
        Player player = ((Player) e.getEntity()).getPlayer();
        int h;
        try {
            h = getConfig().getInt("spawn-height");
        } catch (NumberFormatException ex) {
            getLogger().severe("Invalid height! (Invalid number value)");
            return;
        }
        if(player.getFallDistance() > h)
            e.setCancelled(true);
    }
}
