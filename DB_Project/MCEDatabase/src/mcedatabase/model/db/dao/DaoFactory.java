/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcedatabase.model.db.dao;

/**
 *
 * @author alifaizat
 */
public interface DaoFactory {
    
    public VolunteerDao getVolunteerDao();
    
    public ProgramDao getProgramDao();
    
    public ParticipationDao getParticipationDao();
}
