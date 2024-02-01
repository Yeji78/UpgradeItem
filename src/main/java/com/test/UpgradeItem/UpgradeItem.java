package com.test.UpgradeItem;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;


import java.io.File;

import static com.test.UpgradeItem.LoadLore.loadConfig;


public final class UpgradeItem extends JavaPlugin {
public static UpgradeItem instance;
    @Override
    public void onEnable() {
        // Plugin startup logic
        //如果文件夹中没有lang.yml 新建一个
        if (!(new File(this.getDataFolder(), "lang.yml").exists())){
            this.saveResource("lang.yml", false);
        }
        instance = this;
        //保存初始CONFIG
        this.saveDefaultConfig();
        //注册监听器
        Bukkit.getPluginManager().registerEvents(new OnInventoryClick(),this);
        //在后台发送信息
        getLogger().info("插件已安装");
        //注册指令
        Bukkit.getPluginCommand("UpgradeItem").setExecutor(new Command());
        //加载文件
        loadConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public static UpgradeItem getInstance(){
        return instance;
    }
}
