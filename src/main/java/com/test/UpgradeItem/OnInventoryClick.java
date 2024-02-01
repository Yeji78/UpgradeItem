package com.test.UpgradeItem;


import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


import static com.test.UpgradeItem.LoadLore.*;


public class OnInventoryClick implements Listener {
    @EventHandler
    public void InventoryClick(InventoryClickEvent event) {

        ItemStack firstItem = event.getCursor();
        List<String> firstLore;
        List<String> secondLore;
        Player player = (Player) event.getWhoClicked();
        if (firstItem.hasItemMeta() && firstItem.getItemMeta().hasLore()) {
            //拿起的物品的Lore，即为替换后的lore
            List<String> tempFirstLore = new ArrayList<>();
            for (String s : firstItem.getItemMeta().getLore()) {
                tempFirstLore.add(stringColorTranslate(s));
            }
            firstLore = tempFirstLore;
        } else return;

        ItemStack secondItem = event.getCurrentItem();
        if (secondItem.hasItemMeta() && secondItem.getItemMeta().hasLore()) {
            //要替换的Lore
            List<String> tempSecondLore = new ArrayList<>();
            for (String s : secondItem.getItemMeta().getLore()) {
                tempSecondLore.add(stringColorTranslate(s));
            }
            secondLore = tempSecondLore;
        } else return;

        Object[] set = ItemMap.keySet().toArray();
        //firstLore的配置集合strings
        String[] loreKey = Arrays.copyOfRange(set, 0, set.length, String[].class);
        ItemMeta itemMeta = secondItem.getItemMeta();
        int secondItemAmount = secondItem.getAmount();
        int firstItemAmount = firstItem.getAmount();
        for (String Lore : loreKey) {
            Example appointFormula = ItemMap.get(Lore);
            String FirstItemLore = appointFormula.getFirstItemLore();
            String Name = appointFormula.getItemName();
            List<String> GradeItemLore = appointFormula.getGradeItemLore();
            List<String> ReplaceLore = appointFormula.getReplaceLore();
            if (secondItem.getItemMeta().getDisplayName().contains(Name) && firstLore.contains(FirstItemLore) &&
                    secondLore.contains(GradeItemLore.get(0)) && secondLore.contains(GradeItemLore.get(1))) {
                firstItem.setAmount(0);
                int index = (int) (Math.random() * 100);
                if (index < appointFormula.getSuccess()) {
                    int num1 = 0;
                    int num2 = 0;
                    int num = 0;
                    for (String b : secondLore) {
                        num++;
                        if (b.equals(GradeItemLore.get(0))) {
                            num1 = num;
                        }
                        if (b.equals(GradeItemLore.get(1))) {
                            num2 = num - 1;
                        }
                    }
                    List<String> list1 = new CopyOnWriteArrayList<>(secondLore.subList(0, num1));
                    List<String> list2 = secondLore.subList(num2, secondLore.size());
                    list1.addAll(ReplaceLore);
                    list1.addAll(list2);
                    if (secondItemAmount == 1 && firstItemAmount == 1) {
                        //设置第二个物品的lore
                        itemMeta.setLore(list1);
                        secondItem.setItemMeta(itemMeta);
                        //对玩家发送lang.Yml中的消息
                        player.sendMessage(lang);
                    } else return;
                } else {
                    player.sendMessage(failsTips);
                    return;
                }
            } else return;
        }
    }
}