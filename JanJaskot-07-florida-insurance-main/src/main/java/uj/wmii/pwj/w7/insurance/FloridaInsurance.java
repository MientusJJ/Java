package uj.wmii.pwj.w7.insurance;

import javax.imageio.IIOException;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class FloridaInsurance
{
    public static void main(String[] args)
    {
        Set<String> l_countries= new HashSet<String>();
        ArrayList<OneInsurance> l_list= new ArrayList<OneInsurance>();
        try
        {
            ZipFile l_zip= new ZipFile("FL_insurance.csv.zip");
            ZipEntry l_zipEntry= l_zip.getEntry("FL_insurance.csv");
            InputStream inputStream = l_zip.getInputStream(l_zipEntry);
            BufferedReader bufferReader = new BufferedReader(new InputStreamReader(inputStream));
            String l_oneLine;
            bufferReader.readLine();
            while((l_oneLine=bufferReader.readLine())!=null)
            {
                String[] tab=l_oneLine.split(",");
                OneInsurance l_OI=new OneInsurance(tab[2],Double.parseDouble(tab[7]), Double.parseDouble(tab[8]));
                l_list.add(l_OI);
                l_countries.add(tab[2]);
            }
            FileWriter l_writer = new FileWriter("count.txt");
            l_writer.write(Integer.toString(l_countries.size()));
            l_writer.close();
            l_writer=new FileWriter("tiv2012.txt");
            double l_sum=l_list.stream().mapToDouble(OneInsurance::getM_TIV12).sum();;
            l_writer.write(String.format( "%.2f", l_sum));
            l_writer.close();
            l_writer=new FileWriter("most_valuable.txt");
            var l_most_valuable=  l_list.stream().collect(Collectors.groupingBy(OneInsurance::getM_country,Collectors.summingDouble(OneInsurance::Diff))).entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).limit(10);
            l_writer.write("country,value");
            l_writer.write(System.getProperty("line.separator"));
            FileWriter finalL_writer = l_writer;
            l_most_valuable.forEach(mv->
            {
                try
                {
                    finalL_writer.write(mv.getKey() + "," + String.format("%.2f", mv.getValue()));
                    finalL_writer.write(System.getProperty("line.separator"));
                }
                catch (Exception e)
                {}
            });
            l_writer.close();
            finalL_writer.close();
        }
        catch (IOException e)
        {}
    }
}
