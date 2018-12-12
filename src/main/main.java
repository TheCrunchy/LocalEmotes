package main;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.java.JavaPlugin;

import com.earth2me.essentials.Essentials;
import com.earth2me.essentials.User;

public class main extends JavaPlugin{
    String message;
    private Essentials ess;
    public void onEnable() {
    	this.getCommand("emote").setExecutor(new emote());
    	this.getCommand("em").setExecutor(new emote());
    	this.getCommand("me").setExecutor(new emote());
    	ess = (Essentials) Bukkit.getPluginManager().getPlugin("Essentials");
    }
    // Fired when plugin is disabled
    @Override
    public void onDisable() {
    }
    public class emote implements CommandExecutor {

        // This method is called, when somebody uses our command
        @Override
        @EventHandler
        public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        	if (sender instanceof Player) {
        		Player player = (Player) sender;
                String playernick;
                List<Entity> entities = player.getNearbyEntities(20,20,20);
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < args.length; i++){
                sb.append(args[i]).append(" ");
                }
                User user = ess.getUser(player);
                playernick = user.getNickname();
                if (playernick == null)
                {
                	playernick = sender.getName().toString();
                }
                
            	String nick = player.getDisplayName();
                String tempMessage = sb.toString().trim();
                if (tempMessage.equals("")){
                	player.sendMessage("An action is required!");
                	return true;
                }
                message =  ChatColor.WHITE + "[*] " +  ChatColor.GREEN + playernick + " " +  ChatColor.GREEN + tempMessage;
                System.out.println(message);
                player.sendMessage(message);
                //Iterate through the list and check if the entity is a player
                for(Entity entity : entities) {
                    if(entity instanceof Player) {

                    	//player = ((Player) entity).getPlayer();
                        entity.sendMessage(message);
                    }
                }
        	}
        	
			return true;
        }
    }
}

