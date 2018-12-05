

package rs.hydra.androidtv.bluetoothlegatt;
import java.util.HashMap;
import java.util.UUID;

/**
 * This class includes a small subset of standard GATT attributes for demonstration purposes.
 */
public class SampleGattAttributes {
    private static HashMap<String, String> attributes = new HashMap();
    public static UUID USER_SERVICE_UUID = UUID.fromString("00001805-0000-1000-8000-00805f9b34fb");

    public static UUID ANSWER_CLIENT_CHARACTERISTIC = UUID.fromString("00002a37-0000-1000-8000-00805f9b34fb");
    public static UUID QUESTION_TYPE_CHARACTERISTIC = UUID.fromString("00002902-0000-1000-8000-00805f9b34fb");
    public static UUID QUESTION_CHARACTERISTIC = UUID.fromString("00002907-0000-1000-8000-00805f9b34fb");

    public static UUID ANSWER_CLIENT_DESCRIPTOR = UUID.fromString("00002a52-0000-1000-8000-00805f9b34fb");
    static {
        // Services.
        attributes.put(USER_SERVICE_UUID.toString(), "Users");
        // Characteristics.
        attributes.put(ANSWER_CLIENT_CHARACTERISTIC.toString(), "Answer");
        attributes.put(QUESTION_TYPE_CHARACTERISTIC.toString(), "QuestionType");
        attributes.put(QUESTION_CHARACTERISTIC.toString(), "Question");
    }

    public static String lookup(String uuid, String defaultName) {
        String name = attributes.get(uuid);
        return name == null ? defaultName : name;
    }
}
