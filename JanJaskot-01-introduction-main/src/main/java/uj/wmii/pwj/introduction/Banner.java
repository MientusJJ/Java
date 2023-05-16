package uj.wmii.pwj.introduction;

import java.util.HashMap;
import java.util.Map;

public class Banner {

    static Map<Character,String[]> letter= new HashMap<Character,String[]>(){
        {
            put('a',new String[]{
                    "   #    ",
                    "  # #   ",
                    " #   #  ",
                    "#     # ",
                    "####### ",
                    "#     # ",
                    "#     # ",
            });
            put('b',new String[]{
                    "######  ",
                    "#     # ",
                    "#     # ",
                    "######  ",
                    "#     # ",
                    "#     # ",
                    "######  ",

            });
            put('c',new String[]{
                    " #####  ",
                    "#     # ",
                    "#       ",
                    "#       ",
                    "#       ",
                    "#     # ",
                    " #####  ",


            });
            put('d',new String[]{

                    "######  ",
                    "#     # ",
                    "#     # ",
                    "#     # ",
                    "#     # ",
                    "#     # ",
                    "######  ",
            });
            put('e',new String[]{

                    "####### ",
                    "#       ",
                    "#       ",
                    "#####   ",
                    "#       ",
                    "#       ",
                    "####### ",
            });
            put('f',new String[]{

                    "####### ",
                    "#       ",
                    "#       ",
                    "#####   ",
                    "#       ",
                    "#       ",
                    "#       ",
            });
            put('g',new String[]{

                    " #####  ",
                    "#     # ",
                    "#       ",
                    "#  #### ",
                    "#     # ",
                    "#     # ",
                    "####### ",
            });
            put('h',new String[]{

                    "#     # ",
                    "#     # ",
                    "#     # ",
                    "####### ",
                    "#     # ",
                    "#     # ",
                    "#     # ",
            });
            put('i',new String[]{

                    "### ",
                    " #  ",
                    " #  ",
                    " #  ",
                    " #  ",
                    " #  ",
                    "### ",
            });
            put('j',new String[]{

                    "      # ",
                    "      # ",
                    "      # ",
                    "      # ",
                    "#     # ",
                    "#     # ",
                    " #####  ",
            });
            put('k',new String[]{

                    "#    # ",
                    "#   #  ",
                    "#  #   ",
                    "###    ",
                    "#  #   ",
                    "#   #  ",
                    "#    # ",
            });
            put('l',new String[]{

                    "#       ",
                    "#       ",
                    "#       ",
                    "#       ",
                    "#       ",
                    "#       ",
                    "####### ",
            });
            put('m',new String[]{

                    "#     # ",
                    "##   ## ",
                    "# # # # ",
                    "#  #  # ",
                    "#     # ",
                    "#     # ",
                    "#     # ",
            });
            put('n',new String[]{

                    "#     # ",
                    "##    # ",
                    "# #   # ",
                    "#  #  # ",
                    "#   # # ",
                    "#    ## ",
                    "#     # ",
            });
            put('o',new String[]{

                    "####### ",
                    "#     # ",
                    "#     # ",
                    "#     # ",
                    "#     # ",
                    "#     # ",
                    "####### ",
            });
            put('p',new String[]{

                    "######  ",
                    "#     # ",
                    "#     # ",
                    "######  ",
                    "#       ",
                    "#       ",
                    "#       ",
            });
            put('q',new String[]{

                    " #####  ",
                    "#     # ",
                    "#     # ",
                    "#     # ",
                    "#   # # ",
                    "#    #  ",
                    " #### # ",
            });
            put('r',new String[]{

                    "######  ",
                    "#     # ",
                    "#     # ",
                    "#####   ",
                    "#   #   ",
                    "#    #  ",
                    "#     # ",
            });
            put('s',new String[]{

                    " #####  ",
                    "#     # ",
                    "#       ",
                    " #####  ",
                    "      # ",
                    "#     # ",
                    " #####  ",
            });
            put('t',new String[]{

                    "####### ",
                    "   #    ",
                    "   #    ",
                    "   #    ",
                    "   #    ",
                    "   #    ",
                    "   #    ",
            });
            put('u',new String[]{

                    "#     # ",
                    "#     # ",
                    "#     # ",
                    "#     # ",
                    "#     # ",
                    "#     # ",
                    " #####  ",
            });
            put('w',new String[]{

                    "#     # ",
                    "#  #  # ",
                    "#  #  # ",
                    "#  #  # ",
                    "#  #  # ",
                    "#  #  # ",
                    " ## ##  ",
            });
            put('v',new String[]{

                    "#     # ",
                    "#     # ",
                    "#     # ",
                    "#     # ",
                    " #   #  ",
                    "  # #   ",
                    "   #    ",
            });
            put('x',new String[]{

                    "#     # ",
                    " #   #  ",
                    "  # #   ",
                    "   #    ",
                    "  # #   ",
                    " #   #  ",
                    "#     # ",
            });
            put('y',new String[]{

                    "#     # ",
                    " #   #  ",
                    "  # #   ",
                    "   #    ",
                    "   #    ",
                    "   #    ",
                    "   #    ",
            });
            put('z',new String[]{

                    "#######",
                    "      #",
                    "      #",
                    "   #   ",
                    "  #    ",
                    " #     ",
                    "#######",
            });
            put(' ',new String[]{

                    " ",
                    " ",
                    " ",
                    " ",
                    " ",
                    " ",
                    " ",
            });

    }};

    public  String[] toBanner(String input) {
        if (input==null)
        {
            return null;
        }
        String outek=input.trim().toLowerCase();
        String[] ret= new String[7];
        for(int i=0;i<7;i++)
        {
            ret[i]="";
        }
        for (int i=0;i<outek.length();i++)
        {
            for (int j=0;j<7;j++)
            {
                String l=letter.get(outek.charAt(i))[j];
                ret[j]+=l;
            }
        }
        return ret;
    }

}
