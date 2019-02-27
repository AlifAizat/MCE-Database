/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcedatabase.model.entity;

/**
 *
 * @author alifaizat
 */
public class Participation {
    
    private int volunteer;
    private int program;

    public Participation() {
    }

    
    
    public Participation(int volunteer, int program) {
        this.volunteer = volunteer;
        this.program = program;
    }

    public int getVolunteer() {
        return volunteer;
    }

    public void setVolunteer(int volunteer) {
        this.volunteer = volunteer;
    }

    public int getProgram() {
        return program;
    }

    public void setProgram(int program) {
        this.program = program;
    }
    
    
}
