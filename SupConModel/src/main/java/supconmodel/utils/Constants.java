package supconmodel.utils;

import java.util.Hashtable;

public class Constants {
    public static final Hashtable<Integer, String> speedTextPair =
            new Hashtable<>(){{
                put(1, "1tick/3s");
                put(2, "1tick/2s");
                put(3, "1tick/s");
                put(4, "5tick/s");
                put(5, "10tick/s");
                put(6, "20tick/s");
                put(7, "60tick/s");
                put(8, "120tick/s");
                put(9, "240tick/s");
            }
            };
    public static final Hashtable<Integer, Integer> speedValuePair =
            new Hashtable<>(){{
                put(1, 3000);
                put(2, 2000);
                put(3, 1000);
                put(4, 200);
                put(5, 100);
                put(6, 50);
                put(7, 16);
                put(8, 8);
                put(9, 4);
            }};

}
