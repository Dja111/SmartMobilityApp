package Application;

import Datatypes.ContactData;
import Datatypes.DateData;
import Datatypes.PlaceData;
import Datatypes.TimeData;
import Dbadapter.DBFacade;
import Dbadapter.Offer;
import Interface.PCmds;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class SMApplication implements PCmds {


    private  static  SMApplication instance;

    public  static  SMApplication getInstance(){
        if(instance == null){
            instance = new SMApplication();
        }
        return  instance;
    }


    @Override
    public void createOffer(DateData travelDate, PlaceData startingPlace, TimeData departureTime, PlaceData destionationPlace,
                            TimeData destinationTime, int numberOfFreeSeats, float trunkSizeForLuggage, float priceForTravel,
                            ContactData contactInformation) {
        try {

            DBFacade.getInstance().addOffer(travelDate,startingPlace,departureTime,
                    destionationPlace,destinationTime,numberOfFreeSeats,
                    trunkSizeForLuggage,priceForTravel,contactInformation);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Offer> getSearchingOffer(DateData travelDate, PlaceData startingPlace,
                                              TimeData departureTime, PlaceData destinationPlace,
                                              TimeData destinationTime, int numberOfFreeSeats, float trunkSizeForLuggage) {

        ArrayList<Offer> result = new ArrayList<>();

        try{

            result = DBFacade.getInstance().availableOffer(travelDate,startingPlace,departureTime,
                    destinationPlace,destinationTime,numberOfFreeSeats,trunkSizeForLuggage);


        }catch (Exception e){
            e.printStackTrace();

        }
        return result;
    }

    @Override
    public boolean bookOffer(int id) {

        //
        assert preOfferAvailable(id) : "Precondition not satisfied";

        Offer offer = getOfferForBooking(id);

        boolean result ;

        Date date = new Date( );

        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int month = localDate.getMonthValue();
        int year = localDate.getYear();
        int day = localDate.getDayOfMonth();

        LocalDateTime now = LocalDateTime.now();
        int hour = now.getHour();
        int minute = now.getMinute();

        if(offer.getTravelDate().getYear()<year){
            return false;
        }else if(offer.getTravelDate().getMonth()<month){
            return false;
        }else if(offer.getTravelDate().getDay()<day){
            return false;

        }else{
                if(offer.getDepartureTime().getHour()<hour){
                    return false;
                }else{
                     result = DBFacade.getInstance().bookOffer(id, offer.getTravelDate(),offer.getStartingPlace(),offer.getDepartureTime(),
                            offer.getDestinationTime(),offer.getDestinationPlace(),offer.getNumberOfFreeSeats(),offer.getTrunkSizeForLuggage());
                     return result;
                }

        }
    }

    private boolean preOfferAvailable(int hid) {
        return DBFacade.getInstance().checkOfferById(hid);
    }

    private Offer getOfferForBooking(int oId){

        Offer offer = DBFacade.getInstance().getOfferForBook(oId);

        return offer;
    }

    public void deleteOffer(){
        DBFacade.getInstance().removingOffer();
    }


}
