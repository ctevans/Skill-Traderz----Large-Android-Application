package com.skilltradiez.skilltraderz;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Activities should ideally never need to interact with the model directly.
 * This acts as a controller that facilitates the communication from the views to the models that
 * we possess.
 *
 * Assuming direct control.
 * --Sovereign, mass effect
 */
public class MasterController {
    private static UserDatabase userDB;

    /** DATABASE RELATED **/
    //Initialize the database.
    public void initDB(){
        this.userDB = new UserDatabase();
    }

    //Return the database object!
    public static UserDatabase getUserDB(){
        return userDB;
    }

    //Refresh the database!
    public static void refreshDB(){
        userDB.refresh();
    }

    //I hate this. Deletes ALL data from the database.
    public static void crazyDatabaseDeletion(){
        userDB.deleteAllData();
    }

    public static void save(){
        userDB.save();
    }

    /** USER RELATED **/
    //If we probe for the USER that is currently on the app... returns the USER object of that user.
    //NOT just the name. USER object.
    public static User getCurrentUser(){
        return userDB.getCurrentUser();
    }

    //Give the current username from the database.
    public static String getCurrentUserUsername(){
        return userDB.getCurrentUser().getProfile().getUsername();
    }

    public static String getCurrentUserEmail(){
        return userDB.getCurrentUser().getProfile().getEmail();
    }

    public static boolean isLoggedIn() {
        return userDB.isLoggedIn();
    }

    //Given a profile name... can we please return THE PROFILE OBJECT?! (Yes. Yes we can.)
    public static User getUserByName(String userProfileName){
        return getUserDB().getAccountByUsername(userProfileName);
    }

    public static User getUserByID(ID userID){
        return getUserDB().getAccountByUserID(userID);
    }

    /**FRIEND RELATED **/
    //Do they have THIS friend in particular.
    public static boolean currentUserHasFriend(User currentUser){
        return currentUser.getFriendsList().hasFriend(userDB.getCurrentUser());
    }

    public static void addANewFriend(User currentUser){
        userDB.getCurrentUser().getFriendsList().addFriend(currentUser);
    }

    public static void removeThisFriend(User currentUser){
        userDB.getCurrentUser().getFriendsList().removeFriend(currentUser);
    }

    //When called upon, will create a brand new arraylist of skills associated with that user
    //Primarily used when there is a new user that was not previously detected.
    public static void initializeArrayListForSkills(){
        userDB.getLocal().getLocalData().setSkillz(new ArrayList<Skill>());

    }

    //When we have a new user... we call upon the controller here to interact with the database
    //in order to create a brand new user. Returns this brand new user!
    public static User createNewUser(String usernameGiven, String emailGiven){
        User new_guy = null;

        try {
            new_guy = userDB.createUser(usernameGiven);
        } catch (UserAlreadyExistsException e) {
            e.printStackTrace();
        }
        new_guy.getProfile().setEmail(emailGiven);
        userDB.save();

        return new_guy;
    }

    /** SKILLZ RELATED FUNCTIONS **/

    //Clear the current List<Skill> of skillz!
    // this seems unnecessary
    public static void clearSkillzList(List<Skill> skillz){
        skillz.clear();
    }

    //Obtain from the user database all of the current skills!
    public static Set<Skill> getAllSkillz(){
        return userDB.getSkills();
    }

    public static Set<User> getAllUserz(){
        return userDB.getUsers();
    }

    public void makeNewSkill(String name, String category, String description, boolean isVisible, Image image){
        userDB.getCurrentUser().getInventory().add(new Skill(userDB, name, category, description, isVisible, image));
        save();
    }

    /** SkillDescriptionActivity methods **/

    public static Skill getSkillByID(ID identifier){
        return userDB.getSkillByID(identifier);
    }

    public static void removeCurrentSkill(Skill currentSkill){
        userDB.getCurrentUser().getInventory().remove(currentSkill.getSkillID());
        currentSkill.removeOwner(userDB.getCurrentUser().getUserID());
    }

    public static void addCurrentSkill(Skill currentSkill){
        userDB.getCurrentUser().getInventory().add(currentSkill);
        currentSkill.addOwner(userDB.getCurrentUser().getUserID());
    }

    /** TradeRequestActivity methods **/

    //Given the ID of a trade, we will now RETURN a TRADE OBJECT to the caller of this method.
    public static Trade getCurrentTradeByID(ID identifier){
        return userDB.getTradeByID(identifier);
    }

    //ACCEPT THE TRADE
    public static void acceptTheCurrentTrade(Trade trade){
        trade.getHalfForUser(userDB.getCurrentUser()).setAccepted(true);
    }
}
