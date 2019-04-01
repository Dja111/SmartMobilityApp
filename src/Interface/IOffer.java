package Interface;

import Datatypes.ContactData;
import Datatypes.DateData;
import Datatypes.PlaceData;
import Datatypes.TimeData;
import Dbadapter.Offer;

import java.util.ArrayList;

/**
* Interface
*
* */
public interface IOffer {

    public void addOffer(DateData travelDate, PlaceData startingPlace, TimeData departureTime, PlaceData destionationPlace, TimeData
            destinationTime, int numberOfFreeSeats, float trunkSizeForLuggage, float priceForTravel, ContactData contactInformation);


    public ArrayList<Offer> availableOffer(DateData travelDate, PlaceData startingPlace, TimeData departureTime, PlaceData destinationPlace, TimeData
            destinationTime, int numberOfFreeSeats, float trunkSizeForLuggage );

    // zuerst als Boolean
    public boolean bookOffer(int id, DateData travelDate,PlaceData startingPlace,TimeData departureTime,TimeData
            destinationTime,PlaceData destinationPlace ,int numberOfFreeSeat,float trunkSizeForLuggage );

    //Todo
    public void removingOffer();
}
