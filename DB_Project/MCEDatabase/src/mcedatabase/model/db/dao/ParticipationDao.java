/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcedatabase.model.db.dao;

import java.util.List;
import java.util.Map;
import mcedatabase.model.entity.Participation;

/**
 *
 * @author alifaizat
 */
public interface ParticipationDao {
    
    /*
    *Method to get all participation in database
    */
    public List<Participation> findAll();
    
    /*
    * Method to insert a participation
    */
    public void insert(int volunteer, int program);
    
    /*
    * Method to insert a participation
    */
    public void insertAll(List<Participation> participations);
    
    /*
    * Method to delete a participation
    */
    public void delete(int volunteer, int program);
}
