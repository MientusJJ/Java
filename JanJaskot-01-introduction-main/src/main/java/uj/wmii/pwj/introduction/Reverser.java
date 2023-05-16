package uj.wmii.pwj.introduction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Reverser {

    public String reverse(String input) {
       if(input==null)
       {
           return null;
       }
       String ret=input.trim();
       StringBuilder sb= new StringBuilder(ret);
       ret=sb.reverse().toString();
       return ret;
    }

    public  String reverseWords(String input) {
        if(input==null)
        {
            return null;
        }
        String ret=input.trim();
        List<String> list= new ArrayList<String>();
        int l=0;
        int sz=ret.length()-1;
        for(int r=0;r<=sz;r++)
        {
            if(r==sz)
            {
                list.add(ret.substring(l,r+1));
            }
            else if(ret.charAt(r)==' ')
            {
                list.add(ret.substring(l,r));
                l=r+1;
            }
        }
        Collections.reverse(list);
        ret="";
        for(String s:list)
        {
            ret+=(s+" ");
        }
        ret=ret.trim();
        return ret;
    }

}
