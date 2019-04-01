package Dbadapter;

import Datatypes.ContactData;
import Datatypes.DateData;
import Datatypes.PlaceData;
import Datatypes.TimeData;
import Interface.IOffer;
import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;



public class DBFacade implements IOffer {


    private static DBFacade instance;

    private DBFacade() {

        try {
            Class.forName("com." + Configuration.getTYPE() + ".jdbc.Driver")
                    .newInstance();
        } catch (InstantiationException | IllegalAccessException
                | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    // singleton

    public static DBFacade getInstance() {
        if (instance == null) {
            instance = new DBFacade();
        }
        return instance;
    }

    @Override
    public void addOffer(DateData travelDate, PlaceData startingPlace, TimeData departureTime,
                         PlaceData destionationPlace, TimeData destinationTime, int numberOfFreeSeats,
                         float trunkSizeForLuggage, float priceForTravel, ContactData contactInformation) {

        String sqlInsert = "INSERT INTO Offer (travelDay,travelMonth,travelYear," +
                "streetStart,townStart,hourDeparture,minuteDeparture,secondDeparture," +
                "streetEnde,townEnde" +
                ",hourDestination,minuteDestination,secondDestination,numberOfFreeSeats," +
                "trunkSizeForLuggage,priceForTravel,contactName,phoneNumber,emailAddress)" +
                " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        int status = 0;

        try (Connection connection = DriverManager.getConnection(
                "jdbc:" + Configuration.getTYPE() + "://"
                        + Configuration.getSERVER() + ":"
                        + Configuration.getPORT() + "/"
                        + Configuration.getDATABASE(), Configuration.getUSER(),
                Configuration.getPASSWORD())){
            try (PreparedStatement ps = connection.prepareStatement(sqlInsert)){
                ps.setInt(1,travelDate.getDay());
                ps.setInt(2,travelDate.getMonth());
                ps.setInt(3,travelDate.getYear());
                ps.setString(4,startingPlace.getStreet());
                ps.setString(5,startingPlace.getTown());
                ps.setInt(6,departureTime.getHour());
                ps.setInt(7,departureTime.getMinute());
                ps.setInt(8,departureTime.getSecond());
                ps.setString(9,destionationPlace.getStreet());
                ps.setString(10,destionationPlace.getTown());
                ps.setInt(11,destinationTime.getHour());
                ps.setInt(12,destinationTime.getMinute());
                ps.setInt(13,destinationTime.getSecond());
                ps.setInt(14,numberOfFreeSeats);
                ps.setFloat(15,trunkSizeForLuggage);
                ps.setFloat(16,priceForTravel);
                ps.setString(17,contactInformation.getName());
                ps.setInt(18,contactInformation.getPhoneNumber());
                ps.setString(19,contactInformation.getEmailAddress());
                status = ps.executeUpdate();
            }catch (SQLException e){
                e.printStackTrace();
            }catch (Exception e){
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public ArrayList<Offer> availableOffer(DateData travelDate, PlaceData startingPlace, TimeData departureTime, PlaceData destinationPlace, TimeData destinationTime, int numberOfFreeSeats, float trunkSizeForLuggage) {

        // starting plate, departure time, destination place, destination time
        ArrayList<Offer> result = new ArrayList<>();

        DateData travelDateee = travelDate;
        PlaceData startingPlaceee = startingPlace;
        TimeData departureTimeee = departureTime;
        PlaceData destinationPlaceee = destinationPlace;
        TimeData destinationTimeee = destinationTime;
        int numberOfFreeSeatsss = numberOfFreeSeats;
        float trunkSizeForLuggagess = trunkSizeForLuggage;


        String sqlSearch = "SELECT * FROM Offer WHERE streetStart = ? AND townStart = ? AND hourDeparture = ? AND minuteDeparture = ? AND secondDeparture = ? AND " +
                "streetEnde = ? AND townEnde = ? AND hourDestination = ? AND minuteDestination = ? AND secondDestination = ? and numberOfFreeSeats <= ?";

        try(Connection connection = DriverManager.getConnection(
                "jdbc:" + Configuration.getTYPE() + "://"
                        + Configuration.getSERVER() + ":"
                        + Configuration.getPORT() + "/"
                        + Configuration.getDATABASE(), Configuration.getUSER(),
                Configuration.getPASSWORD())){

            try(PreparedStatement ps = connection.prepareStatement(sqlSearch) ) {

                ps.setString(1,startingPlaceee.getStreet());
                ps.setString(2,startingPlaceee.getTown());
                ps.setInt(3,departureTimeee.getHour());
                ps.setInt(4,departureTimeee.getMinute());
                ps.setInt(5,departureTimeee.getSecond());
                ps.setString(6,destinationPlaceee.getStreet());
                ps.setString(7,destinationPlaceee.getTown());
                ps.setInt(8,destinationTimeee.getHour());
                ps.setInt(9,destinationTimeee.getMinute());
                ps.setInt(10,destinationTimeee.getSecond());
                ps.setInt(11,numberOfFreeSeats);

                    try(ResultSet rs = ps.executeQuery()) {
                        while (rs.next()){

                            DateData travelDatee = new DateData(rs.getInt(2),rs.getInt(3),rs.getInt(4));

                            PlaceData startingPlacee = new PlaceData(rs.getString(5),rs.getString(6));

                            TimeData departureTimee = new TimeData(rs.getInt(7),rs.getInt(8),rs.getInt(9));

                            PlaceData destinationPlacee = new PlaceData(rs.getString(10),rs.getString(11));

                            TimeData destinationTimee = new TimeData(rs.getInt(12),rs.getInt(13),rs.getInt(14));

                            int numberOfFreeSeatss = rs.getInt(15); float trunkSizeForLuggages =rs.getFloat(16);

                            float priceForTravels = rs.getFloat(17);ContactData contactInformation = new ContactData(rs.getString(18),rs.getInt(19),rs.getString(20));

                            Offer offerResult = new Offer(rs.getInt(1),travelDatee,startingPlacee,departureTimee,destinationPlacee,destinationTimee,numberOfFreeSeatss,trunkSizeForLuggages,priceForTravels,contactInformation);

                            result.add(offerResult);


                        }
                    }catch (SQLException e) {
                        e.printStackTrace();
                    }
            }catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean bookOffer(int ids, DateData travelDate, PlaceData startingPlace, TimeData departureTime, TimeData destinationTime,PlaceData destinationPlace, int numberOfFreeSeat, float trunkSizeForLuggage) {
        boolean result ;

        Offer o = null;

        String FromGroup13 ="aurel.donfack@yahoo.fr";

        String receiver = "";

        String host ="localhost";


        String sqlQuery = "select * from Offer where id =?";

        String sqlInsertNewBook = "INSERT INTO book (travelDay,travelMonth,travelYear,streetStart,townStart,hourDeparture,minuteDeparture,secondDeparture,streetEnde,townEnde" +
                ",hourDestination,minuteDestination,secondDestination,numberOfFreeSeats,trunkSizeForLuggage,offerId) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";


        String sqlUpdateOffer = "update Offer set numberOfFreeSeats =? where id=?  ";

        String sqlDeleleOffer = "delete from Offer where id=?";

        try (Connection connection = DriverManager.getConnection(
                "jdbc:" + Configuration.getTYPE() + "://"
                        + Configuration.getSERVER() + ":"
                        + Configuration.getPORT() + "/"
                        + Configuration.getDATABASE(), Configuration.getUSER(),
                Configuration.getPASSWORD())){

            try(PreparedStatement psQuery = connection.prepareStatement(sqlQuery);
                PreparedStatement psInsert = connection.prepareStatement(sqlInsertNewBook);
                PreparedStatement psDelete = connection.prepareStatement(sqlDeleleOffer);
                PreparedStatement psUpdate = connection.prepareStatement(sqlUpdateOffer)

                ){

                psQuery.setInt(1,ids);


                try (ResultSet rs = psQuery.executeQuery()){
                    if(rs.next()){

                        DateData travelDatee = new DateData(rs.getInt(2),rs.getInt(3),rs.getInt(4));

                        PlaceData startingPlacee = new PlaceData(rs.getString(5),rs.getString(6));

                        TimeData departureTimee = new TimeData(rs.getInt(7),rs.getInt(8),rs.getInt(9));

                        PlaceData destinationPlacee = new PlaceData(rs.getString(10),rs.getString(11));

                        TimeData destinationTimee = new TimeData(rs.getInt(12),rs.getInt(13),rs.getInt(14));

                        int numberOfFreeSeatss = rs.getInt(15); float trunkSizeForLuggages =rs.getFloat(16);

                        float priceForTravels = rs.getFloat(17);ContactData contactInformation = new ContactData(rs.getString(18),rs.getInt(19),rs.getString(20));

                       o = new Offer(rs.getInt(1),travelDatee,startingPlacee,departureTimee,destinationPlacee,destinationTimee,numberOfFreeSeatss,trunkSizeForLuggages,priceForTravels,contactInformation);


                    }

                }

                if(o.getNumberOfFreeSeats()== numberOfFreeSeat ){

                    psInsert.setInt(1,travelDate.getDay());
                    psInsert.setInt(2,travelDate.getMonth());
                    psInsert.setInt(3,travelDate.getYear());
                    psInsert.setString(4,startingPlace.getStreet());
                    psInsert.setString(5,startingPlace.getTown());
                    psInsert.setInt(6,departureTime.getHour());
                    psInsert.setInt(7,departureTime.getMinute());
                    psInsert.setInt(8,departureTime.getSecond());
                    psInsert.setString(9,destinationPlace.getStreet());
                    psInsert.setString(10,destinationPlace.getTown());
                    psInsert.setInt(11,destinationTime.getHour());
                    psInsert.setInt(12,destinationTime.getMinute());
                    psInsert.setInt(13,destinationTime.getSecond());
                    psInsert.setInt(14,numberOfFreeSeat);
                    psInsert.setFloat(15,trunkSizeForLuggage);
                    psInsert.setInt(16,o.getId());

                    psInsert.executeUpdate();

                    try{
                        psDelete.setInt(1,ids);
                        psDelete.executeUpdate();
                        return true;
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }else if(o.getNumberOfFreeSeats()> numberOfFreeSeat){

                    psInsert.setInt(1,travelDate.getDay());
                    psInsert.setInt(2,travelDate.getMonth());
                    psInsert.setInt(3,travelDate.getYear());
                    psInsert.setString(4,startingPlace.getStreet());
                    psInsert.setString(5,startingPlace.getTown());
                    psInsert.setInt(6,departureTime.getHour());
                    psInsert.setInt(7,departureTime.getMinute());
                    psInsert.setInt(8,departureTime.getSecond());
                    psInsert.setString(9,destinationPlace.getStreet());
                    psInsert.setString(10,destinationPlace.getTown());
                    psInsert.setInt(11,destinationTime.getHour());
                    psInsert.setInt(12,destinationTime.getMinute());
                    psInsert.setInt(13,destinationTime.getSecond());
                    psInsert.setInt(14,numberOfFreeSeat);
                    psInsert.setFloat(15,trunkSizeForLuggage);
                    psInsert.setInt(16,o.getId());
                    psInsert.executeUpdate();

                    try{

                        numberOfFreeSeat = o.getNumberOfFreeSeats()-numberOfFreeSeat;
                        psUpdate.setInt(1,numberOfFreeSeat);
                        psUpdate.setInt(2,o.getId());
                        psUpdate.executeUpdate();
                        return true;
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }



        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }



    @Override
    public void removingOffer() {

        Date date = new Date( );

        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int month = localDate.getMonthValue();
        int year = localDate.getYear();
        int day = localDate.getDayOfMonth();

        String deleteSql ="delete from Offer where travelYear < ? and travelMonth <? and travelDay <?";

        try (Connection connection = DriverManager.getConnection(
                "jdbc:" + Configuration.getTYPE() + "://"
                        + Configuration.getSERVER() + ":"
                        + Configuration.getPORT() + "/"
                        + Configuration.getDATABASE(), Configuration.getUSER(),
                Configuration.getPASSWORD())){
            try (PreparedStatement psDelete = connection
                    .prepareStatement(deleteSql)){
                psDelete.setInt(1,year);
                psDelete.setInt(2,month);
                psDelete.setInt(3,day);
                psDelete.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public boolean checkOfferById(int oid) {

        // Declare necessary SQL query.
        String queryHO = "SELECT * FROM Offer WHERE id=?";

        // query data.
        try (Connection connection = DriverManager.getConnection(
                "jdbc:" + Configuration.getTYPE() + "://"
                        + Configuration.getSERVER() + ":"
                        + Configuration.getPORT() + "/"
                        + Configuration.getDATABASE(), Configuration.getUSER(),
                Configuration.getPASSWORD())) {
            try (PreparedStatement psSelect = connection
                    .prepareStatement(queryHO)) {
                psSelect.setInt(1, oid);
                try (ResultSet rs = psSelect.executeQuery()) {
                    return rs.next();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Offer getOfferForBook(int oId){

        Offer offer = null;

        String queryHO = "SELECT * FROM Offer WHERE id=?";

        // query data.
        try (Connection connection = DriverManager.getConnection(
                "jdbc:" + Configuration.getTYPE() + "://"
                        + Configuration.getSERVER() + ":"
                        + Configuration.getPORT() + "/"
                        + Configuration.getDATABASE(), Configuration.getUSER(),
                Configuration.getPASSWORD())) {
            try (PreparedStatement psSelect = connection
                    .prepareStatement(queryHO)) {

                psSelect.setInt(1, oId);

                try (ResultSet rs = psSelect.executeQuery()) {
                    while (rs.next()){
                        DateData travelDatee = new DateData(rs.getInt(2),rs.getInt(3),rs.getInt(4));

                        PlaceData startingPlacee = new PlaceData(rs.getString(5),rs.getString(6));

                        TimeData departureTimee = new TimeData(rs.getInt(7),rs.getInt(8),rs.getInt(9));

                        PlaceData destinationPlacee = new PlaceData(rs.getString(10),rs.getString(11));

                        TimeData destinationTimee = new TimeData(rs.getInt(12),rs.getInt(13),rs.getInt(14));

                        int numberOfFreeSeatss = rs.getInt(15); float trunkSizeForLuggages =rs.getFloat(16);

                        float priceForTravels = rs.getFloat(17);ContactData contactInformation = new ContactData(rs.getString(18),rs.getInt(19),rs.getString(20));

                       offer = new Offer(rs.getInt(1),travelDatee,startingPlacee,departureTimee,destinationPlacee,destinationTimee,numberOfFreeSeatss,trunkSizeForLuggages,priceForTravels,contactInformation);


                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  offer;
    }
}
