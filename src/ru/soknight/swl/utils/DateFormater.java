package ru.soknight.swl.utils;

import java.util.Map;

import ru.soknight.swl.files.Config;

public class DateFormater {
    
    public static String getFormatedTime(long minutes) {
        String formated = "";
        long hours, days, weeks, months, years;
        
        Map<String, String> units = Config.units;
        String separator = Config.separator;
        
        DateConverter dc = new DateConverter(minutes);
        minutes = dc.getMinutes();  hours = dc.getHours();
        days    = dc.getDays();     weeks = dc.getWeeks();
        months  = dc.getMonths();   years = dc.getYears();
        
        // String formating:
        if(years > 0)
            formated = units.get("years").replace("%y%", String.valueOf(years));
        if(months > 0) {
            if(!formated.equals("")) formated += separator;
            formated += units.get("months").replace("%M%", String.valueOf(months)); }
        if(weeks > 0) {
            if(!formated.equals("")) formated += separator;
            formated += units.get("weeks").replace("%w%", String.valueOf(weeks)); }
        if(days > 0) {
            if(!formated.equals("")) formated += separator;
            formated += units.get("days").replace("%d%", String.valueOf(days)); }
        if(hours > 0) {
            if(!formated.equals("")) formated += separator;
            formated += units.get("hours").replace("%H%", String.valueOf(hours)); }
        if(minutes > 0) {
            if(!formated.equals("")) formated += separator;
            formated += units.get("minutes").replace("%m%", String.valueOf(minutes)); }
        
        return formated;
    }
}
