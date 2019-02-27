/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcedatabase.model.db.dao;

import java.util.List;
import mcedatabase.model.entity.Program;

/**
 *
 * @author alifaizat
 */
public interface ProgramDao {
     /*
    * Method to get all the data of program in database
    */
    public List<Program> findAll();
    
    /*
    *Method to get a specific program using an ID
    */
    public Program find(int id);
    
    /*
    * To get the last inserted program
    */
    public Program findLastInserted();
    
    /*
    *Method which allow to add a new program
    */
    public void insert(Program program);
    
    /*
    * Method to update a program
    */
    public void update(Program program);
    
    /*
    * Method to delete a program
    */
    public void delete(Program program);
}
