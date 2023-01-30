package seedu.duke;

import java.util.ArrayList;

public class Ui {

    private static boolean receivedBye = false;

    /**
     * Prints out a response, wrapped with 2 hyphenated lines above and below
     * @param response
     */
    public static void print(String response) {
        System.out.println("----------------------------------");
        System.out.println(response.trim());
        System.out.println("----------------------------------");
    }

    public static void showError(Exception e) {
        System.out.println(e);
    }

    public static void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    public static void showBye() {
        System.out.println("----------------------------------");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("----------------------------------");
    }

    /**
     * Responds to the user by printing a response, based on the chat sent by user
     * @param chat User input
     * @param ls The TaskList in Duke
     */
    public static void respond(String chat, TaskList ls) {
        String keyword = chat.split(" ")[0];
        switch(keyword) {
        case("list"): {
            String response = ls.toString();
            Ui.print(response);
            break;
        }
        case("mark"): {
            Task task = ls.mark(chat.substring(5));
            String response = "Nice! I've marked this task as done:\n";
            response += task.toString();
            Ui.print(response);
            break;
        }
        case("unmark"): {
            Task task = ls.unmark(chat.substring(7));
            String response = "OK, I've marked this task as not done yet:\n";
            response += task.toString();
            Ui.print(response);
            break;
        }
        case("todo"): {
            try {
                Task task = ls.addToDo(chat);
                String response = "Got it. I've added this task:\n";
                response += task.toString() + "\n";
                response += "Now you have " + ls.getSize() + " tasks in the list.";
                Ui.print(response);
            } catch (DukeException e) {
                Ui.print(e.toString());
            }
            break;
        }
        case("deadline"): {
            try {
                Task task = ls.addDeadline(chat);
                String response = "Got it. I've added this task:\n";
                response += task.toString() + "\n";
                response += "Now you have " + ls.getSize() + " tasks in the list.";
                Ui.print(response);
            } catch (DukeException e) {
                Ui.print(e.toString());
            }
            break;
        }
        case("event"): {
            try {
                Task task = ls.addEvent(chat);
                String response = "Got it. I've added this task:\n";
                response += task.toString() + "\n";
                response += "Now you have " + ls.getSize() + " tasks in the list.";
                Ui.print(response);
            } catch (DukeException e) {
                Ui.print(e.toString());
            }
            break;
        }
        case("delete"): {
            Task task = ls.delete(chat.substring(7));
            String response = "Noted. I've removed this task:\n";
            response += task.toString() + "\n";
            response += "Now you have " + ls.getSize() + " tasks in the list.";
            Ui.print(response);
            break;
        }
        case("bye"): {
            receivedBye = true;
            break;
        }
        case("find"): {
            String search = chat.split(" ")[1];
            ArrayList<Task> list = new ArrayList<>();
            for (Task task : ls.getList()) {
                if (task.contains(search)) {
                    list.add(task);
                }
            }
            TaskList filteredList = new TaskList(list);
            String response = "Here are the matching tasks in your list:\n";
            response += filteredList.toString();
            Ui.print(response);
            break;
        }
        default:
            Ui.print("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public static boolean receivedBye() {
        return receivedBye;
    }
}
