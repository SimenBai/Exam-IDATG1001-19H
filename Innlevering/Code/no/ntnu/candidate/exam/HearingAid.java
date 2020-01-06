package no.ntnu.candidate.exam;

/**
 * The Hearing aid class.
 */
public class HearingAid {
    private int aidID;
    private String description;
    private String rentalName;
    private boolean rentalStatus;

    /**
     * Instantiates a new Hearing aid.
     *
     * @param aidID       the aid id
     * @param description the description
     */
    public HearingAid(int aidID, String description) {
        this.aidID = aidID;
        this.description = description;
        this.rentalName = "";
        this.rentalStatus = false;
    }

    /**
     * Instantiates a new Hearing aid.
     *
     * @param aidID        the aid id
     * @param description  the description
     * @param rentalName   the rental name
     * @param rentalStatus the rental status
     */
    public HearingAid(int aidID, String description, String rentalName, boolean rentalStatus) {
        this.aidID = aidID;
        this.description = description;

        this.rentalName = rentalName;
        this.rentalStatus = rentalStatus;
    }

    /**
     * Gets aid id.
     *
     * @return the aid id
     */
    public int getAidID() {
        return aidID;
    }

    private String getDescription() {
        return description;
    }

    private String getRentalName() {
        return rentalName;
    }

    /**
     * Gets rental status.
     *
     * @return the rental status
     */
    public boolean getRentalStatus() {
        return rentalStatus;
    }

    private void setRentalName(String name) {
        this.rentalName = name;
    }

    private void setRentalStatus(boolean rentalStatus) {
        this.rentalStatus = rentalStatus;
    }

    /**
     * Compare objects and return true if they have the same aidID.
     *
     * @param hearingAid the hearing aid you want to compare against
     * @return the boolean
     */
    public boolean compareObjects(HearingAid hearingAid) {
        return hearingAid.getAidID() == this.getAidID();
    }

    /**
     * Returning aid from borrowed
     */
    public void returningAid() {
        this.setRentalName("");
        this.setRentalStatus(false);
    }

    /**
     * Borrow aid
     *
     * @param rentalName the rental name
     * @return string Regarding if it was borrowed out or not
     */
    public String borrowAid(String rentalName) {
        //Rented out
        if (this.getRentalStatus()) {
            return "The " + this.getDescription() + " with the id: " + this.getAidID() + " is already leased out";
        } else {
            this.setRentalName(rentalName);
            this.setRentalStatus(true);
            return this.getRentalName() +
                    " has now borrowed " +
                    this.getDescription() +
                    " with the id: " +
                    this.getAidID();
        }
    }

    /**
     * Gets information about the hearing aid.
     *
     * @return the information
     */
    public String getInformation() {
        //General information
        String information = this.getAidID() + " " + this.getDescription() + " ";

        //If it is rented out
        if (rentalStatus) {
            information += "leased to " + this.getRentalName();
        } else {
            information += "available";
        }
        return information;
    }
}
