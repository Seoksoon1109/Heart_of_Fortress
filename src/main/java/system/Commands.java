//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package system;



import data.PlayerData;
import ingame.MoneySystem;
import ingame.gui.UpgradeAbility;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import java.util.UUID;


public class Commands implements CommandExecutor {
    public Commands() {
    }
    
    
    @SuppressWarnings("deprecation")
    public boolean onCommand(@NotNull CommandSender sender, Command cmd, @NotNull String label, @NotNull String[] args) {
        if (cmd.getName().equalsIgnoreCase("h") && sender instanceof Player) {
            Player player = (Player)sender;
            UUID uuid = player.getUniqueId();
            if (args[0].equalsIgnoreCase("cc")) {
                if (PlayerData.isRed(player)) {
                    if ((PlayerData.chatType.get(uuid)).equals(ChatType.all)) {
                        PlayerData.chatType.put(uuid, ChatType.red);
                        player.sendMessage(ChatColor.GOLD + "[TeamChat] : " + ChatColor.WHITE + "[ALL] -> " + ChatColor.RED + "[RED]");
                    } else {
                        PlayerData.chatType.put(uuid, ChatType.all);
                        player.sendMessage(ChatColor.GOLD + "[TeamChat] : " + ChatColor.RED + "[RED]" + ChatColor.WHITE + " -> [ALL]");
                    }
                } else if (PlayerData.isBlue(player)) {
                    if ((PlayerData.chatType.get(uuid)).equals(ChatType.all)) {
                        PlayerData.chatType.put(uuid, ChatType.blue);
                        player.sendMessage(ChatColor.GOLD + "[TeamChat] : " + ChatColor.WHITE + "[ALL] -> " + ChatColor.BLUE + "[BLUE]");
                    } else {
                        PlayerData.chatType.put(uuid, ChatType.all);
                        player.sendMessage(ChatColor.GOLD + "[TeamChat] : " + ChatColor.BLUE + "[BLUE]" + ChatColor.WHITE + " -> [ALL]");
                    }
                } else {
                    player.sendMessage(ChatColor.GOLD + "[TeamChat] : " + ChatColor.WHITE + "[ALL] -> [ALL]");
                }
            } else if (args[0].equalsIgnoreCase("start") && !GameManager.isStart) {
                if (player.isOp()) {
                    GameManager.startingGame();
                } else {
                    player.sendMessage(ChatColor.GOLD + "[요새의 심장]" + ChatColor.RED + "관리자가 아닙니다!");
                }
            } else {
                if (args[0].equalsIgnoreCase("stop") && GameManager.isStart) {
                    if (player.isOp()) {
                        Bukkit.broadcastMessage(ChatColor.GOLD + "[요새의 심장]" + ChatColor.RED + "관리자가 게임을 중지시켰습니다");
                        GameManager.resetGame();
                    } else {
                        player.sendMessage(ChatColor.GOLD + "[요새의 심장]" + ChatColor.RED + "관리자가 아닙니다!");
                    }
                } else if (args[0].equalsIgnoreCase("join") && !GameManager.isStart) {
                    if (args.length == 1) {
                        player.sendMessage(ChatColor.GOLD + "[요새의 심장]" + ChatColor.RED + "팀명을 적어주세요!");
                    } else if (PlayerData.hasTeam(player)) {
                        if (PlayerData.isRed(player) && args[1].equalsIgnoreCase("blue")) {
                            TeamSystem.red.removePlayer(player);
                            TeamSystem.blue.addPlayer(player);
                            Bukkit.broadcastMessage(ChatColor.GOLD + "[요새의 심장]" + ChatColor.DARK_AQUA + player.getName() + ChatColor.WHITE + "님의 팀이 변경되었습니다. " + ChatColor.RED + "[RED]" + ChatColor.WHITE + " -> " + ChatColor.BLUE + "[BLUE]");
                        } else if (PlayerData.isBlue(player) && args[1].equalsIgnoreCase("red")) {
                            TeamSystem.blue.removePlayer(player);
                            TeamSystem.red.addPlayer(player);
                            Bukkit.broadcastMessage(ChatColor.GOLD + "[요새의 심장]" + ChatColor.DARK_AQUA + player.getName() + ChatColor.WHITE + "님의 팀이 변경되었습니다. " + ChatColor.BLUE + "[BLUE]" + ChatColor.WHITE + " -> " + ChatColor.RED + "[RED]");
                        } else if (PlayerData.isBlue(player) && args[1].equalsIgnoreCase("blue")) {
                            player.sendMessage(ChatColor.GOLD + "[요새의 심장]" + ChatColor.YELLOW + "이미 " + ChatColor.BLUE + "[BLUE]" + ChatColor.YELLOW + "팀에 참가되어 있습니다.");
                        } else if (PlayerData.isRed(player) && args[1].equalsIgnoreCase("red")) {
                            player.sendMessage(ChatColor.GOLD + "[요새의 심장]" + ChatColor.YELLOW + "이미 " + ChatColor.RED + "[RED]" + ChatColor.YELLOW + "팀에 참가되어 있습니다.");
                        }
                    } else if (!PlayerData.hasTeam(player)) {
                        if (args[1].equalsIgnoreCase("red")) {
                            TeamSystem.red.addPlayer(player);
                            Bukkit.broadcastMessage(ChatColor.GOLD + "[요새의 심장]" + ChatColor.DARK_AQUA + player.getName() + ChatColor.WHITE + "님이 [" + ChatColor.RED + "RED" + ChatColor.WHITE + "]팀에 참가하셨습니다.");
                        } else if (args[1].equalsIgnoreCase("blue")) {
                            TeamSystem.blue.addPlayer(player);
                            Bukkit.broadcastMessage(ChatColor.GOLD + "[요새의 심장]" + ChatColor.DARK_AQUA + player.getName() + ChatColor.WHITE + "님이 [" + ChatColor.BLUE + "BLUE" + ChatColor.WHITE + "]팀에 참가하셨습니다.");
                        } else {
                            player.sendMessage(ChatColor.GOLD + "[요새의 심장]" + ChatColor.RED + "올바른 팀명을 적어주세요!");
                        }
                    }
                } else if (args[0].equalsIgnoreCase("join") && GameManager.isStart) {
                    Bukkit.broadcastMessage(ChatColor.GOLD + "[요새의 심장]" + ChatColor.RED + "게임이 진행중입니다!");
                } else if (args[0].equalsIgnoreCase("out") && !GameManager.isStart) {
                    if (!PlayerData.hasTeam(player)) {
                        player.sendMessage(ChatColor.GOLD + "[요새의 심장]" + ChatColor.RED + "팀에 참가되어 있지 않습니다!");
                    }

                    if (PlayerData.hasTeam(player)) {
                        if (TeamSystem.red.getPlayers().contains(player)) {
                            TeamSystem.red.removePlayer(player);
                            Bukkit.broadcastMessage(ChatColor.GOLD + "[요새의 심장]" + ChatColor.DARK_AQUA + player.getName() + ChatColor.WHITE + "님이 <" + ChatColor.RED + "RED" + ChatColor.WHITE + ">팀을 탈퇴하셨습니다.");
                            if ((PlayerData.chatType.get(uuid)).equals(ChatType.red)) {
                                PlayerData.chatType.put(uuid, ChatType.all);
                                player.sendMessage(ChatColor.GOLD + "[TeamChat] : " + ChatColor.RED + "[RED]" + ChatColor.WHITE + " -> [ALL]");
                            }
                        } else if (TeamSystem.blue.getPlayers().contains(player)) {
                            TeamSystem.blue.removePlayer(player);
                            Bukkit.broadcastMessage(ChatColor.GOLD + "[요새의 심장]" + ChatColor.DARK_AQUA + player.getName() + ChatColor.WHITE + "님이 <" + ChatColor.BLUE + "BLUE" + ChatColor.WHITE + ">팀을 탈퇴하셨습니다.");
                            if ((PlayerData.chatType.get(uuid)).equals(ChatType.blue)) {
                                PlayerData.chatType.put(uuid, ChatType.all);
                                player.sendMessage(ChatColor.GOLD + "[TeamChat] : " + ChatColor.BLUE + "[BLUE]" + ChatColor.WHITE + " -> [ALL]");
                            }
                        }
                    }
                } else if (args[0].equalsIgnoreCase("out") && GameManager.isStart) {
                    Bukkit.broadcastMessage(ChatColor.GOLD + "[Heart_of_Fortress]" + ChatColor.RED + "게임이 진행중입니다!");
                } else if (args[0].equalsIgnoreCase("team")) {
                    if (args[1].equalsIgnoreCase("reset") && !GameManager.isStart) {
                        if (player.isOp()) {
                            for(Player target : (Player[]) Bukkit.getOfflinePlayers()){
                                if(PlayerData.chatType.containsKey(target.getUniqueId())){
                                    PlayerData.chatType.replace(target.getUniqueId(),ChatType.all);
                                }
                            }
                            Bukkit.broadcastMessage(ChatColor.GOLD + "[요새의 심장]" + ChatColor.WHITE + "팀 설정이 초기화 되었습니다.");
                            Bukkit.broadcastMessage(ChatColor.GOLD + "[TeamChat] : " + ChatColor.RED + "팀 설정 변경으로 인해 채팅 채널이 [ALL]으로 변경되었습니다.");
                        } else {
                            player.sendMessage(ChatColor.GOLD + "[요새의 심장]" + ChatColor.RED + "관리자가 아닙니다!");
                        }
                    } else if (args[1].equalsIgnoreCase("clear") && GameManager.isStart) {
                        if (player.isOp()) {
                            player.sendMessage(ChatColor.GOLD + "[요새의 심장]" + ChatColor.RED + "게임이 이미 진행 중입니다!");
                        } else {
                            player.sendMessage(ChatColor.GOLD + "[요새의 심장]" + ChatColor.RED + "관리자가 아닙니다!");
                        }
                    } else if (args[1].equalsIgnoreCase("random") && !GameManager.isStart) {
                        TeamSystem.setTeam();
                    }
                } else if (args[0].equalsIgnoreCase("upgrade")) {
                    UpgradeAbility.openUpgradeInv(player);
                } else if (args[0].equalsIgnoreCase("give")) {
                    if (player.isOp()) {
                        if (args.length >= 3) {
                            String name = args[1];
                            int money = Integer.parseInt(args[2]);
                            Player target = Bukkit.getPlayer(name);
                            if (target == null) {
                                player.sendMessage(ChatColor.GOLD + "[요새의 심장]" + ChatColor.RED + "플레이어[" + args[1] + "]가 존재하지 않습니다!");
                            }else{
                                target.setLevel(target.getLevel() + money);
                                target.sendMessage(ChatColor.GOLD + "[요새의 심장]" + ChatColor.YELLOW + "관리자가 당신에게 " + ChatColor.GREEN + args[2] + " Point" + ChatColor.YELLOW + "를 지급했습니다.");
                            }
                        } else {
                            player.sendMessage(ChatColor.GOLD + "[요새의 심장]" + ChatColor.RED + "[플레이어]나 [수량]을 제대로 입력하지 않았습니다!");
                            player.sendMessage(ChatColor.YELLOW + "    ▶" + ChatColor.GREEN + "/h give [플레이어] [금액]");
                        }
                    } else {
                        player.sendMessage(ChatColor.GOLD + "[요새의 심장]" + ChatColor.RED + "관리자가 아닙니다!");
                    }
                } else if (args[0].equalsIgnoreCase("money")) {
                    if (args.length == 3) {
                        if (args[1] != null && args[2] != null) {
                            int money = Integer.parseInt(args[1]);
                            int amount = Integer.parseInt(args[2]);
                            MoneySystem.withdrawMoney(player, money, amount);
                        } else {
                            player.sendMessage(ChatColor.GOLD + "[요새의 심장]" + ChatColor.RED + "명령어의 인자를 다시 확인해주세요!");
                        }
                    } else {
                        player.sendMessage(ChatColor.GOLD + "[요새의 심장]" + ChatColor.RED + "[10/100/1000] 또는 [수량]을 입력하지 않으셨습니다!");
                        player.sendMessage(ChatColor.YELLOW + "    ▶" + ChatColor.GREEN + "/h money [10/100/1000] [수량]");
                    }
                } else if (args[0].equalsIgnoreCase("help")) {
                    Help.sendHelp(player);
                } else if (args[0].equalsIgnoreCase("rule")) {
                    Help.sendRule(player);
                } else if (args[0].equalsIgnoreCase("setting") && args[1].equalsIgnoreCase("reset")) {
                    if (player.isOp()) {
                        Bukkit.broadcastMessage(ChatColor.GOLD + "[요새의 심장]" + ChatColor.YELLOW + "config 파일이 초기화되었습니다. (서버 재시작 또는 리로드 후 적용)");
                    } else {
                        player.sendMessage(ChatColor.GOLD + "[요새의 심장]" + ChatColor.RED + "관리자가 아닙니다!");
                    }
                } else if (args[0].equalsIgnoreCase("test")) {
                    player.sendMessage("" + PlayerData.scores + "");
                } else {
                    player.sendMessage(ChatColor.GOLD + "[요새의 심장]" + ChatColor.RED + "명령어가 정확하지 않습니다! /h help 를 통해 명령어를 확인하세요.");
                }
            }
        }
        return false;
    }
}
