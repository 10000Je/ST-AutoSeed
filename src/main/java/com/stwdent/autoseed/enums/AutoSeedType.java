package com.stwdent.autoseed.enums;

import org.bukkit.Material;

public enum AutoSeedType {

    CROPS(Material.CROPS), CARROT(Material.CARROT), POTATO(Material.POTATO),
    BEETROOT_BLOCK(Material.BEETROOT_BLOCK), NETHER_WARTS(Material.NETHER_WARTS), COCOA(Material.COCOA);

    private final Material data;

    private AutoSeedType(Material data) {
        this.data = data;
    }

    public Material getData() {
        return this.data;
    }

}
