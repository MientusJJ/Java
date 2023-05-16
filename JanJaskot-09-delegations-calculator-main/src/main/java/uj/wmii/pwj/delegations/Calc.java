package uj.wmii.pwj.delegations;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.*;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.MINUTES;

public class Calc {

    static SimpleDateFormat m_formatSimple= new SimpleDateFormat("yyyy-MM-dd HH:mm");
    static DateTimeFormatter m_formatDat= DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    static BigDecimal m_renumeration=new BigDecimal(0);
    BigDecimal calculate(String name, String start, String end, BigDecimal dailyRate) throws IllegalArgumentException
    {
        ZonedDateTime startData = makeOneData(start);
        ZonedDateTime endData = makeOneData(end);
        m_renumeration=dailyRate;
        long l_days=DAYS.between(startData,endData);
        long l_minutesModulo=MINUTES.between(startData,endData) %(60*24);
        if(l_minutesModulo<0.0)
        {
            return new BigDecimal(0.00);
        }
        else
        {
            return m_renumeration.multiply(new BigDecimal(l_days)).add(checkMinutes(l_minutesModulo));
        }


    }
    private static BigDecimal checkMinutes(long p_minutes)
    {
        if(p_minutes==0)
        {
            return new BigDecimal(0.00);
        }
        long p_hours= p_minutes/60;
        if(p_hours<=8)
        {
           return m_renumeration.divide(new BigDecimal(3),2, RoundingMode.HALF_UP);
        }
        else if(p_hours<=12)
        {
            return m_renumeration.divide(new BigDecimal(2),2, RoundingMode.HALF_EVEN);
        }
        else
        {
            return m_renumeration.divide(new BigDecimal(1),2, RoundingMode.HALF_EVEN);
        }
    }
    private static ZonedDateTime makeOneData(String p_str)
    {
        String[] l_tab=p_str.split(" ");
        String time= l_tab[0] + " " + l_tab[1];
        return LocalDateTime.parse(time, m_formatDat).atZone(ZoneId.of(l_tab[2]));
    }



}
