package json;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class JsonObject extends Json {
    Map<String, Json> obj = new HashMap<>();

    public JsonObject(JsonPair... jsonPairs) {
        for (JsonPair each: jsonPairs) {
            obj.put(each.key, each.value);
        }
    }

    @Override
    public String toJson() {
        String res = "{";
        for(Map.Entry<String, Json> entry: obj.entrySet()) {
            if (!res.equals("{")) {
                res += ", ";
            }
            res += "'" + entry.getKey()+ "': " + entry.getValue().toJson();
        }
        res += "}";
        return res;
    }

    public void add(JsonPair jsonPair) {
        obj.put(jsonPair.key, jsonPair.value);
    }

    public Json find(String name) {
        return obj.get(name);
    }

    public JsonObject projection(String... names) {
        JsonObject new_obj = new JsonObject();

        for (String name: names) {
            if (this.find(name) != null) {
                new_obj.add(new JsonPair(name, this.find(name)));
            }
        }
        return new_obj;
    }
}