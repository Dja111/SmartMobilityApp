package Testing;

import Datatypes.DateData;
import Datatypes.PlaceData;
import Datatypes.TimeData;
import Dbadapter.Configuration;
import Dbadapter.DBFacade;
import Dbadapter.Offer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import java.sql.*;
import java.util.ArrayList;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DBFacade.class)
public class DBFacadeTest {

    private Connection stubCon;

    private String sqlSearch;

    private String sqlInsert;

    private  PreparedStatement ps;

    private  PreparedStatement psInsert;

    private ResultSet rs;

    private ResultSet rss;

    private  String sqlQuery ;

    private  String sqlInsertNewBook;

    private  String queryHO;

    private  String sqlDeleleOffer;

    private  String queryHO1;


    @Before
    public void setUp(){
        PowerMockito.mockStatic(DriverManager.class);

        sqlQuery = "select * from Offer where id =?";

        sqlDeleleOffer = "delete from Offer where id=?";

         queryHO = "SELECT * FROM Offer WHERE id=?";

        queryHO1 = "SELECT * FROM Offer WHERE id=?";

        sqlInsertNewBook = "INSERT INTO book (travelDay,travelMonth,travelYear,streetStart,townStart,hourDeparture,minuteDeparture,secondDeparture,streetEnde,townEnde" +
                ",hourDestination,minuteDestination,secondDestination,numberOfFreeSeats,trunkSizeForLuggage,offerId) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        sqlSearch = "SELECT * FROM Offer WHERE streetStart = ? AND townStart = ? AND hourDeparture = ? AND minuteDeparture = ? AND secondDeparture = ? AND " +
                "streetEnde = ? AND townEnde = ? AND hourDestination = ? AND minuteDestination = ? AND secondDestination = ? and numberOfFreeSeats <= ?";

        sqlInsert = "INSERT INTO Offer (travelDay,travelMonth,travelYear,streetStart,townStart,hourDeparture,minuteDeparture,secondDeparture,streetEnde,townEnde" +
                ",hourDestination,minuteDestination,secondDestination,numberOfFreeSeats,trunkSizeForLuggage,priceForTravel,contactName,phoneNumber,emailAddress) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        ps = mock(PreparedStatement.class);

        psInsert = mock(PreparedStatement.class);

        rs = mock(ResultSet.class);

        rss = mock(ResultSet.class);



        try{
            stubCon = mock(Connection.class);

            PowerMockito.when(DriverManager.getConnection(
                    "jdbc:" + Configuration.getTYPE() + "://"
                            + Configuration.getSERVER() + ":"
                            + Configuration.getPORT() + "/"
                            + Configuration.getDATABASE(), Configuration.getUSER(),
                    Configuration.getPASSWORD())).thenReturn(stubCon);

            when(stubCon.prepareStatement(sqlSearch)).thenReturn(ps);
            when(ps.executeQuery()).thenReturn(rs);

            when(stubCon.prepareStatement(sqlInsertNewBook)).thenReturn(ps);
            when(ps.executeUpdate()).thenReturn(1);

            when(stubCon.prepareStatement(sqlDeleleOffer)).thenReturn(ps);
            when(ps.executeUpdate()).thenReturn(1);

            when(stubCon.prepareStatement(queryHO)).thenReturn(ps);
            when(ps.executeQuery()).thenReturn(rss);

            when(stubCon.prepareStatement(queryHO)).thenReturn(ps);
            when(ps.executeQuery()).thenReturn(rs);


            when(rss.next()).thenReturn(true);

            //Setting up return values for method

            when(rs.next()).thenReturn(true).thenReturn(false);
            when(rs.getInt(1)).thenReturn(1);
            when(rs.getInt(2)).thenReturn(1);
            when(rs.getInt(3)).thenReturn(1);
            when(rs.getInt(4)).thenReturn(2019);
            when(rs.getString(5)).thenReturn("Steinmetz");
            when(rs.getString(6)).thenReturn("Essen");
            when(rs.getInt(7)).thenReturn(12);
            when(rs.getInt(8)).thenReturn(1);
            when(rs.getInt(9)).thenReturn(1);
            when(rs.getString(10)).thenReturn("veleda");
            when(rs.getString(11)).thenReturn("Essen");
            when(rs.getInt(12)).thenReturn(13);
            when(rs.getInt(13)).thenReturn(1);
            when(rs.getInt(14)).thenReturn(1);
            when(rs.getInt(15)).thenReturn(6);
            when(rs.getFloat(16)).thenReturn(6.1f);
            when(rs.getFloat(17)).thenReturn(1f);
            when(rs.getString(18)).thenReturn("falz");
            when(rs.getInt(19)).thenReturn(123456);
            when(rs.getString(20)).thenReturn("aur@y.fr");




        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*@Test
    public  void TestAdd() throws SQLException {
        DateData travelDate = new DateData(1,1,2019);
        PlaceData startingPlace = new PlaceData("Steinmetz","Essen");
        TimeData departureTime = new TimeData(12,1,1);
        PlaceData destinationPlace = new PlaceData("veleda","Essen");
        TimeData destinationTime = new TimeData(13,1,1);
        int numberOfFreeSeat = 6;
        float trunkSizeForLuggage = 6.1f;
        ContactData contactData = new ContactData("test",1012212,"au@yah.de");

        DBFacade.getInstance().addOffer(travelDate,startingPlace,departureTime,destinationPlace,destinationTime,numberOfFreeSeat,trunkSizeForLuggage,10,contactData);

        verify(stubCon,times(1)).prepareStatement(sqlInsert);
        verify(psInsert,times(1)).executeUpdate();
    }*/



    @Test
    public  void TestavailableOffer(){

        DateData travelDate = new DateData(1,1,2019);
        PlaceData startingPlace = new PlaceData("Steinmetz","Essen");
        TimeData departureTime = new TimeData(12,1,1);
        PlaceData destinationPlace = new PlaceData("veleda","Essen");
        TimeData destinationTime = new TimeData(13,1,1);
        int numberOfFreeSeat = 6;
        float trunkSizeForLuggage = 6.1f;

        ArrayList<Offer> rep = DBFacade.getInstance().availableOffer(travelDate,startingPlace,departureTime,destinationPlace,destinationTime,numberOfFreeSeat,trunkSizeForLuggage);

        try{
            verify(stubCon,times(1)).prepareStatement(sqlSearch);
            verify(ps,times(1)).executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        assertTrue(rep.size()==1);
    }




    @Test
    public void TestAvailableOfferEmpty(){

        DateData travelDate = new DateData(0,0,2010);
        PlaceData startingPlace = new PlaceData("Steinmetz","Essen");
        TimeData departureTime = new TimeData(0,0,1);
        PlaceData destinationPlace = new PlaceData("veleda","Essen");
        TimeData destinationTime = new TimeData(13,1,1);
        int numberOfFreeSeat = 6;
        float trunkSizeForLuggage = 6.1f;

        ArrayList<Offer> rep = DBFacade.getInstance().availableOffer(travelDate,startingPlace,departureTime,destinationPlace,destinationTime,numberOfFreeSeat,trunkSizeForLuggage);

        try{
            verify(stubCon,times(1)).prepareStatement(sqlSearch);
            verify(ps,times(1)).executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        assertTrue(rep.size()==1);
    }

    @Test
    public  void bookTest(){

        int ids = 1;
        DateData travelDate = new DateData(1,1,2019);
        PlaceData startingPlace = new PlaceData("Steinmetz","Essen");
        TimeData departureTime = new TimeData(12,1,1);
        PlaceData destinationPlace = new PlaceData("veleda","Essen");
        TimeData destinationTime = new TimeData(13,1,1);
        int numberOfFreeSeat = 6;
        float trunkSizeForLuggage = 6.1f;

        boolean res = DBFacade.getInstance().bookOffer(ids,travelDate,startingPlace,departureTime,destinationTime,destinationPlace,numberOfFreeSeat,trunkSizeForLuggage);

        try{
            verify(stubCon,times(1)).prepareStatement(queryHO);
            verify(ps,times(1)).executeQuery();

            verify(stubCon,times(1)).prepareStatement(sqlInsertNewBook);
            verify(ps,times(1)).executeUpdate();

            verify(stubCon,times(1)).prepareStatement(sqlDeleleOffer);
            verify(ps,times(1)).executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        assertTrue(res);



    }

    @Test
    public  void checkOfferTest(){

        int id = 1;

        boolean res = DBFacade.getInstance().checkOfferById(id);

        try{
            verify(stubCon,times(1)).prepareStatement(queryHO);
            verify(ps,times(1)).executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        assertTrue(res);

    }

    @Test
    public  void getOfferByIdTest(){
        int id = 1;

        Offer offer = null;
        DateData travelDate = new DateData(1,1,2019);
        PlaceData startingPlace = new PlaceData("Steinmetz","Essen");
        TimeData departureTime = new TimeData(12,1,1);
        PlaceData destinationPlace = new PlaceData("veleda","Essen");
        TimeData destinationTime = new TimeData(13,1,1);
        int numberOfFreeSeat = 6;
        float trunkSizeForLuggage = 6.1f;

         offer = DBFacade.getInstance().getOfferForBook(1);

        try{
            verify(stubCon,times(1)).prepareStatement(queryHO);
            verify(ps,times(1)).executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        assertEquals(offer.getId(),1);
    }

}
