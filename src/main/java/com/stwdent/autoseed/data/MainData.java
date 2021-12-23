package com.stwdent.autoseed.data;

import com.stwdent.autoseed.AutoSeedCore;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainData {

    public static File autoSeedDataFile;
    public static YamlConfiguration autoSeedData;

    static {
        autoSeedDataFile = new File("plugins/" + AutoSeedCore.instance.getName() + "/autoSeedData.yml");
        autoSeedData = YamlConfiguration.loadConfiguration(autoSeedDataFile);
    }

    public static void save() {
        try {
            autoSeedData.save(autoSeedDataFile);
        } catch (IOException e) {
            e.printStackTrace();
            Bukkit.getConsoleSender().sendMessage("§6ST-§bAutoSeed §c데이터를 저장하는 과정에서 오류가 발생했습니다.");
        }
    }

    public static void setAutoSeedItem(ItemStack itemStack) {
        autoSeedData.set("AutoSeedItem", itemStack);
    }

    public static ItemStack getAutoSeedItem(int time) {
        ItemStack item = (autoSeedData.getItemStack("AutoSeedItem") == null) ? new ItemStack(Material.STONE) : autoSeedData.getItemStack("AutoSeedItem");
        ItemMeta itemMeta = !item.hasItemMeta() ? Bukkit.getItemFactory().getItemMeta(Material.STONE) : item.getItemMeta();
        itemMeta.setLocalizedName("ST_AutoSeed_Item_" + time);
        List<String> itemLore = new ArrayList<>();
        /*
        for(String currentLore : itemMeta.getLore()) {
            itemLore.add(currentLore.replace("%time%", String.valueOf(time)));
        }
        itemMeta.setLore(itemLore);
        */
        item.setItemMeta(itemMeta);
        return item;
    }

}
