package org.example;

import java.util.HashMap;
import java.util.Random;

public class ParadoxMH {

    private int staticTrueOneStep = 0; //кол-во положительных результатов при отказе о смене
    private int staticFalseOneStep = 0; //кол-во негативных результатов при отказе о смене
    private int staticTrueTwoStep = 0; //кол-во положительных результатов при согласии о смене
    private int staticFalseTwoStep = 0; //кол-во положительных результатов при согласии о смене
    private Random random = new Random();
    private HashMap<Integer, Boolean> map;
    private final Integer[] list = {1, 0, 0};

    public ParadoxMH() {
        map = new HashMap<>();
    }

    public void OneStep(boolean diffTwoStep, Integer kay) {
        boolean res = false;
        int r1 = random.nextInt(3); //первичный выбор двери
        //System.out.println(r1);
        if (diffTwoStep == false) {
            if (list[r1] == 1) {
                res = true;
                staticTrueOneStep++;
            }
            else {
                res = false;
                staticFalseOneStep++;
            }
        } else {
            for (int i = 0; i < 3; i++) {
                if (i == r1) {staticFalseTwoStep++;break;}
                if (list[i] == 1) {
                    res = true;
                    staticTrueTwoStep++;
                    break;
                } else {res = false; staticFalseTwoStep++;}
            }
        }
        map.put(kay, res);
    }

    public int getStaticTrueOneStep() {
        return staticTrueOneStep;
    }

    public int getStaticFalseOneStep() {
        return staticFalseOneStep;
    }

    public int getStaticTrueTwoStep() {
        return staticTrueTwoStep;
    }

    public int getStaticFalseTwoStep() {
        return staticFalseTwoStep;
    }

    public HashMap<Integer, Boolean> getMap() {
        return map;
    }
}
