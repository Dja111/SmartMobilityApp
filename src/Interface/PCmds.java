package Interface;

import Datatypes.ContactData;
import Datatypes.DateData;
import Datatypes.PlaceData;
import Datatypes.TimeData;
import Dbadapter.Offer;

import java.util.ArrayList;

public interface PCmds {

    public void createOffer(DateData travelDate, PlaceData startingPlace, TimeData departureTime, PlaceData destionationPlace, TimeData
            destinationTime, int numberOfFreeSeats, float trunkSizeForLuggage, float priceForTravel, ContactData contactInformation);


    public ArrayList<Offer> getSearchingOffer(DateData travelDate, PlaceData startingPlace, TimeData departureTime, PlaceData destinationPlace, TimeData
            destinationTime, int numberOfFreeSeats, float trunkSizeForLuggage );

    // zuerst als Boolean
    public boolean bookOffer(int id);
}
