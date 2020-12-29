package com.testJugRu.TestJugRu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map map = new HashMap<Class, Class>();
        map.put(Policeman.class, PolicemanImpl.class);
        ApplicationContext context = Application.run("com.testJugRu", map);
        CoronaDesinfector desinfector = context.getObject(CoronaDesinfector.class);
        desinfector.start(new Room());
    }
}
