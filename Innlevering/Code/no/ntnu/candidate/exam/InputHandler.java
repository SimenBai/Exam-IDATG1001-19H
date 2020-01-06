package no.ntnu.candidate.exam;

import java.util.Scanner;

/**
 * The type Input handler.
 */
public class InputHandler {
    private Scanner scanner;

    /**
     * Instantiates a new Input handler.
     */
    public InputHandler() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Gets any string.
     *
     * @param question the question
     * @return the any string
     */
    public String getAnyString(String question) {
        System.out.println(question);
        return this.scanner.nextLine();
    }

    /**
     * Gets string.
     *
     * @param question the question
     * @return the string
     */
    public String getString(String question) {
        while (true) {
            String answer = getAnyString(question);
            if (!answer.isEmpty()) {
                return answer;
            } else {
                System.out.println("Please enter something!");
            }
        }
    }

    /**
     * Gets integer.
     *
     * @param question the question
     * @return the integer
     */
    public int getInteger(String question) {
        while (true) {
            String answer = getAnyString(question);

            try {
                return Integer.parseInt(answer);
            } catch (NumberFormatException exception) {
                System.out.println("Please enter a valid number");
            }
        }
    }

    /**
     * Gets integer above zero.
     *
     * @param question the question
     * @return the integer above zero
     */
    public int getIntegerAboveZero(String question) {
        while (true) {
            int answer = getInteger(question);
            if (answer > 0) {
                return answer;
            }

            System.out.println("Please enter a number above zero");
        }
    }

    /**
     * Gets integer between two values, exclusive.
     *
     * @param question the question
     * @param minimum  The minimum value
     * @param maximum  The maximum value
     * @return the integer above zero
     */
    public int getIntegerBetween(String question, int minimum, int maximum) {
        while (true) {
            int answer = getInteger(question);
            if (answer > minimum && answer < maximum) {
                return answer;
            }
            System.out.println("Please enter a number between " + minimum + " and " + maximum);
        }
    }

    /**
     * Gets boolean.
     *
     * @param question the question
     * @return the boolean
     */
    public boolean getBoolean(String question) {
        String answer = getAnyString(question);
        if (answer.equalsIgnoreCase("yes") || answer.equalsIgnoreCase("ja")) {
            return true;
        } else {
            return Boolean.parseBoolean(answer);
        }
    }
}

