/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcedatabase.model.db.dao.db;

import mcedatabase.model.db.dao.DaoFactory;
import mcedatabase.model.db.dao.ParticipationDao;
import mcedatabase.model.db.dao.ProgramDao;
import mcedatabase.model.db.dao.VolunteerDao;

/**
 *
 * @author alifaizat
 */
public class DbFactoryDao implements DaoFactory{

    
    @Override
    public VolunteerDao getVolunteerDao() {
        return new VolunteerDbDao();
    }

    @Override
    public ProgramDao getProgramDao() {
        return new ProgramDbDao();
    }

    @Override
    public ParticipationDao getParticipationDao() {
        return new ParticipationDbDao();
    }
    
}
