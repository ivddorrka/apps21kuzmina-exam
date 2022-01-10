package domain;
import json.*;

import java.util.ArrayList;
import java.util.Arrays;

public class Student extends BasicStudent{

    private final ArrayList<Tuple<String, Integer>> examResults = new ArrayList<Tuple<String, Integer>>();

    @SafeVarargs
    public Student(String name, String surname, Integer year, Tuple<String, Integer>... examResults) {
        super(name, surname, year);

        this.examResults.addAll(Arrays.asList(examResults));
    }

    @Override
    public JsonObject toJsonObject() {

        JsonObject newStudent = new JsonObject();
        Json[] examResultsHere = new Json[examResults.size()];
        int num = 0;

        for (Tuple<String, Integer> part : examResults){
            JsonObject ex = new JsonObject(new JsonPair("course", new JsonString(part.key)), new JsonPair("mark", new JsonNumber(part.value)));
            if (part.value >= 3){
                ex.add(new JsonPair("passed", new JsonBoolean(true)));
            } else {
                ex.add(new JsonPair("passed", new JsonBoolean(false)));
            }
            examResultsHere[num] = ex;
            num++;
        }

        newStudent.add(new JsonPair("name", new JsonString(this.name)));
        newStudent.add(new JsonPair("surname", new JsonString(this.surname)));
        newStudent.add(new JsonPair("year", new JsonNumber(this.year)));
        newStudent.add(new JsonPair("exams", new JsonArray(examResultsHere)));

        return newStudent;
    }
}
