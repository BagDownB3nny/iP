package Duke;

import java.util.HashMap;

public class Event extends Task {

    private String from;
    private String to;

    public Event(HashMap<String, String> parsed) throws DukeException {
        super(parsed.get("event"));
        from = parsed.get("/from");
        to = parsed.get("/to");
        abbreviation = 'E';
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + from + " to: " + to + ")";
    }

    public String getBreakdown() {
        return "event " + task + " /from " + from + " /to " + to;
    }
}
