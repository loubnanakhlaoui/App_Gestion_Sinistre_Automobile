package sgsa;

import java.sql.Date;

public class InsuredData {
    
    private String insured_id;
    private String firstName;
    private String lastName;
    private String gender;
    private String phoneNum;
    private String image;
    private Date date;
  
    public InsuredData(String insured_id, String firstName, String lastName, String gender, String phoneNum, String image, Date date){
        this.insured_id = insured_id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.phoneNum = phoneNum;
        this.image = image;
        this.date = date;
    }
    
    public String getInsured_id(){
        return insured_id;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getGender(){
        return gender;
    }

    public String getPhoneNum(){
        return phoneNum;
    }

    public String getImage(){
        return image;
    }

    public Date getDate(){
        return date;
    }
}
