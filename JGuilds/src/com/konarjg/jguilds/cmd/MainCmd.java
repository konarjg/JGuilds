package com.konarjg.jguilds.cmd;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MainCmd implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("g")) {
			if (args.length == 0) {
				send("&a&lPomoc JGuilds: ", sender);
				send("&b/g zaloz <tag> <nazwa>: " + "&6zaklada gildie", sender);
				send("&b/g usun <tag>: " + "&6usuwa gildie", sender);
				send("&b/g info <tag>: " + "&6wyswietla informacje o gildii", sender);
				send("&b/g top <gracze/gildie>: " + "&6wyswietla top 10 graczy/gildii", sender);
				send("&b/g dolacz <tag>: " + "&6dolacz do gildii", sender);
				send("&b/g wojna <tag>: " + "&6wypowiada wojne", sender);
				send("&b/g sojusz <tag>: " + "&6wysyla prosbe o sojusz", sender);
				send("&b/g neutral <tag>: " + "&6wysyla prosbe o neutralnosc", sender);
				send("&b/g akceptujsojusz <tag>: " + "&6akceptuje prosbe o sojusz", sender);
				send("&b/g odrzucsojusz <tag>: " + "&6odrzuca prosbe o sojusz", sender);
				send("&b/g akceptujneutral <tag>: " + "&6akceptuje prosbe o neutralnosc", sender);
				send("&b/g odrzucneutral <tag>: " + "&6odrzuca prosbe o neutralnosc", sender);
				send("&b/g wyrzuc <gracz>: " + "&6wyrzuca gracza z gildii", sender);
				send("&b/g zapros <gracz>: " + "&6zaprasza gracza do gildii", sender);
				send("&b/g odzapros <gracz>: " + "&6usuwa zaproszenie gracza do gildii", sender);
				send("&b/g mod <gracz>: " + "&6awansuje gracza na moderatora", sender);
				send("&b/g unmod <gracz>: " + "&6degraduje gracza z moderatora", sender);
				send("&b/g baza: " + "&6teleportuje gracza do bazy", sender);
				send("&b/g opusc: " + "&6opuszcza gildie", sender);
				send("&b/g itemy: " + "&6wywietla przedmioty potrzebne do utworzenia gildii", sender);
				send("&b/g rank: " + "&6wywietla twoj ranking", sender);
				return true;
			}else {
				if (args[0].equalsIgnoreCase("zaloz") || args[0].equalsIgnoreCase("create")) {
					new CmdZaloz().Zaloz(sender, cmd, label, args);
					return true;
				}else if (args[0].equalsIgnoreCase("usun") || args[0].equalsIgnoreCase("disband")) {
					new CmdUsun().Usun(sender, cmd, label, args);
					return true;
				}else if (args[0].equalsIgnoreCase("info")) {
					new CmdInfo().Info(sender, cmd, label, args);
					return true;
				}
				else if (args[0].equalsIgnoreCase("wyrzuc") || args[0].equalsIgnoreCase("kick")) {
					new CmdWyrzuc().Wyrzuc(sender, cmd, label, args);
					return true;
				}else if (args[0].equalsIgnoreCase("zapros") || args[0].equalsIgnoreCase("invite")) {
					new CmdZapros ().Zapros(sender, cmd, label, args);
					return true;
				}else if (args[0].equalsIgnoreCase("odzapros") || args[0].equalsIgnoreCase("uninvite")) {
					new CmdOdzapros().Odzapros(sender, cmd, label, args);
					return true;
				}else if (args[0].equalsIgnoreCase("dolacz") || args[0].equalsIgnoreCase("join")) {
					new CmdDolacz().Dolacz(sender, cmd, label, args);
					return true;
				}else if (args[0].equalsIgnoreCase("top")) {
					new CmdTop().Top(sender, cmd, label, args);
					return true;
				}else if (args[0].equalsIgnoreCase("sojusz") || args[0].equalsIgnoreCase("ally")) {
					new CmdSojusz().Sojusz(sender, cmd, label, args);
					return true;
				}else if (args[0].equalsIgnoreCase("wojna") || args[0].equalsIgnoreCase("war")) {
					new CmdWojna().Wojna(sender, cmd, label, args);
					return true;
				}else if (args[0].equalsIgnoreCase("neutral") || args[0].equalsIgnoreCase("neutral")) {
					new CmdNeutral().Neutral(sender, cmd, label, args);
					return true;
				}else if (args[0].equalsIgnoreCase("akceptujsojusz") || args[0].equalsIgnoreCase("allyaccept")) {
					new CmdSojuszAkceptuj().SojuszAkceptuj(sender, cmd, label, args);
					return true;
				}else if (args[0].equalsIgnoreCase("odrzucsojusz") || args[0].equalsIgnoreCase("allyreject")) {
					new CmdSojuszOdrzuc().Sojuszodrzuc(sender, cmd, label, args);
					return true;
				}else if (args[0].equalsIgnoreCase("odrzucneutral") || args[0].equalsIgnoreCase("neutralreject")) {
					new CmdNeutralOdrzuc().NeutralOdrzuc(sender, cmd, label, args);
					return true;
				}else if (args[0].equalsIgnoreCase("akceptujneutral") || args[0].equalsIgnoreCase("neutralaccept")) {
					new CmdNeutralAkceptuj().NeutralAkceptuj(sender, cmd, label, args);
					return true;
				}else if (args[0].equalsIgnoreCase("mod")) {
					new CmdModerator().Mod(sender, cmd, label, args);
					return true;
				}else if (args[0].equalsIgnoreCase("unmod")) {
					new CmdOdmoderator().Unmod(sender, cmd, label, args);
					return true;
				}else if (args[0].equalsIgnoreCase("baza") || args[0].equalsIgnoreCase("base")) {
					new CmdBaza().Baza(sender, cmd, label, args);
					return true;
				}else if (args[0].equalsIgnoreCase("opusc") || args[0].equalsIgnoreCase("leave")) {
					new CmdOpusc().Opusc(sender, cmd, label, args);
					return true;
				}else if (args[0].equalsIgnoreCase("itemy") || args[0].equalsIgnoreCase("items")) {
					new CmdItemy ().Itemy(sender, cmd, label, args);
					return true;
				}else if (args[0].equalsIgnoreCase("rank")) {
					new CmdRank().Rank(sender, cmd, label, args);
					return true;
				}
			}
		}
		return false;
	}
	
	void send (String msg, CommandSender sender) {
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
	}

}
