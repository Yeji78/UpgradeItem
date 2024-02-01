package com.test.UpgradeItem;


import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


import static com.test.UpgradeItem.LoadLore.ItemMap;
import static com.test.UpgradeItem.LoadLore.loadConfig;


public class Command implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {

        if (sender instanceof Player){
            Player player = (Player) sender;
            //指令为lorereplace reload
            if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
                ItemMap.clear();
                loadConfig();
                player.sendMessage("§b[UpgradeItem]§6插件重载成功");
                return true;
            }
        }
        return true;
    }
}
