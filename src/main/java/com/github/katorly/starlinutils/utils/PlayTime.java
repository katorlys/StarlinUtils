package com.github.katorly.starlinutils.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.github.katorly.starlinutils.StarlinUtils;
import com.github.katorly.starlinutils.backup.ConfigReader;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class PlayTime {
    public static void initialize(Player p) throws ParseException { //Get player's join time.
        long t = System.currentTimeMillis();
        SimpleDateFormat dnow = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date d1 = dnow.parse(dnow.format(t));
        Long t1 = d1.getTime();
        StarlinUtils.INSTANCE.StartTime.put(p.getPlayer().getUniqueId(), t1);
    }

    public static void settle(Player p) throws ParseException { //Count player's total & monthly play time.
        FileConfiguration timedata = StarlinUtils.timedata.getConfig();
        long t = System.currentTimeMillis();
        SimpleDateFormat d = new SimpleDateFormat("yyyy");
        String year = d.format(t);
        String u = p.getPlayer().getUniqueId().toString();
        Long minutes;
        if (!StarlinUtils.timedata.getConfig().contains(u)) {
            timedata.set(u + ".name", p.getName());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm");
            String timenow = dateFormat.format(t);
            timedata.set(u + ".first-time", timenow);
            timedata.set(u + ".total", 0.0);
            timedata.set(u + ".month-time." + year, "0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0");
            ConfigReader.save(StarlinUtils.timedata);
        } else if (!StarlinUtils.timedata.getConfig().contains(u + ".month-time." + year)) {
            timedata.set(u + ".month-time." + year, "0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0");
            ConfigReader.save(StarlinUtils.timedata);
        } else {
            if (StarlinUtils.INSTANCE.StartTime.containsKey(p.getPlayer().getUniqueId())) {
                Long t1 =StarlinUtils.INSTANCE.StartTime.get(p.getPlayer().getUniqueId());
                SimpleDateFormat dnow = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                Date d2 = dnow.parse(dnow.format(t));
                Long t2 = d2.getTime();
                minutes = (t2 - t1) / (1000 * 60);
                StarlinUtils.INSTANCE.StartTime.remove(p.getPlayer().getUniqueId());
                String ytime = timedata.getString(u + ".month-time." + year);
                String[] mtime = ytime.split(",");
                SimpleDateFormat n = new SimpleDateFormat("M");
                String m = n.format(t);
                int month = Integer.valueOf(m);
                mtime[month - 1] = String.format("%.1f", Double.valueOf(mtime[month - 1]) + minutes / 60.0);
                String newtime = String.join(",", mtime);
                timedata.set(u + ".month-time." + year, newtime);
                Double newtotal = Double.valueOf(String.format("%.1f", timedata.getDouble(u + ".total"))) + minutes / 60.0;
                timedata.set(u + ".total", Double.valueOf(String.format("%.1f", newtotal)));
                ConfigReader.save(StarlinUtils.timedata);
            }
        }
    }
}