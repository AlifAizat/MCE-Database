/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcedatabase.model.entity;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alifaizat
 */
public class Library {
    
    private List<Volunteer> volunteers;
    private List<Program> programs;
    private String user;
    private List<Participation> participations;

    public Library() {
        this.participations = new ArrayList<>();
        this.programs = new ArrayList<>();
        this.volunteers = new ArrayList<>();
        this.user = System.getProperty("user.name");
    }
    
    /*
    * return a list of Volunteer
    * List<Volunteer>
    */
    public List<Volunteer> getVolunteers() {
        return volunteers;
    }
    
    /*
    * set the instance of list of Volunteer
    */
    public void setVolunteers(List<Volunteer> volunteers) {
        this.volunteers = volunteers;
    }

    /*
    * return a list of Program
    * List<Program>
    */
    public List<Program> getPrograms() {
        return programs;
    }

    /*
    * set the instance of list of Program
    */
    public void setPrograms(List<Program> programs) {
        this.programs = programs;
    }
    
    /*
    * return a String of name of the connected user
    */
    public String getUser() {
        return user;
    }
    
    /*
    * Delete a Volunteer from the list of Volunteer
    */
    public void deleteVolunteer(Volunteer vol){
        for(Program p : vol.getPrograms())
        {
            p.deleteParticipant(vol);
        }
        this.volunteers.remove(vol);
    }
    
    /*
    * Delete a Program from the list of Program
    */
    public void deleteProgram(Program prog){
        for(Volunteer v : prog.getParticipants())
        {
            v.deleteProgram(prog);
        }
        
        this.programs.remove(prog);
    }

    /*
    * 
    */
    public List<Participation> getParticipations() {
        return participations;
    }

    public void setParticipations(List<Participation> participations) {
        this.participations = participations;
    }
    
    public Volunteer getVolunteerViaID(int id){
        Volunteer vol = null;
        
        for(Volunteer v : this.volunteers)
        {
            if(v.getId() == id)
            {
                vol = v;
                break;
            }
        }
        
        return vol;
    }
    
    
    public Program getProgramViaId(int id){
        Program prog = null;
        
        for(Program p : this.programs)
        {
            if(p.getId() == id)
            {
                prog = p;
            }
        }
        
        return prog;
    }
    
}
