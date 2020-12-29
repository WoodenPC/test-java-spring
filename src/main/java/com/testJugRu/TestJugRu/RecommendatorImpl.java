package com.testJugRu.TestJugRu;

public class RecommendatorImpl implements Recommendator {
    @InjectProperty("wisky")
    private String alcohol;

    @Override
    public void recommend() {
        System.out.println("to protect from covid drink " + alcohol);
    }
}
