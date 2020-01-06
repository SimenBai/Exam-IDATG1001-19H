package no.ntnu.candidate.exam;

import java.util.ArrayList;

/**
 * The type Hearing aid central.
 */
public class HearingAidCentral {
    private ArrayList<HearingAid> aidRegister;
    private String nameOfCentral;

    /**
     * Instantiates a new Hearing aid central.
     */
    public HearingAidCentral() {
        this.aidRegister = new ArrayList<>();
        this.nameOfCentral = "";
    }

    /**
     * Instantiates a new Hearing aid central.
     *
     * @param centralName the central name
     */
    public HearingAidCentral(String centralName){
        this.aidRegister = new ArrayList<>();
        this.nameOfCentral = centralName;
    }

    /**
     * Gets name of central.
     *
     * @return the name of central
     */
    public String getNameOfCentral() {
        return nameOfCentral;
    }

    /**
     * Populates register.
     */
    public void populateRegister() {
        registerAid(1001, "Test1");
        registerAid(1002, "Test2");
        registerAid(1003, "Test3");
        registerAid(1004, "Test4");
        registerAid(1006, "Test4", "Name1", true);
        registerAid(1007, "Test5", "Name2", true);
        registerAid(1008, "Test6", "Name3", true);
    }

    /**
     * Register an aid
     *
     * @param aidID       the aid id
     * @param description the description
     * @return If it was successful or not
     */
    public boolean registerAid(int aidID, String description) {
        return registerAid(createAid(aidID, description));
    }

    /**
     * Register an aid.
     *
     * @param aidID        the aid id
     * @param description  the description
     * @param rentalName   the rental name
     * @param rentalStatus the rental status
     * @return If it was successful or not
     */
    public boolean registerAid(int aidID, String description, String rentalName, boolean rentalStatus) {
        return registerAid(createAid(aidID, description, rentalName, rentalStatus));
    }

    /**
     * Register aid
     *
     * @param hearingAid the hearing aid
     * @return If it was successful or not
     */
    public boolean registerAid(HearingAid hearingAid) {
        if (hearingAid == null) {
            return false;
        }
        for (HearingAid aid : aidRegister) {
            if (aid.compareObjects(hearingAid)) {
                return false;
            }
        }
        aidRegister.add(hearingAid);
        return true;
    }

    private boolean checkAidIDParams(int aidID) {
        return aidID < 1001 || aidID > 9999;
    }

    /**
     * Create hearing aid.
     *
     * @param aidID       the aid id
     * @param description the description
     * @return the hearing aid
     */
    public HearingAid createAid(int aidID, String description) {
        if (checkAidIDParams(aidID)) {
            return null;
        }
        return new HearingAid(aidID, description);
    }

    /**
     * Create hearing aid.
     *
     * @param aidID        the aid id
     * @param description  the description
     * @param rentalName   the rental name
     * @param rentalStatus the rental status
     * @return the hearing aid
     */
    public HearingAid createAid(int aidID, String description, String rentalName, boolean rentalStatus) {
        if (checkAidIDParams(aidID)) {
            return null;
        }
        return new HearingAid(aidID, description, rentalName, rentalStatus);
    }

    /**
     * Lend out hearing aid to rental name.
     *
     * @param hearingAid the hearing aid
     * @param rentalName the rental name
     */
    public void lendOut(HearingAid hearingAid, String rentalName) {
        System.out.println(hearingAid.borrowAid(rentalName));
    }

    /**
     * Returning aid.
     *
     * @param hearingAid the hearing aid
     */
    public void returningAid(HearingAid hearingAid) {
        hearingAid.returningAid();
    }

    /**
     * Gets hearing aid by id.
     *
     * @param id the id
     * @return the hearing aid by id
     */
    public HearingAid getHearingAidByID(int id) {
        for(HearingAid aid: aidRegister) {
            if (id == aid.getAidID()) {
                return aid;
            }
        }
        return null;
    }

    /**
     * Gets available aids.
     *
     * @param aidName the aid name
     * @return the available aids
     */
    public ArrayList<HearingAid> getAvailableAids(String aidName) {
        ArrayList<HearingAid> availableAids = new ArrayList<>();
        aidRegister.forEach((hearingAid) -> {
            if (!hearingAid.getRentalStatus()) {
                availableAids.add(hearingAid);
            }
        });
        return availableAids;
    }

    /**
     * Gets all aid information.
     *
     * @return the all aid information
     */
    public ArrayList<String> getAllAidInformation() {
        ArrayList<String> aidInformation = new ArrayList<>();
        aidRegister.forEach((hearingAid -> {
            aidInformation.add(hearingAid.getInformation());
        }));
        return aidInformation;
    }
}
