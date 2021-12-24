package com.stwdent.autoseed.commands;

import com.stwdent.autoseed.AutoSeedAPI;
import com.stwdent.autoseed.AutoSeedCore;
import com.stwdent.autoseed.data.MainData;
import com.stwdent.autoseed.interfaces.AutoSeedPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AdminCommand implements TabExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
        if(!cmd.getName().equals("자동심기관리")) return false;
        if(args.length == 0) {
            sender.sendMessage("/자동심기관리 생성 [초] - 자동심기 아이템을 생성합니다.");
            sender.sendMessage("/자동심기관리 보기 [닉네임] - 유저의 잔여시간을 보여줍니다.");
            sender.sendMessage("/자동심기관리 설정 [닉네임] [시간] - 유저의 잔여시간을 설정합니다.");
            sender.sendMessage("/자동심기관리 초기화 - 모든 유저의 데이터를 초기화합니다.");
            sender.sendMessage("/자동심기관리 아이템설정 - 자동심기 아이템을 들고있는 아이템으로 설정합니다.");
            sender.sendMessage("/자동심기관리 리로드 - 콘피그를 리로드합니다. (config.yml)");
            return false;
        }
        if(args[0].equals("생성")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("§c버킷에서는 이 명령어를 사용할 수 없습니다.");
                return false;
            }
            Player player = (Player) sender;
            int second;
            try {
                second = args.length == 1 ? 60 : Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                second = 60;
            }
            player.getInventory().addItem(MainData.getAutoSeedItem(second));
            player.sendMessage("자동심기 아이템이 생성되었습니다. (§6" + second + "§r초)");
            return false;
        }
        if(args[0].equals("보기")) {
            if (args.length == 1 && !(sender instanceof Player)) {
                sender.sendMessage("§c닉네임을 입력해주세요.");
                return false;
            }
            OfflinePlayer target = (args.length == 1) ? (Player) sender : Bukkit.getOfflinePlayer(args[1]);
            if (!target.hasPlayedBefore()) {
                sender.sendMessage("§c해당 유저는 존재하지 않습니다.");
                return false;
            }
            AutoSeedPlayer autoSeedPlayer = AutoSeedAPI.getPlayer(target);
            sender.sendMessage("유저 (§6" + target.getName() + "§r) 님의 잔여시간 : " + autoSeedPlayer.getAutoSeedTime() + "초");
            return false;
        }
        if(args[0].equals("설정")) {
            if (args.length == 1 && !(sender instanceof Player)) {
                sender.sendMessage("§c닉네임을 입력해주세요.");
                return false;
            }
            OfflinePlayer target = (args.length == 1) ? (Player) sender : Bukkit.getOfflinePlayer(args[1]);
            if (!target.hasPlayedBefore()) {
                sender.sendMessage("§c해당 유저는 존재하지 않습니다.");
                return false;
            }
            int time;
            try {
                time = args.length == 2 ? 0 : Integer.parseInt(args[2]);
            } catch (NumberFormatException e) {
                time = 0;
            }
            AutoSeedPlayer autoSeedPlayer = AutoSeedAPI.getPlayer(target);
            autoSeedPlayer.setAutoSeedTime(time);
            sender.sendMessage("유저 (§6" + target.getName() + "§r) 님의 잔여시간 : " + autoSeedPlayer.getAutoSeedTime() + "초");
            return false;
        }
        if(args[0].equals("초기화")) {
            for (OfflinePlayer offlinePlayer : Bukkit.getOfflinePlayers()) {
                AutoSeedPlayer autoSeedPlayer = AutoSeedAPI.getPlayer(offlinePlayer);
                autoSeedPlayer.setAutoSeedStatus(false);
                autoSeedPlayer.setAutoSeedTime(0);
            }
            sender.sendMessage("유저들의 자동심기 데이터가 초기화되었습니다.");
            return false;
        }
        if(args[0].equals("아이템설정")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("§c버킷에서는 이 명령어를 사용할 수 없습니다.");
                return false;
            }
            Player player = (Player) sender;
            ItemStack item = player.getInventory().getItemInMainHand();
            if(item.getType().equals(Material.AIR)) {
                sender.sendMessage("§c손에 아이템을 들어주세요.");
                return false;
            }
            MainData.setAutoSeedItem(item);
            sender.sendMessage("자동심기 아이템이 설정되었습니다.");
            return false;
        }
        if(args[0].equals("리로드")) {
            AutoSeedCore.instance.reloadConfig();
            AutoSeedCore.cf = AutoSeedCore.instance.getConfig();
            sender.sendMessage("리로드가 완료되었습니다.");
            return false;
        }
        sender.sendMessage("/자동심기관리");
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
        if(cmd.getName().equals("자동심기관리")) {
            List<String> available = new ArrayList<>();
            if(args.length == 1) {
                List<String> args0 = Arrays.asList("생성", "보기", "설정", "초기화", "아이템설정");
                for(String arg : args0) if(arg.toLowerCase().startsWith(args[0].toLowerCase())) available.add(arg);
            }
            else if(args.length == 2) {
                if(args[0].equals("보기") || args[0].equals("설정")) {
                    List<String> args1 = new ArrayList<>();
                    for(OfflinePlayer offlinePlayer : Bukkit.getOfflinePlayers()) args1.add(offlinePlayer.getName());
                    for(String arg : args1) if(arg.toLowerCase().startsWith(args[1].toLowerCase())) available.add(arg);
                }
            }
            return available;
        }
        return null;
    }

}
