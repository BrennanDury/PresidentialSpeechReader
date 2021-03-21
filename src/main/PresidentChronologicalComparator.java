package main;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class PresidentChronologicalComparator implements Comparator<President>{
    private static final Map<String, Integer> nameToChronologicalOrder;
    static {
        nameToChronologicalOrder = new HashMap<String, Integer>();
        nameToChronologicalOrder.put("washington", 1);
        nameToChronologicalOrder.put("adams", 2);
        nameToChronologicalOrder.put("jefferson", 3);
        nameToChronologicalOrder.put("madison", 4);
        nameToChronologicalOrder.put("monroe", 5);
        nameToChronologicalOrder.put("jqadams", 6);
        nameToChronologicalOrder.put("jackson", 7);
        nameToChronologicalOrder.put("vanburen", 8);
        nameToChronologicalOrder.put("harrison", 9);
        nameToChronologicalOrder.put("tyler", 10);
        nameToChronologicalOrder.put("polk", 11);
        nameToChronologicalOrder.put("taylor", 12);
        nameToChronologicalOrder.put("fillmore", 13);
        nameToChronologicalOrder.put("pierce", 14);
        nameToChronologicalOrder.put("buchanan", 15);
        nameToChronologicalOrder.put("lincoln", 16);
        nameToChronologicalOrder.put("johnson", 17);
        nameToChronologicalOrder.put("grant", 18);
        nameToChronologicalOrder.put("hayes", 19);
        nameToChronologicalOrder.put("garfield", 20);
        nameToChronologicalOrder.put("arthur", 21);
        nameToChronologicalOrder.put("cleveland", 22); //cleveland is counted as before bharrison but was elected again after bharrison
        nameToChronologicalOrder.put("bharrison", 23);
        nameToChronologicalOrder.put("mckinley", 24);
        nameToChronologicalOrder.put("roosevelt", 25);
        nameToChronologicalOrder.put("taft", 26);
        nameToChronologicalOrder.put("wilson", 27);
        nameToChronologicalOrder.put("harding", 28);
        nameToChronologicalOrder.put("coolidge", 29);
        nameToChronologicalOrder.put("hoover", 30);
        nameToChronologicalOrder.put("fdroosevelt", 31);
        nameToChronologicalOrder.put("truman", 32);
        nameToChronologicalOrder.put("eisenhower", 33);
        nameToChronologicalOrder.put("kennedy", 34);
        nameToChronologicalOrder.put("lbjohnson", 35);
        nameToChronologicalOrder.put("nixon", 36);
        nameToChronologicalOrder.put("ford", 37);
        nameToChronologicalOrder.put("carter", 38);
        nameToChronologicalOrder.put("reagan", 39);
        nameToChronologicalOrder.put("bush", 40);
        nameToChronologicalOrder.put("clinton", 41);
        nameToChronologicalOrder.put("gwbush", 42);
        nameToChronologicalOrder.put("obama", 43);
        nameToChronologicalOrder.put("trump", 44);
        nameToChronologicalOrder.put("hclinton", 45);
        nameToChronologicalOrder.put("Trump2020", 46);
        nameToChronologicalOrder.put("Biden2020", 48);
    };

    @Override
    public int compare(President o1, President o2) {
        assert (nameToChronologicalOrder.containsKey(o1.getName())) : "comparing a president without data on them";
        assert (nameToChronologicalOrder.containsKey(o2.getName())) : "comparing a president without data on them";
        return Integer.compare(nameToChronologicalOrder.get(o1.getName()), nameToChronologicalOrder.get(o2.getName()));
    }
}
