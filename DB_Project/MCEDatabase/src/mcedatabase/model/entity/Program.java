/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcedatabase.model.entity;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author alifaizat
 */
public class Program {
    
    private int id;
    private String name;
    private String location;
    private Date datestart;
    private Date dateend;
    private String description;
    private ArrayList<Volunteer> participants;

    public Program(String name, String location, Date datestart, Date dateend, String description) {
        this.name = name;
        this.location = location;
        this.datestart = datestart;
        this.dateend = dateend;
        this.description = description;
        this.participants = new ArrayList<>();
    }

    public Program(int id, String name, String location, Date datestart, Date dateend, String description) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.datestart = datestart;
        this.dateend = dateend;
        this.description = description;
        this.participants = new ArrayList<>();
    }

    
    
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getDatestart() {
        return datestart;
    }

    public void setDatestart(Date datestart) {
        this.datestart = datestart;
    }

    public Date getDateend() {
        return dateend;
    }

    public void setDateend(Date dateend) {
        this.dateend = dateend;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Volunteer> getParticipants() {
        return participants;
    }

    public void setParticipants(ArrayList<Volunteer> participants) {
        this.participants = participants;
    }
    
    public void deleteParticipant(Volunteer vol){
        this.participants.remove(vol);
    }
}
