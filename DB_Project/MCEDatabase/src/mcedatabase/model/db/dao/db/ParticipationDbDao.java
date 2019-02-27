/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcedatabase.model.db.dao.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import mcedatabase.model.db.dao.ParticipationDao;
import mcedatabase.model.db.singleton.DBSingleton;
import mcedatabase.model.entity.Participation;

/**
 *
 * @author alifaizat
 */
public class ParticipationDbDao implements ParticipationDao{
    
    private DBSingleton dbSingleton;

    public ParticipationDbDao() {
        try {
            dbSingleton = DBSingleton.getInstance();
        } catch (SQLException ex) {

        }
    }

    @Override
    public List<Participation> findAll() {
        
        List<Participation> theParticipations = new ArrayList<>();
        
        try{
            if(DBSingleton.con == null){
                DBSingleton.getInstance();
            }
            
            Connection con = DBSingleton.con;
            Statement stmt = con.createStatement();
            String query = "SELECT * FROM Participation";
            ResultSet rs = stmt.executeQuery(query);
            
            while(rs.next())
            {
                theParticipations.add(new Participation(rs.getInt("volunteer_id"), rs.getInt("program_id")));
            }
            
            rs.close();
            stmt.close();
            
        }catch(SQLException ex){
            System.out.println("SQLException FINDALL:" + ex.getMessage());
        }
        
        return theParticipations;
    }

    @Override
    public void insert(int volunteer, int program) {
        
        try{
            if(DBSingleton.con == null){
                DBSingleton.getInstance();
            }
            
            Connection con = DBSingleton.con;
            Statement stmt = con.createStatement();
            String query = "INSERT INTO Participation(volunteer_id,program_id) "
                    + "VALUES("+volunteer+','+program+ ")";
            
            int insert = stmt.executeUpdate(query);
            stmt.close();
            
        }catch(SQLException ex){
            System.out.println("SQLException INSERT : " + ex.getMessage());
        }
    }

    @Override
    public void insertAll(List<Participation> participations) {
        
        try{
            if(DBSingleton.con == null){
                DBSingleton.getInstance();
            }
            
            Connection con = DBSingleton.con;
            Statement stmt = con.createStatement();
        
            for (Participation part : participations) {
                String query = "INSERT INTO Participation(volunteer_id,program_id) "
                        + "VALUES(" + part.getVolunteer() + ',' + part.getProgram() + ")";

                int insert = stmt.executeUpdate(query);
            }

            stmt.close();
            
        }catch(SQLException ex){
            
        }
    }

    @Override
    public void delete(int volunteer, int program) {
        
        try{
            if(DBSingleton.con == null){
                DBSingleton.getInstance();
            }
            
            Connection con = DBSingleton.con;
            Statement stmt = con.createStatement();
            String query = "DELETE FROM Participation WHERE volunteer_id="+volunteer+" AND program_id="+program;
            
            int delete = stmt.executeUpdate(query);
            
            if(delete == 0)
            {
                System.out.println("Delete impossible !");
            }
            
            stmt.close();
            
        }catch(SQLException ex){
            
        }
    }
}
