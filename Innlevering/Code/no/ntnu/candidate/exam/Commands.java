package no.ntnu.candidate.exam;


/**
 * The enum Commands.
 */
public enum Commands {
    /**
     * The add command enum.
     */
    ADD("Let's you register a aid", "register") {
        @Override
        public void run(UserCommandHandeler userCommandHandeler) {
            userCommandHandeler.registerAid();
        }
    },

    /**
     * The populate command enum.
     */
    POPULATE("Let's you artifically populate the register") {
        @Override
        public void run(UserCommandHandeler userCommandHandeler) {
            userCommandHandeler.populate();
        }
    },

    /**
     * The lend command enum.
     */
    LEND("Lend a hearing aid", "borrow") {
        @Override
        public void run(UserCommandHandeler userCommandHandeler) {
            userCommandHandeler.lendOut();
        }
    },

    /**
     * The return command enum.
     */
    RETURN("Lend a hearing aid", "returning") {
        @Override
        public void run(UserCommandHandeler userCommandHandeler) {
            userCommandHandeler.returning();
        }
    },

    /**
     * The print command enum.
     */
    PRINT("Print all information about registerd aids", "list", "all") {
        @Override
        public void run(UserCommandHandeler userCommandHandeler) {
            userCommandHandeler.printAllAids();
        }
    },

    /**
     * The print command enum.
     */
    EXIT("Exits the program", "leave", "quit") {
        @Override
        public void run(UserCommandHandeler userCommandHandeler) {
            System.exit(0);
        }
    },
    /**
     * The help command enum
     */
    HELP("Prints the help function", "hjelp") {
        @Override
        public void run(UserCommandHandeler userCommandHandeler) {
            Commands.printHelp();
        }
    };


    /**
     * The function that should be used to run each of the different commands
     *
     * @param userCommandHandeler The object that handels the user commands
     */
    public abstract void run(UserCommandHandeler userCommandHandeler);

    private String explanation;
    private String[] aliases;

    Commands(String explanation) {
        this.explanation = explanation;
        this.aliases = new String[]{this.toString()};
    }

    Commands(String explanation, String... aliases) {
        this.explanation = explanation;
        this.aliases = new String[aliases.length + 1];
        System.arraycopy(aliases, 0, this.aliases, 0, aliases.length);
        this.aliases[aliases.length] = this.toString();
    }

    /*
        Prints the help based on the enums values
     */
    private static void printHelp() {
        System.out.println("+------------------------+");
        System.out.println("|          HELP          |");
        System.out.println("+------------------------+");
        for (Commands command : Commands.values()) {
            StringBuilder output = new StringBuilder(command.aliases[command.aliases.length - 1] + " - ");
            output.append(command.explanation).append(" - <");
            boolean hasAlias = false;
            for (int i = 0; i < command.aliases.length - 1; i++) {
                if (hasAlias) {
                    output.append(" ");
                }
                output.append(command.aliases[i]).append(",");
                hasAlias = true;
            }
            if (hasAlias) {
                output = new StringBuilder(output.substring(0, output.length() - 1));
            }
            output.append(">");
            System.out.println(output);
        }
    }

    /**
     * Gets a enum with the given input.
     *
     * @param input the input
     * @return the enum or Commands.UNKNOWN if a enum could not be found
     */
    public static Commands getEnum(String input) {
        for (Commands command : Commands.values()) {
            if (command.toString().equalsIgnoreCase(input.trim())) {
                return command;
            }
            for (String alias : command.aliases) {
                if (alias.equalsIgnoreCase(input.trim())) {
                    return command;
                }
            }
        }
        return Commands.HELP;
    }
}
