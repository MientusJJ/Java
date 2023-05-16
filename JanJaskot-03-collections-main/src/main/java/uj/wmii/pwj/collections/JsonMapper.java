package uj.wmii.pwj.collections;

import java.util.List;
import java.util.Map;

public interface JsonMapper {

    String toJson(Map<String, ?> map);

    static JsonMapper defaultInstance() {
    return  new MakeJson();
    }

}
class MakeJson implements JsonMapper
{
    public String toJson(Map<String, ?> map)
    {
        String ret="{\n";
        if(map.isEmpty() || map==null)
        {
            return "";
        }
        ret=toJson(map,0,true);
        return ret;
    }
        private String toJson(Map<String, ?> map,int tab,Boolean t)
        {

            if(map.isEmpty() || map==null) {
                return "";
            }
            String toRet="";
            if(t==true)
            {toRet+=tabs(tab);}
            toRet+="{\n";
            int sz=map.size();
            int indx=0;
            for (String key : map.keySet()) {
                indx++;
                toRet+=tabs(tab+1);
                toRet+="\""+key+"\": ";
                if (map.get(key) instanceof Map) {
                    toRet+=toJson((Map<String, ?>) map.get(key),tab+1,false);

                }
                else
                {

                    if(map.get(key) instanceof List)
                    {
                        toRet+="[";
                        toRet+= listaCheck((List<?>) map.get(key),tab+1);

                    }
                    else
                    {
                        if(map.get(key) instanceof String)
                        {
                            toRet+="\""+map.get(key)+"\"";
                        }

                        else
                        {
                            toRet+=String.valueOf(map.get(key));
                        }
                    }
                }
                toRet+=(indx<sz ? ",\n":"\n");
            }
            toRet+=tabs(tab);
            toRet+="}";
            return toRet;
    }
    private String tabs(int num)
    {
        String s="";
        for(int i=0;i<num;i++)
        {
            s+="\t";
        }
        return s;
    }
    private String listaCheck(List<?> e,int num)
    {
        String toRet="";
        for(int i=0;i<e.size();i++)
        {
            if(e.get(i) instanceof Map)
            {
                toRet+="\n"+toJson((Map<String, ?>) e.get(i),num+1,true);
                if(i+1==e.size())
                {
                    toRet+="\n";
                    toRet+=tabs(num);
                }
            }
            else if(e.get(i) instanceof List)
            {
                toRet+=listaCheck((List<?>) e.get(i),num);
            }
            else if(e.get(i) instanceof String)
            {
                toRet+="\""+e.get(i)+"\"";
            }

            else
            {
                toRet+=String.valueOf(e.get(i));
            }
            if(i+1!=e.size())
            {
                toRet+=", ";
            }
        }
        toRet+="]";
        return toRet;
    }

}
