package task_52;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FruitList {

    private static List<String> toDo = new ArrayList<>();

    public static void main(String[] args) {
        toDo.add("one");
        toDo.add("two");

        try (Scanner scanner = new Scanner(System.in)) {
            boolean flag = false;
            while (!flag) {
                System.out.println("Please enter the command");
                String input = scanner.nextLine().toLowerCase();

                InputType inputType = checkInput(input);

                if (inputType.equals(InputType.LIST)) {
                    System.out.println(toDo);
                }

                String[] splitInput = input.split(" ");

                switch (inputType) {
                    case ADD_END:
                        toDo.add(splitInput[1]);
                        break;
                    case ADD_POSITION:
                        toDo.add(Integer.parseInt(splitInput[1]), splitInput[2]);
                        break;
                    case EDIT:
                        if (Integer.parseInt(splitInput[1]) < splitInput.length){
                            toDo.set(Integer.parseInt(splitInput[1]), splitInput[2]);
                        } else System.out.println("Nothing to edit");
                        break;
                    case DELETE:
                        toDo.remove(Integer.parseInt(splitInput[1]));
                        break;
                    case UNKNOWN:
                        continue;
                    case END:
                        flag = true;
                        break;
                }
            }
        }
    }

    private static InputType checkInput(String input) {
        if (input.equals("list")) return InputType.LIST;
        if (input.equals("end")) return InputType.END;
        if (input.matches("^(add)\\s[a-zA-z]+")) return InputType.ADD_END;
        if (input.matches("^(add)\\s\\d+\\s[a-zA-z]+")) return InputType.ADD_POSITION;
        if (input.matches("^(edit)\\s\\d+\\s[a-zA-z]+")) return InputType.EDIT;
        if (input.matches("^(delete)\\s\\d+")) return InputType.DELETE;

        System.out.println("Error in command. Try again");
        return InputType.UNKNOWN;
    }
}
