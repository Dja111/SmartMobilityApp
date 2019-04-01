package Dbadapter;

import Datatypes.ContactData;
import Datatypes.DateData;
import Datatypes.PlaceData;
import Datatypes.TimeData;

import java.sql.Timestamp;

public class Book {

    private int id ;
    private DateData travelDate;
    private PlaceData startingPlace;
    private Timestamp departureTime;
    private PlaceData destinationPlace;
    private Timestamp destinationTime;
    private int numberOfFreeSeats;
    private float trunkSizeForLuggage;
    private float priceForTravel;
    private ContactData contactInformation;
    private  Timestamp creationDate;

    public  Book(int id,DateData travelDate, PlaceData startingPlace, Timestamp departureTime, PlaceData destinationPlace, Timestamp
            destinationTime, int numberOfFreeSeats, float trunkSizeForLuggage, float priceForTravel, ContactData contactInformation,Timestamp creationDate ){
        this.id = id;
        this.travelDate = travelDate;
        this.startingPlace = startingPlace;
        this.departureTime = departureTime;
        this.destinationPlace = destinationPlace;
        this.destinationTime = destinationTime;
        this.numberOfFreeSeats = numberOfFreeSeats;
        this.trunkSizeForLuggage = trunkSizeForLuggage;
        this.priceForTravel = priceForTravel;
        this.contactInformation = contactInformation;
        this.creationDate = creationDate;
    }

    public DateData getTravelDate() {
        return travelDate;
    }

    public ContactData getContactInformation() {
        return contactInformation;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public float getPriceForTravel() {
        return priceForTravel;
    }

    public float getTrunkSizeForLuggage() {
        return trunkSizeForLuggage;
    }

    public int getId() {
        return id;
    }

    public int getNumberOfFreeSeats() {
        return numberOfFreeSeats;
    }

    public PlaceData getDestinationPlace() {
        return destinationPlace;
    }

    public PlaceData getStartingPlace() {
        return startingPlace;
    }

    public Timestamp getDepartureTime() {
        return departureTime;
    }

    public Timestamp getDestinationTime() {
        return destinationTime;
    }

    public void setDepartureTime(Timestamp departureTime) {
        this.departureTime = departureTime;
    }

    public void setContactInformation(ContactData contactInformation) {
        this.contactInformation = contactInformation;
    }

    public void setDestinationTime(Timestamp destinationTime) {
        this.destinationTime = destinationTime;
    }

    public void setDestinationPlace(PlaceData destinationPlace) {
        this.destinationPlace = destinationPlace;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public void setNumberOfFreeSeats(int numberOfFreeSeats) {
        this.numberOfFreeSeats = numberOfFreeSeats;
    }

    public void setPriceForTravel(float priceForTravel) {
        this.priceForTravel = priceForTravel;
    }

    public void setStartingPlace(PlaceData startingPlace) {
        this.startingPlace = startingPlace;
    }

    public void setTravelDate(DateData travelDate) {
        this.travelDate = travelDate;
    }

    public void setTrunkSizeForLuggage(float trunkSizeForLuggage) {
        this.trunkSizeForLuggage = trunkSizeForLuggage;
    }

}
