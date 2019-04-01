package Datatypes;

/**
* Contains the necessary informations about a ContactData
* @author  Group_13
*
* */
public class ContactData {

    private String name;
    private int phoneNumber;
    private String emailAddress;

    public ContactData(){

    }

    public ContactData(String name, int phoneNumber, String emailAddress ){
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    public String getEmailAddress() {
        return emailAddress;
    }


    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
