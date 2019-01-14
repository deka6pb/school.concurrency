package concurrent;

import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentMap1 {

    public static void main(String[] args) {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.putIfAbsent("foo", "bar");
        map.putIfAbsent("han", "solo");
        map.putIfAbsent("r2", "d2");
        map.putIfAbsent("c3", "p0");
        String fooValue = map.putIfAbsent("foo", "TEST");

        System.out.println(fooValue);
        System.out.println(map);


        String hiValue = map.getOrDefault("hi", "there");
        System.out.println(hiValue);    // there


        map.replaceAll((key, value) -> "r2".equals(key) ? "d3" : value);
        System.out.println(map.get("r2"));    // d3


        map.compute("foo", (key, value) -> value + value);
        System.out.println(map.get("foo"));   // barbar


        map.merge("foo", "boo", (oldVal, newVal) -> newVal + " was " + oldVal);
        System.out.println(map.get("foo"));   // boo was bar
    }
}
