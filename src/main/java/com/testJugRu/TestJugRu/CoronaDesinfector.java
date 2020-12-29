package com.testJugRu.TestJugRu;

@Singleton
public class CoronaDesinfector {
    @InjectByType
    private Announcer announcer;
    @InjectByType
    private Policeman policeman;

    CoronaDesinfector() {

    }

    public void start(Room room) {
        announcer.announce("Начинаем дезинфекцию, все вон");
        policeman.makePeopleLeaveRoom();
        desinfect(room);
        announcer.announce("Рискните зайти обратно.");
    }

    private void desinfect(Room room) {
        System.out.println("Корона изыди");
    }
}
