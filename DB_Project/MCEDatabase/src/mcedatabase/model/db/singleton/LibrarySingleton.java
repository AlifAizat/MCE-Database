/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcedatabase.model.db.singleton;

import mcedatabase.controller.MainWindowsFXMLController;
import mcedatabase.controller.tabs.*;
import mcedatabase.model.entity.*;

/**
 *
 * @author alifaizat
 */
public class LibrarySingleton {
    
    //Static variable singleton_instance of type LibrarySingleton
    public static LibrarySingleton singleton_instance = null;
    public static MainTabFXMLController maintab_instance = null;
    public static VolunteerTabFXMLController volunteertab_instance = null;
    public static ProgramTabFXMLController programtab_instance = null;
    public static MainWindowsFXMLController parent_instance = null;
    public static Volunteer volunteer_instance = null;
    public static Program program_instance = null;
    
    //Variable of type Library
    public Library mceLibrary;
    
    //private constructeur restricted to the class itself
    private LibrarySingleton()
    {
        mceLibrary = new Library();
    }
    
    //static method to create instance of LibrarySingleton class
    public static LibrarySingleton librarySingleton(){
        
        //to ensure that only one instance is created
        if(singleton_instance == null)
        {
            singleton_instance = new LibrarySingleton();
        }
        
        return singleton_instance;
    }
    
    //static method to create instance of controllers
    
    public static void createMainWindowsFXMLController(MainWindowsFXMLController mw){
        if(parent_instance == null)
        {
            parent_instance = mw;
        }
    }
    
    public static void createMainTabController(MainTabFXMLController mt){
        if(maintab_instance == null)
        {
            maintab_instance = mt;
        }
    }
    
    public static void createVolunteerTabController(VolunteerTabFXMLController vt){
        if(volunteertab_instance == null)
        {
            volunteertab_instance = vt;
        }
    }
    
    public static void createProgramTabController(ProgramTabFXMLController pt){
        if(programtab_instance == null)
        {
            programtab_instance = pt;
        }
    }

    //Setter for the instance of Volunteer and Program
    public static void setVolunteer(Volunteer volunteer_instance) {
        LibrarySingleton.volunteer_instance = volunteer_instance;
    }

    public static void setProgram(Program program_instance) {
        LibrarySingleton.program_instance = program_instance;
    }
    
    

    //Getter for the instances
    
    public static MainWindowsFXMLController getParent_instance(){
        return parent_instance;
    }
    
    public static MainTabFXMLController getMaintab_instance() {
        return maintab_instance;
    }

    public static VolunteerTabFXMLController getVolunteertab_instance() {
        return volunteertab_instance;
    }

    public static ProgramTabFXMLController getProgramtab_instance() {
        return programtab_instance;
    }

    public static Volunteer getVolunteer_instance() {
        return volunteer_instance;
    }

    public static Program getProgram_instance() {
        return program_instance;
    }
    
    
    
}
