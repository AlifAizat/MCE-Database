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
import java.util.logging.Level;
import java.util.logging.Logger;
import mcedatabase.model.db.dao.VolunteerDao;
import mcedatabase.model.db.singleton.DBSingleton;
import mcedatabase.model.entity.Volunteer;

/**
 *
 * @author alifaizat
 */
public class VolunteerDbDao implements VolunteerDao{
    
    private DBSingleton dbSingleton;
    
    public VolunteerDbDao(){
        try {
            dbSingleton = DBSingleton.getInstance();
        } catch (SQLException ex) {
            
        }
    }
    
    @Override
    public List<Volunteer> findAll() {
        
        List<Volunteer> theVolunteers = new ArrayList<>();
        
        try{
            
            if(DBSingleton.con == null){
                DBSingleton.getInstance();
            }
            
            Connection con = DBSingleton.con;
            Statement stmt = con.createStatement();
            String query = "SELECT * FROM Volunteer";
            ResultSet rs = stmt.executeQuery(query);
            
            while(rs.next())
            {
                int id = rs.getInt("idVolunteer");
                String firstname = rs.getString("firstname");
                String lastname  = rs.getString("lastname");
                String tel = rs.getString("tel");
                String ic  = rs.getString("ic");
                String nationality = rs.getString("nationality");
                String university = rs.getString("university");
                String country = rs.getString("country");
                String course = rs.getString("course");
                String email = rs.getString("email");
                
                theVolunteers.add(new Volunteer(id , firstname, lastname, tel, ic, nationality, university, country, course, email));
            }
            
            rs.close();
            stmt.close();
            
        }catch(SQLException ex){
            System.out.println("SQLException FINDALL:" + ex.getMessage());
        }
        
        return theVolunteers; 
    }

    @Override
    public Volunteer find(int idVolunteer) {
        
        Volunteer result = null;
        
        try{
            
            if(DBSingleton.con == null){
                DBSingleton.getInstance();
            }
            
            Connection con = DBSingleton.con;
            Statement stmt = con.createStatement();
            String query = "SELECT * FROM Volunteer WHERE idVolunteer=" + idVolunteer;
            ResultSet rs = stmt.executeQuery(query);
            
            if(rs.next()){
                
                int id = rs.getInt("idVolunteer");
                String firstname = rs.getString("firstname");
                String lastname  = rs.getString("lastname");
                String tel = rs.getString("tel");
                String ic  = rs.getString("ic");
                String nationality = rs.getString("nationality");
                String university = rs.getString("university");
                String country = rs.getString("country");
                String course = rs.getString("course");
                String email = rs.getString("email");
                
                result = new Volunteer(id, firstname, lastname, tel, ic, nationality, university, country, course, email);
            }
            
            rs.close();
            stmt.close();
            
        }catch(SQLException ex)
        {
            System.out.println("SQLException FIND : " + ex.getMessage());
        }
        
        return result;
    }

    @Override
    public Volunteer findLastInserted() {
        
        Volunteer result = null;
        
        try{
            
            if(DBSingleton.con == null){
                DBSingleton.getInstance();
            }
            
            Connection con = DBSingleton.con;
            Statement stmt = con.createStatement();
            String query = "SELECT * FROM Volunteer ORDER BY idVolunteer DESC LIMIT 1";
            ResultSet rs = stmt.executeQuery(query);
            
            if(rs.next()){
                
                int id = rs.getInt("idVolunteer");
                String firstname = rs.getString("firstname");
                String lastname  = rs.getString("lastname");
                String tel = rs.getString("tel");
                String ic  = rs.getString("ic");
                String nationality = rs.getString("nationality");
                String university = rs.getString("university");
                String country = rs.getString("country");
                String course = rs.getString("course");
                String email = rs.getString("email");
                
                result = new Volunteer(id, firstname, lastname, tel, ic, nationality, university, country, course, email);
            }
            
            rs.close();
            stmt.close();
            
        }catch(SQLException ex)
        {
            System.out.println("SQLException FINDLASTINSERTED: " + ex.getMessage());
        }
        
        return result;
    }

    @Override
    public void insert(Volunteer vol) {
        
        try{
            
            if(DBSingleton.con == null){
                DBSingleton.getInstance();
            }
            
            Connection con = DBSingleton.con;
            Statement stmt = con.createStatement();
            String query = "INSERT INTO Volunteer(firstname,lastname,tel,ic,email,nationality,university,country,course) " +
                           "VALUES(\'"+vol.getFirstname()+"\'"+","+"\'"+vol.getLastname()+"\'"+','+"\'"+vol.getTel()+"\'"
                            +','+"\'"+vol.getIc()+"\'"+','+"\'"+vol.getEmail()+"\'"+','+"\'"+vol.getNationality()+"\'"+','+"\'"+vol.getUniversity()
                            +"\'"+','+"\'"+vol.getCountry()+"\'"+','+"\'"+vol.getCourse()+"\'"+")";
            
            int insert = stmt.executeUpdate(query);
            
            stmt.close();
            
        }catch(SQLException ex){
            System.out.println("SQLException INSERT : " + ex.getMessage());
        }
    }

    @Override
    public void update(Volunteer vol) {
        
        try{
            
            if(DBSingleton.con == null){
                DBSingleton.getInstance();
            }
            
            Connection con = DBSingleton.con;
            Statement stmt = con.createStatement();
            String query = "UPDATE Volunteer " + 
                            "SET firstname="+vol.getFirstname()+','+"lastname="+vol.getLastname()+','+"tel="+vol.getTel()
                           +','+"ic="+vol.getIc()+','+"email="+vol.getEmail()+','+"nationality="+vol.getNationality()
                           +','+"university="+vol.getUniversity()+','+"country="+vol.getCountry()+','+"course="+vol.getCourse()+" "
                           +"WHERE idVolunteer="+vol.getId();
            
            int update = stmt.executeUpdate(query);
            
            if(update == 0)
            {
                System.out.println("Update impossible !");
            }
            
            stmt.close();
            
        }catch(SQLException ex){
            System.out.println("SQLException UPDATE : " + ex.getMessage());
        }
    }

    @Override
    public void delete(Volunteer vol) {
        
        try{
            
            if(DBSingleton.con == null){
                DBSingleton.getInstance();
            }
            
            Connection con = DBSingleton.con;
            Statement stmt = con.createStatement();
            String query ="DELETE FROM Volunteer WHERE idVolunteer="+vol.getId();
            
            int delete = stmt.executeUpdate(query);
            
            if(delete == 0)
            {
                System.out.println("Delete impossible !");
            }
            
            stmt.close();
            
        }catch(SQLException ex){
            System.out.println("SQLException DELETE : " + ex.getMessage());
        }
    }
    
}
