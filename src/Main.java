import adt.interfaces.*;
import adt.implementations.*;
import utils.*;

public class Main {
    private static void testMap(Map<String, Integer> map) {
        assert map.isEmpty();
        assert map.size() == 0;
        assert !map.has("a");
        assert map.get("a") == null;

        System.out.println(map);

        assert map.insert("a", 1) == null;
        assert !map.isEmpty();
        assert map.size() == 1;
        assert map.has("a");
        assert map.get("a") == 1;

        System.out.println(map);

        assert map.insert("b", 2) == null;
        assert !map.isEmpty();
        assert map.size() == 2;
        assert map.has("a");
        assert map.has("b");
        assert map.get("a") == 1;
        assert map.get("b") == 2;

        System.out.println(map);

        assert map.insert("a", 3) == 1;
        assert !map.isEmpty();
        assert map.size() == 2;
        assert map.has("a");
        assert map.has("b");
        assert map.get("a") == 3;
        assert map.get("b") == 2;

        System.out.println(map);

        assert map.insert("c", 4) == null;
        assert !map.isEmpty();
        assert map.size() == 3;
        assert map.has("a");
        assert map.has("b");
        assert map.has("c");
        assert map.get("a") == 3;
        assert map.get("b") == 2;
        assert map.get("c") == 4;

        System.out.println(map);
    }

    static
    class MyClass implements Registry.Identifiable<String> {
        String myClassID;

        public MyClass(String ID) { myClassID = ID; }

        @Override
        public String key() { return myClassID; }
    }

    public static void main(String[] args) {
//        testMap(new HashMap<>());
//        testMap(new ArrayMap<>());
        var registry = new Registry<String, MyClass>();

        if (registry.find("some ID") == null) System.out.println("There's no element with this ID");
        else registry.register(new MyClass("some ID"));
    }
}