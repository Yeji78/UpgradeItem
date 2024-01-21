package com.test.lorereplace;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;


import static com.test.lorereplace.LoadLore.*;
import static org.bukkit.GameMode.CREATIVE;

public class OnInventoryClick implements Listener {
    @EventHandler
    public void InventoryClick(InventoryClickEvent event) {

        ItemStack firstItem = event.getCursor();
        List<String> firstLore;
        List<String> secendLore;
        Player player = (Player) event.getWhoClicked();
        GameMode gameMode = player.getGameMode();

        if (firstItem.hasItemMeta() && firstItem.getItemMeta().hasLore()) {
            //拿起的物品的Lore，即为替换后的lore
            firstLore = firstItem.getItemMeta().getLore();
        } else return;
        ItemStack secendItem = event.getCurrentItem();
        if (secendItem.hasItemMeta() && secendItem.getItemMeta().hasLore()) {
            //要替换的Lore
            secendLore = secendItem.getItemMeta().getLore();
        } else return;
        Object[] set = LoreMap.keySet().toArray();
        //firstLore的配置集合strings
        String[] strings = Arrays.copyOfRange(set, 0, set.length, String[].class);
        ItemMeta itemMeta = secendItem.getItemMeta();
        int c = secendItem.getAmount();
        int b = firstItem.getAmount();
        //遍历strings
        for (String f : strings) {
            String s = LoreMap.get(f);
            //各种限制
            if (firstLore.contains(f) && secendLore.contains(s)) {
                if (c == 1 && b == 1 && gameMode != CREATIVE) {
                    for (int i = 0; i < secendLore.size(); i++) {
                        if (secendLore.get(i).equals(s)) {
                            firstItem.setAmount(0);
                            //设置第二个物品的lore
                            secendLore.set(i, f);
                            itemMeta.setLore(secendLore);
                            secendItem.setItemMeta(itemMeta);
                            //对玩家发送lang.Yml中的消息
                            player.sendMessage(lang);
                            break;
                        }
                    }

                }
            }
        }
    }
}
