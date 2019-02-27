/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcedatabase.model.db.dao;

import java.util.List;
import mcedatabase.model.entity.Volunteer;

/**
 *
 * @author alifaizat
 */
public interface VolunteerDao {
    
    /*
    * Method to get all the data of volunteer in database
    */
    public List<Volunteer> findAll();
    
    /*
    *Method to get a specific volunteer using an ID
    */
    public Volunteer find(int id);
    
    /*
    * To get the last inserted volunteer
    */
    public Volunteer findLastInserted();
    
    /*
    *Method which allow to add a new volunteer
    */
    public void insert(Volunteer volunteer);
    
    /*
    * Method to update a volunteer
    */
    public void update(Volunteer volunteer);
    
    /*
    * Method to delete a volunteer
    */
    public void delete(Volunteer volunteer);
}
