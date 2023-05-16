package uj.wmii.pwj.collections;

import java.util.*;

public class ListMerger {
    public  static List<Object> mergeLists(List<?> l1, List<?> l2) {
        if (l1 == null && l2 == null) {
            return new ArrayList<>();
        }
        List<Object> ret = new ArrayList<Object>();
        if (l1==null)
        {
            for (var p:l2)
            {
                ret.add(p);
            }
        }
        else if(l2==null)
        {
            for (var p:l1)
            {
                ret.add(p);
            }
        }
        else{
            int range = Math.min(l1.size(), l2.size());

            for (int i = 0; i < range; i++) {
                ret.add(l1.get(i));
                ret.add(l2.get(i));
            }
            int p = range;
            while (p < l1.size()) {
                ret.add(l1.get(p));
                p++;
            }
            p = range;
            while (p < l2.size()) {
                ret.add(l2.get(p));
                p++;
            }
        }
        return Collections.unmodifiableList(ret);
    }

}
