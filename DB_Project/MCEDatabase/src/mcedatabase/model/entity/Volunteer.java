/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcedatabase.model.entity;

import java.util.ArrayList;

/**
 *
 * @author alifaizat
 */
public class Volunteer {
    
    private String firstname;
    private String lastname;
    private int id;
    private String tel;
    private String ic;
    private String nationality;
    private String university;
    private String country;
    private String course;
    private String email;
    private int nbprogram;
    private ArrayList<Program> programs;

    public Volunteer(){
        
    }
    
    public Volunteer(String firstname, String lastname, String tel, String ic, String nationality, String university, String country, String course, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.tel = tel;
        this.ic = ic;
        this.nationality = nationality;
        this.university = university;
        this.country = country;
        this.course = course;
        this.email = email;
        this.programs = new ArrayList<>();
    }

    public Volunteer( int id,String firstname, String lastname, String tel, String ic, String nationality, String university, String country, String course, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.id = id;
        this.tel = tel;
        this.ic = ic;
        this.nationality = nationality;
        this.university = university;
        this.country = country;
        this.course = course;
        this.email = email;
        this.programs = new ArrayList<>();
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getIc() {
        return ic;
    }

    public void setIc(String ic) {
        this.ic = ic;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNbprogram() {
        this.nbprogram = this.programs.size();
        return Integer.toString(nbprogram);
    }

    public void setNbprogram() {
        this.nbprogram = this.getPrograms().size();
    }

    public ArrayList<Program> getPrograms() {
        return programs;
    }

    public void setPrograms(ArrayList<Program> programs) {
        this.programs = programs;
    }

    public void deleteProgram(Program prog){
        this.programs.remove(prog);
    }
    
}
