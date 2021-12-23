package com.stwdent.autoseed.interfaces;

import org.bukkit.OfflinePlayer;

public interface AutoSeedPlayer {

    void setAutoSeedTime(int time);
    void removeAutoSeedTime(int time);
    void addAutoSeedTime(int time);
    int getAutoSeedTime();
    void setAutoSeedStatus(boolean status);
    boolean isAutoSeedEnabled();

    OfflinePlayer getBukkitPlayer();

}
