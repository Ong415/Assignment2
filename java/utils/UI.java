package utils;

import adt.implementations.ArrayList;
import adt.interfaces.Map;
import adt.interfaces.Stack;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public abstract class UI<C> {
    public interface Validator<T> {
        String validate(T input);

        default Validator<T> compose(Validator<T> validator) {
            return input -> {
                var s = validate(input);
                if (s == null) s = validator.validate(input);
                return s;
            };
        }
    }

    private static final Scanner input = new Scanner(System.in);
    protected C context = null;
    private String screen;

    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm";
    public static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT);

    private void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033\143");
            }
        } catch (Exception ignored) {
        }
    }

    protected void clear() {
        screen = "";
        clearScreen();
    }

    private void refresh() {
        clearScreen();
        System.out.print(screen);
    }

    protected void print(Object object) {
        screen += object;
        System.out.print(object);
    }

    protected void println() {
        screen += '\n';
        System.out.println();
    }

    protected void println(Object object) {
        screen += object;
        screen += '\n';
        System.out.println(object);
    }

    public static <T> Validator<T> noValidation() {
        return input -> null;
    }

    public static final Validator<Integer> NONZERO_INT = input -> input == 0 ? "Only non-zero integers are accepted!" : null;
    public static final Validator<String> NO_BLANK_STRING = input -> input.isBlank() ? "Please enter your input!" : null;
    
    private static String read(String prompt) {
        System.out.print(prompt);
        return input.nextLine();
    }

    private boolean acceptInput(String prompt, String message, Object input) {
        if (message == null) {
            screen += prompt;
            screen += input;
            screen += '\n';
            return true;
        } else {
            refresh();
            System.out.println(message);
            return false;
        }
    }

    protected String readString(String prompt, Validator<String> validator) {
        while (true) {
            var s = read(prompt);
            var m = validator.validate(s);
            if (acceptInput(prompt, m, s)) return s;
        }
    }

    protected int readInt(String prompt, Validator<Integer> validator) {
        while (true) {
            int i = 0;
            var s = read(prompt);
            var m = NO_BLANK_STRING.validate(s);

            if (m == null)
                try {
                    m = validator.validate(i = Integer.parseInt(s));
                } catch (NumberFormatException e) {
                    m = "Only integers are accepted!";
                }

            if (acceptInput(prompt, m, i)) return i;
        }
    }

    protected int readInt(String prompt, int defaultValue, Validator<Integer> validator) {
        while (true) {
            int i = 0;
            String m = null;

            try {
                var s = read(prompt);
                if (s.isBlank()) i = defaultValue;
                else m = validator.validate(i = Integer.parseInt(s));
            } catch (NumberFormatException e) {
                m = "Only integers are accepted!";
            }

            if (acceptInput(prompt, m, i)) return i;
        }
    }

    protected double readDouble(String prompt, Validator<Double> validator) {
        while (true) {
            double f = 0;
            var s = read(prompt);
            var m = NO_BLANK_STRING.validate(s);

            if (m == null)
                try {
                    m = validator.validate(f = Double.parseDouble(s));
                } catch (NumberFormatException e) {
                    m = "Only decimals are accepted!";
                }

            if (acceptInput(prompt, m, f)) return f;
        }
    }

    protected double readDouble(String prompt, double defaultValue, Validator<Double> validator) {
        while (true) {
            double f = 0;
            String m = null;

            try {
                var s = read(prompt);
                if (s.isBlank()) f = defaultValue;
                else m = validator.validate(f = Double.parseDouble(s));
            } catch (NumberFormatException e) {
                m = "Only decimals are accepted!";
            }

            if (acceptInput(prompt, m, f)) return f;
        }
    }

    protected char readChar(String prompt, Validator<Character> validator) {
        while (true) {
            char c = 0;
            var s = read(prompt).strip();
            var m = NO_BLANK_STRING
                    .compose(input -> input.length() == 1 ? null : "Only a single character is accepted!")
                    .validate(s);
            if (m == null) m = validator.validate(c = s.charAt(0));
            if (acceptInput(prompt, m, c)) return c;
        }
    }

    protected char readChar(String prompt, char defaultValue, Validator<Character> validator) {
        while (true) {
            char c = 0;
            var s = read(prompt).strip();
            String m = null;

            if (s.isEmpty()) c = defaultValue;
            else m = (s.length() == 1 ? validator.validate(c = s.charAt(0)) : "Only a single character is accepted!");

            if (acceptInput(prompt, m, c)) return c;
        }
    }

    protected <T> T readOption(String prompt, Map<Character, T> options) {
        return options.get(readChar(prompt, input -> options.has(input) ? null : "Please select one of the options!"));
    }

    protected <T> T readOption(String prompt, char defaultOption, Map<Character, T> options) {
        return options.get(readChar(prompt, defaultOption, input -> options.has(input) ? null : "Please select one of the options!"));
    }

    protected void waitInput() {
        readString("Press Enter to exit", noValidation());
    }

    protected abstract UI<C> display();

    public void run(C context) {
        Stack<UI<C>> navigationStack = new ArrayList<>();

        navigationStack.push(this);

        while (!navigationStack.isEmpty()) {
            var ui = navigationStack.peek();

            ui.clear();
            ui.context = context;
            ui = ui.display();

            if (ui == null) navigationStack.pop();
            else navigationStack.push(ui);
        }
    }
}
