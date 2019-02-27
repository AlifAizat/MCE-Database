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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import mcedatabase.model.db.dao.ProgramDao;
import mcedatabase.model.db.singleton.DBSingleton;
import mcedatabase.model.entity.Program;

/**
 *
 * @author alifaizat
 */
public class ProgramDbDao implements ProgramDao{
    
    private DBSingleton dbSingleton;
    
    public ProgramDbDao(){
        try {
            dbSingleton = DBSingleton.getInstance();
        } catch (SQLException ex) {
            
        }
    }

    @Override
    public List<Program> findAll() {
        
        List<Program> thePrograms = new ArrayList<>();
        
        try{
            if(DBSingleton.con == null){
                DBSingleton.getInstance();
            }
            
            Connection con = DBSingleton.con;
            Statement stmt = con.createStatement();
            String query = "SELECT * FROM Program";
            ResultSet rs = stmt.executeQuery(query);
            
            while(rs.next())
            {
                int id = rs.getInt("idProgram");
                String name = rs.getString("name");
                String location = rs.getString("location");
                Date dateStart = rs.getDate("dateStart");
                Date dateEnd = rs.getDate("dateEnd");
                String desc = rs.getString("description");
                
                thePrograms.add(new Program(id, name, location, dateStart, dateEnd, desc));
            }
            
            rs.close();
            stmt.close();
            
        }catch(SQLException ex){
            System.out.println("SQLException FINDALL:" + ex.getMessage());
        }
        
        return thePrograms;
    }

    @Override
    public Program find(int idProgram) {
        
        Program result = null;
        
        try{
            
            if(DBSingleton.con == null){
                DBSingleton.getInstance();
            }
            
            Connection con = DBSingleton.con;
            Statement stmt = con.createStatement();
            String query = "SELECT * FROM Program WHERE idProgram="+idProgram;
            ResultSet rs = stmt.executeQuery(query);
            
            if(rs.next())
            {
                int id = rs.getInt("idProgram");
                String name = rs.getString("name");
                String location = rs.getString("location");
                Date dateStart = rs.getDate("dateStart");
                Date dateEnd = rs.getDate("dateEnd");
                String desc = rs.getString("description");
                
                result = new Program(id, name, location, dateStart, dateEnd, desc);
            }
            
            rs.close();
            stmt.close();
            
        }catch(SQLException ex){
            System.out.println("SQLException FIND : " + ex.getMessage());
        }
        
        return result;
    }

    @Override
    public Program findLastInserted() {
        
        Program result = null;
        
        try{
            if(DBSingleton.con == null){
                DBSingleton.getInstance();
            }
            
            Connection con = DBSingleton.con;
            Statement stmt = con.createStatement();
            String query = "SELECT * FROM Program ORDER BY idProgram DESC LIMIT 1";
            ResultSet rs = stmt.executeQuery(query);
            
            if(rs.next())
            {
                int id = rs.getInt("idProgram");
                String name = rs.getString("name");
                String location = rs.getString("location");
                Date dateStart = rs.getDate("dateStart");
                Date dateEnd = rs.getDate("dateEnd");
                String desc = rs.getString("description");
                
                result = new Program(id, name, location, dateStart, dateEnd, desc);
            }
            
            rs.close();
            stmt.close();
            
        }catch(SQLException ex){
            System.out.println("SQLException FINDLASTINSERTED: " + ex.getMessage());
        }
        
        return result;
    }

    @Override
    public void insert(Program prog) {
        
        try{
            
            if(DBSingleton.con == null){
                DBSingleton.getInstance();
            }
            
            Connection con = DBSingleton.con;
            Statement stmt = con.createStatement();
            
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String dateStart = formatter.format(prog.getDatestart());
            String dateEnd = formatter.format(prog.getDateend());
            
            String query = "INSERT INTO Program(name,location,dateStart,dateEnd,description) "
                    + "VALUES("+"\'" +prog.getName()+"\'"+','+"\'"+prog.getLocation()+"\'"+','+"\'"+dateStart+"\'"+','+"\'"+dateEnd+"\'"+','+"\'"+prog.getDescription()+"\'"+')';
            
            int insert = stmt.executeUpdate(query);
            
            stmt.close();
            
            
        }catch(SQLException ex){
            System.out.println("SQLException INSERT : " + ex.getMessage());
        }
    }

    @Override
    public void update(Program prog) {

        try {

            if (DBSingleton.con == null) {
                DBSingleton.getInstance();
            }

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String dateStart = formatter.format(prog.getDatestart());
            String dateEnd = formatter.format(prog.getDateend());
            
            Connection con = DBSingleton.con;
            Statement stmt = con.createStatement();
            String query = "UPDATE PROGRAM " +
                    "SET name="+prog.getName()+','+"location="+prog.getLocation()+','+"dateStart="+dateStart+','+"dateEnd="+dateEnd+','+prog.getDescription()
                    +" WHERE idProgram="+prog.getId();
            
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
    public void delete(Program prog) {
        
        try{
            
            if(DBSingleton.con == null){
                DBSingleton.getInstance();
            }
            
            Connection con = DBSingleton.con;
            Statement stmt = con.createStatement();
            String query ="DELETE FROM Program WHERE idProgram="+prog.getId();
            
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
