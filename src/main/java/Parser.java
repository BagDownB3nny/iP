import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.function.Function;

public class Parser {

    private static Function<String, HashMap<String, String>> todoParser = createTodoParser();
    private static Function<String, HashMap<String, String>> deadlineParser = createDeadlineParser();
    private static Function<String, HashMap<String, String>> eventParser = createEventParser();

    private static Function<String, HashMap<String, String>> createParser(ArrayList<String> keywords) {
        return (String chat) -> {
            int waitingForKeyword = 0;
            String currentKey = "";
            String currentValue = "";
            HashMap<String, String> parsed = new HashMap<>();
            for (String word : chat.split(" ")) {
                if (waitingForKeyword < keywords.size() && word.equals(keywords.get(waitingForKeyword))) {
                    parsed.put(currentKey, currentValue);
                    currentKey = keywords.get(waitingForKeyword++);
                    currentValue = "";
                } else {
                    currentValue += word + " ";
                }
            }
            parsed.put(currentKey, currentValue);
            return parsed;
        };
    }

    /**
     * Creates a parser that is able to take in a String and parse it into components.
     * The components are defined by the keywords.
     * @param keywords (Defines the components to parse)
     * @return Function<String, HashMap<String, String>>
     */

    /**
     * Creates a parser that converts a user inputted String into a HashMap containing: "ToDo"
     * @return created parser
     */
    private static Function<String, HashMap<String, String>> createTodoParser() {
        ArrayList<String> toParse = new ArrayList<>(Arrays.asList("todo"));
        return createParser(toParse);
    }

    /**
     * Creates a parser that converts a user inputted String into a HashMap containing: "deadline", "/by"
     * @return created parser
     */
    private static Function<String, HashMap<String, String>> createDeadlineParser() {
        ArrayList<String> toParse = new ArrayList<>(Arrays.asList("deadline", "/by"));
        return createParser(toParse);
    }

    /**
     * Creates a parser that converts a user inputted String into a HashMap containing: "event", "/from", "/to"
     * @return created parser
     */
    private static Function<String, HashMap<String, String>> createEventParser() {
        ArrayList<String> toParse = new ArrayList<>(Arrays.asList("event", "/from", "/to"));
        return createParser(toParse);
    }

    public static HashMap<String, String> parseTodo(String chat) {
        return todoParser.apply(chat);
    }

    public static HashMap<String, String> parseDeadline(String chat) {
        return deadlineParser.apply(chat);
    }

    public static HashMap<String, String> parseEvent(String chat) {
        return eventParser.apply(chat);
    }

}
