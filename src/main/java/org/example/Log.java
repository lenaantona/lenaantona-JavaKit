package org.example;
import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

import java.util.Date;
import java.util.HashMap;
public class Log {
    private int res_true_choice_one;
    private int res_false_choice_one;
    private int res_true_choice_two;
    private int res_false_choice_two;

    private  JSONArray ar = new JSONArray();
    public Log() {
    }

    public void setRes_true_choice_one(int res_true_choice_one) {
        this.res_true_choice_one = res_true_choice_one;
    }

    public void setRes_false_choice_one(int res_false_choice_one) {
        this.res_false_choice_one = res_false_choice_one;
    }

    public void setRes_true_choice_two(int res_true_choice_two) {
        this.res_true_choice_two = res_true_choice_two;
    }

    public void setRes_false_choice_two(int res_false_choice_two) {
        this.res_false_choice_two = res_false_choice_two;
    }

    public void writeTextJson() throws JSONException {
        JSONObject obgJson = new JSONObject();
        obgJson.put("% пол.без измен.", this.res_true_choice_one);
        obgJson.put("% отр.без измен.", this.res_false_choice_one);
        obgJson.put("% пол.с измен.", this.res_true_choice_two);
        obgJson.put("% отр.с измен.", this.res_false_choice_two);
        ar.add(obgJson);
    }
    public void writeFileJson(String namePath) {
        try (FileWriter file = new FileWriter(namePath)) {
            file.write(ar.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void writeAddFileJson(String namePath) throws IOException, ParseException, JSONException { //запись в файл
        if (findFiles(namePath) == true) {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(namePath));
            JSONArray ar1 = (JSONArray) obj;
            for (int i = 0; i < ar1.size(); i++) {
                ar.add(ar1.get(i));
            }
        }
        writeFileJson(namePath);
    }
    public String reedFileJson(String namePath) throws IOException, ParseException { //считывание данных с файла
        if (findFiles(namePath) == true) {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(namePath));
            JSONArray ar1 = (JSONArray) obj;
            String message = null;
            for (int i = 0; i < ar1.size(); i++) {
                HashMap m = (HashMap) ar1.get(i);
                message = message + m.get("% пол.без измен.").toString() + "\n";
                message = message + m.get("% отр.без измен.").toString() + "\n";
                message = message + m.get("% пол.с измен.").toString() + "\n";
                message = message + m.get("% отр.с измен.").toString() + "\n";
            }
            return message;
        }
        return null;
    }

    public boolean findFiles(String namePath) { //проверка наличия файла
        File f = new File(".");
        String[] list = f.list();     //список файлов в текущей папке
        boolean b = false;
        for (String file : list) {      //проверка на совпадение
            if (namePath.equals(file)) {
                b = true;
                break;
            } else b = false;
        }
        return b;
    }
}
