package com.test.UpgradeItem;


import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Set;


public class LoadLore {
    public static Set<String> allKeys;
    public static HashMap<String,Example> ItemMap = new HashMap<>();
    public static String lang = null;
    public static String failsTips = null;

    public static void loadConfig() {
        //获取这两个文件夹
        final YamlConfiguration file = YamlConfiguration.loadConfiguration(new File(UpgradeItem.instance.getDataFolder(), "\\config.yml"));
        final YamlConfiguration langfile = YamlConfiguration.loadConfiguration(new File(UpgradeItem.instance.getDataFolder(), "\\lang.yml"));
        //获取config中所有的key
        allKeys = file.getKeys(false);
        //获取lang中的tips词条
        lang = stringColorTranslate(langfile.getString("Tips"));
        failsTips =stringColorTranslate(langfile.getString("FailsTips"));
        //遍历所有key
        for (String s : allKeys) {
            //初始化
            String FirstItemLore = null;
            String ItemName = null;
            List<String> GradeItemLore = new ArrayList<>();
            List<String> ReplaceLore = new ArrayList<>();
            int Success = 100;
            //获取文件夹中的两个值
            if (file.getString(s + ".FirstItemLore") != null) {
                FirstItemLore = stringColorTranslate(file.getString(s + ".FirstItemLore"));


            }


            if (file.getString(s + ".ItemName") != null) {
                ItemName = stringColorTranslate(file.getString(s + ".ItemName"));

            }

            if (file.getStringList(s + ".GradeItemLore").size()>0) {
                List<String> oldGradeItemLore = file.getStringList(s + ".GradeItemLore");
                for (String a : oldGradeItemLore) {
                    String lore = stringColorTranslate(a);
                    GradeItemLore.add(lore);
                }
            }
            if (file.getStringList(s + ".ReplaceLore").size()>0) {
                List<String> oldReplaceLore= file.getStringList(s + ".ReplaceLore");
                for (String a : oldReplaceLore) {
                    String lore = stringColorTranslate(a);
                    ReplaceLore.add(lore);
                }
            }
            if (file.getInt(s + ".Success")>=0) {
                Success= file.getInt(s + ".Success");
            }


            //如果值不为空加入hashmap
            if (FirstItemLore != null && ItemName != null && GradeItemLore.size() > 0 && ReplaceLore.size() > 0) {
                Example example = new Example(FirstItemLore,ItemName,GradeItemLore,ReplaceLore,Success);
                ItemMap.put(FirstItemLore,example);


            }
        }
    }

    public static String stringColorTranslate(String string) {

        return string.replace("&", "§");
    }
}
