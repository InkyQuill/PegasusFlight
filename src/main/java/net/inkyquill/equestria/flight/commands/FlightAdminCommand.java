package net.inkyquill.equestria.flight.commands;

import net.inkyquill.equestria.flight.math.Region;
import net.inkyquill.equestria.flight.properties.Settings;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class FlightAdminCommand implements CommandExecutor {
    static String PIntro = ChatColor.GOLD + "[Flight/Admin] ";

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if (commandSender instanceof ConsoleCommandSender) {
            commandSender.sendMessage(PIntro + ChatColor.RED + "Глупая консоль, ты не можешь использовать эту команду!");
            return true;
        }
        Player p = (Player) commandSender;
        if (!p.hasPermission(Settings.FlightEnabled)) {
            p.sendMessage(PIntro + ChatColor.RED + "Вы не можете использовать админские команды.");
            return true;
        }

        if (args.length == 0 || (args.length > 0 && Objects.equals(args[0].toLowerCase(), "help"))) {
            showHelp(p);
        } else if (args[0].toLowerCase().equals("p1")) {
            Region r = Settings.getTemp(p);
            r.Pos1 = p.getLocation();
            p.sendMessage(PIntro + ChatColor.GREEN + "Первая точка региона установлена.");
            checkregion(p, r);
        } else if (args[0].toLowerCase().equals("p2")) {
            Region r = Settings.getTemp(p);
            r.Pos2 = p.getLocation();
            p.sendMessage(PIntro + ChatColor.GREEN + "Вторая точка региона установлена.");
            checkregion(p, r);
        } else if (args[0].toLowerCase().equals("save")) {
            Region r = Settings.getTemp(p);
            if (!r.isCorrect()) {
                p.sendMessage(PIntro + ChatColor.RED + "Регион неполный. Установлены частичные координаты или они находятся в разных мирах.");
                return true;
            }
            if (args.length < 2) {
                p.sendMessage(PIntro + ChatColor.RED + "Не указано имя региона.");
                return true;
            }
            if (Settings.Regions.containsKey(args[1].toLowerCase())) {
                p.sendMessage(PIntro + ChatColor.RED + "Уже есть регион под этим именем. Используйте другое или удалите старый.");
                return true;
            }
            Settings.Regions.put(args[1].toLowerCase(), r);
            p.sendMessage(PIntro + ChatColor.GREEN + "Регион успешно сохранён как " + args[1].toLowerCase());
        } else if (args[0].toLowerCase().equals("delete")) {
            if (args.length < 2) {
                p.sendMessage(PIntro + ChatColor.RED + "Не указано имя региона.");
                return true;
            }
            if (Settings.Regions.containsKey(args[1].toLowerCase())) {
                Settings.Regions.remove(args[1].toLowerCase());
                p.sendMessage(PIntro + ChatColor.GREEN + "Регион успешно удалён.");
                return true;
            }
            p.sendMessage(PIntro + ChatColor.YELLOW + "Регион не существует. Удаление не требуется");
        } else if (args[0].toLowerCase().equals("list")) {
            p.sendMessage(PIntro + ChatColor.WHITE + "Список регионов:");
            for (String st : Settings.Regions.keySet()) {
                p.sendMessage(PIntro + ChatColor.WHITE + st + ": " + Settings.Regions.get(st).toReadable());
            }
        }
        return false;
    }

    private void showHelp(Player p) {
        p.sendMessage(PIntro + ChatColor.WHITE + "Использование:");
        p.sendMessage(PIntro + ChatColor.AQUA + "/fa p1" + ChatColor.WHITE + ", " + ChatColor.AQUA + "/fa p2 " + ChatColor.WHITE + "- устанавливает позиции для региона.");
        p.sendMessage(PIntro + ChatColor.AQUA + "/fa save регион" + ChatColor.WHITE + " - сохраняет регион в словарь.");
        p.sendMessage(PIntro + ChatColor.AQUA + "/fa delete регион" + ChatColor.WHITE + " - удаляет регион из словаря.");
        p.sendMessage(PIntro + ChatColor.AQUA + "/fa list" + ChatColor.WHITE + " - показывает словарь регионов.");
    }

    private void checkregion(Player p, Region r) {
        if (r.isCorrect()) {
            p.sendMessage(PIntro + ChatColor.GREEN + "Регион установлен верно: " +
                    r.Pos1.getBlockX() + ":" + r.Pos1.getBlockY() + ":" + r.Pos1.getBlockZ() +
                    " - " +
                    r.Pos2.getBlockX() + ":" + r.Pos2.getBlockY() + ":" + r.Pos2.getBlockZ());
        } else {
            p.sendMessage(PIntro + ChatColor.RED + "Регион неполный. Установлены частичные координаты или они находятся в разных мирах.");
        }
    }
}
