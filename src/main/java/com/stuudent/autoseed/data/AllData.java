package com.stuudent.autoseed.data;

import com.stuudent.autoseed.AutoSeedCore;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class AllData {

    public static File autoSeedDataFile;
    public static YamlConfiguration autoSeedData;

    static {
        autoSeedDataFile = new File("plugins/" + AutoSeedCore.instance.getName() + "/autoSeedData.yml");
        autoSeedData = YamlConfiguration.loadConfiguration(autoSeedDataFile);
    }


}
