package system;

import data.MapData;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Help {
    public static void sendHelp(Player player) {
        String msg = "";
        msg = msg + ChatColor.GREEN + "=-=-=-=-=-=-=-=-=-=" + ChatColor.GOLD + "[Commands]" + ChatColor.GREEN + "=-=-=-=-=-=-=-=-=-=\n";
        msg = msg + ChatColor.DARK_AQUA + "▶  " + ChatColor.YELLOW + "[노랑색]" + ChatColor.WHITE + "은 일반, " + ChatColor.RED + "[빨간색]" + ChatColor.WHITE + "은 OP 전용 명령어\n";
        msg = msg + ChatColor.YELLOW + "/h cc" + ChatColor.WHITE + " : 채팅 채널을 변경합니다.\n";
        msg = msg + ChatColor.YELLOW + "/h rule" + ChatColor.WHITE + " : 게임 규칙을 확인합니다.\n";
        msg = msg + ChatColor.YELLOW + "/h join [red/blue]" + ChatColor.WHITE + " : [red/blue]팀에 참가합니다.\n";
        msg = msg + ChatColor.YELLOW + "/h out" + ChatColor.WHITE + " : 현재 참가되어있는 팀을 탈퇴합니다.\n";
        msg = msg + ChatColor.YELLOW + "/h money [10/100/1000] [수량]" + ChatColor.WHITE + " : [10/100/1000] 수표를 [수량]만큼 발행합니다.\n";
        msg = msg + ChatColor.RED + "/h team reset" + ChatColor.WHITE + " : 팀 설정을 초기화 합니다.\n";
        msg = msg + ChatColor.RED + "/h team random" + ChatColor.WHITE + " : 팀을 랜덤으로 뽑습니다.\n";
        msg = msg + ChatColor.RED + "/h start" + ChatColor.WHITE + " : 게임을 시작합니다.\n";
        msg = msg + ChatColor.RED + "/h stop" + ChatColor.WHITE + " : 게임을 중단합니다.\n";
        msg = msg + ChatColor.RED + "/h give [플레이어] [금액]" + ChatColor.WHITE + " : [플레이어]에게 [금액]만큼의 포인트를 지급합니다.\n";
        msg = msg + ChatColor.RED + "/h setting reset" + ChatColor.WHITE + " : [config.yml]에 저장된 설정을 초기화 합니다. 변경된 설정은 서버를 재시작 해야 적용됩니다.\n";
        player.sendMessage(msg);
    }

    public static void sendRule(Player player) {
        String tm = "" + MapData.towerTime / 60 + "";
        String ts = "" + MapData.towerTime % 60 + "";
        if (MapData.towerTime % 60 < 10) {
            ts = "0" + MapData.towerTime % 60 + "";
        }

        String am = "" + MapData.appleTime / 60 + "";
        String as = "" + MapData.appleTime % 60 + "";
        if (MapData.appleTime % 60 < 10) {
            as = "0" + MapData.towerTime % 60 + "";
        }

        String gm = "" + MapData.buffTime / 60 + "";
        String gs = "" + MapData.buffTime % 60 + "";
        if (MapData.buffTime % 60 < 10) {
            gs = "0" + MapData.buffTime % 60 + "";
        }

        String msg1 = "" + ChatColor.GREEN + "=-=-=-=-=-=-=-=-=-=-=" + ChatColor.GOLD + "[Rule]" + ChatColor.GREEN + "=-=-=-=-=-=-=-=-=-=-=";
        String msg2 = "" + ChatColor.WHITE + "◎상대팀의 포탑[철블럭]을 모두 파괴한 후 상대의 요새에 있는 심장[다이아몬드블럭]을 파괴하면 승리합니다.";
        String msg3 = "" + ChatColor.WHITE + "◎직업은 [전사/궁수/암살자/광부] 가 있으며, 모두 4차까지 전직할 수 있습니다.";
        String msg4 = "" + ChatColor.WHITE + "◎포탑은 활성화 되어야 파괴할 수 있으며, 게임 시작 후 " + tm + "분" + ts + "초가 지나면 활성화 됩니다.";
        String msg5 = "" + ChatColor.WHITE + "◎맵 곳곳에 있는 사과나무의 사과[레드스톤블럭]을 우클릭 하면 사과를 얻을 수 있으며, 사과를 우클릭하면 [재생/독/100POint]를 랜덤으로 얻습니다.";
        String msg6 = "" + ChatColor.WHITE + "◎맵 중간의 다리에 위치한 금블럭을 우클릭하면 [힘/신속/저항] 효과를 랜덤으로 얻습니다.";
        String msg7 = "" + ChatColor.WHITE + "◎사과는 " + am + "분" + as + "초, 금블럭은 " + gm + "분" + gs + "초 마다 리젠됩니다.";
        String msg = "" + msg1 + "\n" + msg2 + "\n" + msg3 + "\n" + msg4 + "\n" + msg5 + "\n" + msg6 + "\n" + msg7 + "";
        player.sendMessage(msg);
    }
}
