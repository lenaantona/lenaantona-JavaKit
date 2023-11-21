package org.example;

import org.json.simple.parser.ParseException;
import org.json.JSONException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        int res_true_choice_one; //Процент положительных результатов, при отказе сменить выбор
        int res_false_choice_one; //Процент негативных результатов, при отказе сменить выбор
        int res_true_choice_two; //Процент позитивных результатов, при согласии о смене выбора
        int res_false_choice_two; //Процент негативных результатов, при согласии о смене выбора

        ParadoxMH game = new ParadoxMH();
        boolean diffStep = false; //признак согласия на смену двери
        for (int i = 0; i < 1000; i++) {  //цикл расчитан на половину случаев отказа о смене выбора и половины согласия
            if (500 <= i) diffStep = true;
            //System.out.println(diffStep);
            game.OneStep(diffStep, i);
        }
        String str = game.getMap().toString();
        System.out.println(str);
        res_true_choice_one = (game.getStaticTrueOneStep() * 100) / (game.getStaticTrueOneStep() + game.getStaticFalseOneStep());
        res_false_choice_one = (game.getStaticFalseOneStep() * 100) / (game.getStaticTrueOneStep() + game.getStaticFalseOneStep());
        res_true_choice_two = (game.getStaticTrueTwoStep() * 100) / (game.getStaticTrueTwoStep() + game.getStaticFalseTwoStep());
        res_false_choice_two = (game.getStaticFalseTwoStep() * 100) / (game.getStaticTrueTwoStep() + game.getStaticFalseTwoStep());
        Log logFile = new Log();
        try {
            logFile.reedFileJson("stat.json"); //чтение файла
        } catch (IOException | ParseException ex) {
            throw new RuntimeException(ex);
        }
        logFile.setRes_true_choice_one(res_true_choice_one);
        logFile.setRes_false_choice_one(res_false_choice_one);
        logFile.setRes_true_choice_two(res_true_choice_two);
        logFile.setRes_false_choice_two(res_false_choice_two);
        try {
            logFile.writeTextJson();
        } catch (JSONException ex) {
            throw new RuntimeException(ex);
        }
        try {
            logFile.writeAddFileJson("stat.json");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        } catch (JSONException ex) {
            throw new RuntimeException(ex);

        }
    }
}