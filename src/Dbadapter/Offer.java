package Dbadapter;

import Datatypes.ContactData;
import Datatypes.DateData;
import Datatypes.PlaceData;
import Datatypes.TimeData;

import java.sql.Timestamp;

public class Offer {

    private int id ;
    private DateData travelDate;
    private PlaceData startingPlace;
    private TimeData departureTime;
    private PlaceData destinationPlace;
    private TimeData destinationTime;
    private int numberOfFreeSeats;
    private float trunkSizeForLuggage;
    private float priceForTravel;
    private ContactData contactInformation;



    public Offer(int id,DateData travelDate, PlaceData startingPlace, TimeData departureTime, PlaceData destinationPlace, TimeData
            destinationTime, int numberOfFreeSeats, float trunkSizeForLuggage, float priceForTravel, ContactData contactInformation){
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
    }
    public Offer(DateData travelDate, PlaceData startingPlace, TimeData departureTime, PlaceData destinationPlace, TimeData
            destinationTime, int numberOfFreeSeats, float trunkSizeForLuggage, float priceForTravel, ContactData contactInformation){
        this.travelDate = travelDate;
        this.startingPlace = startingPlace;
        this.departureTime = departureTime;
        this.destinationPlace = destinationPlace;
        this.destinationTime = destinationTime;
        this.numberOfFreeSeats = numberOfFreeSeats;
        this.trunkSizeForLuggage = trunkSizeForLuggage;
        this.priceForTravel = priceForTravel;
        this.contactInformation = contactInformation;
    }

    public String toString() {
        return "Offer " + id + "travelDate:" +travelDate + " startingPlace: " + startingPlace + " departureTime: " + departureTime+
                " destinationPlace: " + destinationPlace + "destinationTime:" + destinationTime+ "numberOfFreeSeats:" +
                numberOfFreeSeats+ " trunkSizeForLuggage: " +trunkSizeForLuggage +"priceForTravel:" +priceForTravel
                +"contactInformation:"+ contactInformation;
    }

    public DateData getTravelDate() {
        return travelDate;
    }

    public ContactData getContactInformation() {
        return contactInformation;
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

    public TimeData getDepartureTime() {
        return departureTime;
    }

    public TimeData getDestinationTime() {
        return destinationTime;
    }

    public void setDepartureTime(TimeData departureTime) {
        this.departureTime = departureTime;
    }

    public void setContactInformation(ContactData contactInformation) {
        this.contactInformation = contactInformation;
    }

    public void setDestinationTime(TimeData destinationTime) {
        this.destinationTime = destinationTime;
    }

    public void setDestinationPlace(PlaceData destinationPlace) {
        this.destinationPlace = destinationPlace;
    }

    public void setId(int id) {
        this.id = id;
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
