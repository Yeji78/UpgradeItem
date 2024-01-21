package com.test.lorereplace;



import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;
import java.util.HashMap;

import java.util.Set;


public class LoadLore {
    public static Set<String> allKeys;
    public static HashMap<String, String> LoreMap = new HashMap<>();
    public static String lang = null;

    public static void loadConfig() {
        //获取这两个文件夹
        final YamlConfiguration file = YamlConfiguration.loadConfiguration(new File(LoreReplace.instance.getDataFolder(), "\\config.yml"));
        final YamlConfiguration langfile = YamlConfiguration.loadConfiguration(new File(LoreReplace.instance.getDataFolder(),"\\lang.yml"));
        //获取config中所有的key
        allKeys = file.getKeys(false);
        //获取lang中的tips词条
        lang = stringColorTranslate(langfile.getString("Tips"));
        //遍历所有key
        for (String s : allKeys) {
            //初始化
            String firstLore = null;
            String replaceLore = null;
            //获取文件夹中的两个值
            if (file.getString(s + ".FirstLore") != null) {
                firstLore = stringColorTranslate(file.getString(s + ".FirstLore"));
            }
            if (file.getString(s + ".ReplaceLore") != null) {
                replaceLore = stringColorTranslate(file.getString(s + ".ReplaceLore"));
            }
            //如果值不为空加入hashmap
            if (firstLore != null && replaceLore != null) {
                LoreMap.put(firstLore, replaceLore);
            }
        }
    }

    public static String stringColorTranslate(String string) {

        return string.replace("&", "§");
    }
}
