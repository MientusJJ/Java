package uj.wmii.pwj.spreadsheet;

public class SpreadSheet {


    public static String[][] calculate(String[][] input) {
        if(input==null) {
            return null;
        }
        String[][] ret;
        int sz1=input.length;
        int sz2= input[0].length;
        ret=new String[sz1][sz2];
        String cell;
        for(int i=0;i<sz1;i++) {
            sz2=input[i].length;
            for(int j=0;j<sz2;j++) {
                cell=input[i][j];
                if(cell.charAt(0)=='=' ||cell.charAt(0)=='$') {
                    input[i][j]=String.valueOf(check(cell, input));
                }
                else {
                    input[i][j]=cell;
                }
            }
        }
        return input;
    }

    private static int number(String s,int u,int v) {
        int value=0;
         switch(s) {
            case "ADD" -> value= u + v;
            case "SUB" -> value=u-v;
            case "MOD" -> value=u%v;
            case "MUL" -> value=u*v;
            case "DIV" -> value=u/v;
        };
         return value;
    }
    public static int  check(String cell,String input[][]) {
        int ret = 0;
        try {
            ret = Integer.parseInt(cell);
        } catch (Exception e) {
            Character c = cell.charAt(0);
            if (c == '$') {
                int column, row;
                column = Integer.parseInt(cell.substring(2)) - 1;
                row = cell.charAt(1) - 'A';
                cell = input[column][row];
                ret = check(cell, input);
            } else if (c == '=') {
                String[] cells = cell.substring(5, cell.length() - 1).split(",");
                int u = check(cells[0], input);
                int v = check(cells[1], input);
                String operation = cell.substring(1, 4);
                ret = number(operation, u, v);
            }
        }
        return ret;
    }

}
