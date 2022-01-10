package json;

/**
 * Created by Andrii_Rodionov on 1/4/2017.
 */
public class JsonBoolean extends Json {
    String state;

    public JsonBoolean(Boolean bool) {
        state = bool.toString();
    }

    @Override
    public String toJson() {
        // ToDo
        return state;
    }
}