package seedu.duke;

import java.util.ArrayList;
import java.util.HashMap;

public class TaskList {

    public ArrayList<Task> list = new ArrayList<>();
    public ArrayList<Task> getList() {
        return list;
    }

    /**
     * Using the chat of the user, creates a new ToDo object and adds it to the list
     * @param chat (String inputted by user)
     * @return newToDo
     * @throws Exception (Task description cannot be empty)
     */
    public ToDo addToDo(String chat) throws DukeException {
        HashMap<String, String> parsed = Parser.parseTodo(chat);
        ToDo newToDo = new ToDo(parsed);
        list.add(newToDo);
        return newToDo;
    }

    /**
     * Using the chat of the user, creates a new Deadline object and adds it to the list
     * @param chat (String inputted by user)
     * @return newDeadline
     * @throws Exception (Task description cannot be empty)
     */
    public Deadline addDeadline(String chat) throws DukeException {
        HashMap<String, String> parsed = Parser.parseDeadline(chat);
        Deadline newDeadline = new Deadline(parsed);
        list.add(newDeadline);
        return newDeadline;
    }

    /**
     * Using the chat of the user, creates a new Event object and adds it to the list
     * @param chat (String inputted by user)
     * @return newEvent
     * @throws Exception (Task description cannot be empty)
     */
    public Event addEvent(String chat) throws DukeException {
        HashMap<String, String> parsed = Parser.parseEvent(chat);
        Event newEvent = new Event(parsed);
        list.add(newEvent);
        return newEvent;
    }

    public int getSize() {
        return list.size();
    }

    /**
     * Marks the task corresponding to taskNumberString as complete
     * @param taskNumberString
     * @return marked task
     */
    public Task mark(String taskNumberString) {
        Integer taskNumber = Integer.valueOf(taskNumberString) - 1;
        Task task = list.get(taskNumber);
        task.mark();
        return task;
    }

    /**
     * Marks the task corresponding to taskNumberString as not complete
     * @param taskNumberString
     * @return unmarked task
     */
    public Task unmark(String taskNumberString) {
        Integer taskNumber = Integer.valueOf(taskNumberString) - 1;
        Task task = list.get(taskNumber);
        task.unmark();
        return task;
    }

    /**
     * Deletes the task corresponding to taskNumberString
     * @param taskNumberString
     * @return deleted task
     */
    public Task delete(String taskNumberString) {
        int taskNumber = Integer.valueOf(taskNumberString) - 1;
        Task task = list.remove(taskNumber);
        return task;
    }
}