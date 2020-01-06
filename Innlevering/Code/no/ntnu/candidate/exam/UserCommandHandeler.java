package no.ntnu.candidate.exam;

/**
 * The type User command handeler.
 */
public class UserCommandHandeler {
    private HearingAidCentral hearingAidCentral;
    private InputHandler inputHandeler;

    /**
     * Instantiates a new User command handeler.
     */
    UserCommandHandeler() {
        this.hearingAidCentral = new HearingAidCentral();
        this.inputHandeler = new InputHandler();

        while (true) {
            Commands.getEnum(inputHandeler.getString(
                    "What's your next command? Type help to get help"))
                    .run(this);
        }
    }


    /**
     * Register an aid.
     */
    public void registerAid() {
        //Information needed
        boolean registerUser = this.inputHandeler.getBoolean(
                "Do you want to register a user while registering the aid?");
        int aidID = this.inputHandeler.getIntegerBetween(
                "What's the id of the aid?",
                1000,
                10000);
        String description = this.inputHandeler.getString("What's the description of the aid?");
        boolean gotAdded;
        if (registerUser) {
            String rentersName = this.inputHandeler.getString("What's the name of the renter");
            gotAdded = hearingAidCentral.registerAid(aidID, description, rentersName, true);
        } else {
            gotAdded = hearingAidCentral.registerAid(aidID, description);
        }
        if (gotAdded) {
            System.out.println("The aid was added succesfully");
            return;
        }
        System.out.println("The aid was not added succesfully");
    }

    /**
     * Print all registered aids.
     */
    public void printAllAids() {
        System.out.println("| Hearing aids registered at " + hearingAidCentral.getNameOfCentral());
        hearingAidCentral.getAllAidInformation().forEach(System.out::println);
    }

    /**
     * Populates the register
     */
    public void populate() {
        hearingAidCentral.populateRegister();
    }

    /**
     * Lending out an aid.
     */
    public void lendOut() {
        while (true) {
            int id = this.inputHandeler.getIntegerBetween(
                    "Which hearing aid ID do you want to lend?",
                    1000,
                    10000);
            HearingAid hearingAid = hearingAidCentral.getHearingAidByID(id);

            if (hearingAid == null) {
                System.out.println("Could not find hearing aid");
                if (!this.inputHandeler.getBoolean("Do you want to try again <yes/no>?")) {
                    return;
                }
            } else if (hearingAid.getRentalStatus()) {
                System.out.println("Hearing aid is already lent out.");
                if (!this.inputHandeler.getBoolean("Do you want to try another one <yes/no>?")) {
                    return;
                }
            } else {
                String name = this.inputHandeler.getString("Who is lending the aid");
                hearingAid.borrowAid(name);
            }
        }
    }

    /**
     * Returning an aid.
     */
    public void returning() {
        while (true) {
            int id = this.inputHandeler.getIntegerBetween(
                    "Which hearing aid ID do you want to return?",
                    1000,
                    10000);
            HearingAid hearingAid = hearingAidCentral.getHearingAidByID(id);

            if (hearingAid == null) {
                System.out.println("Could not find hearing aid");
                if (!this.inputHandeler.getBoolean("Do you want to try again <yes/no>?")) {
                    return;
                }
            } else if (!hearingAid.getRentalStatus()) {
                System.out.println("Hearing aid is already returned.");
                if (!this.inputHandeler.getBoolean("Do you want to try again <yes/no>?")) {
                    return;
                }
            } else {
                hearingAid.returningAid();
                System.out.println("You returned the aid");
                return;
            }
        }
    }
}
