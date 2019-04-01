package Servlets;

import Application.SMApplication;
import Datatypes.ContactData;
import Datatypes.DateData;
import Datatypes.PlaceData;
import Datatypes.TimeData;
import Dbadapter.Offer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class StakeHoldersWebPage extends HttpServlet {



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(request.getParameter("search").equals("addOffer")){
            try{
                DateData travelDate = new DateData();
                travelDate.setDay(Integer.parseInt((request.getParameter("travelDay"))));
                travelDate.setMonth(Integer.parseInt(request.getParameter("travelMonth")));
                travelDate.setYear(Integer.parseInt(request.getParameter("travelYear")));


                PlaceData startingPlace = new PlaceData();
                startingPlace.setStreet(request.getParameter("streetStart"));
                startingPlace.setTown(request.getParameter("townStart"));

                TimeData  departureTime = new TimeData();
                departureTime.setHour(Integer.parseInt(request.getParameter("hourDeparture")));
                departureTime.setMinute(Integer.parseInt(request.getParameter("minuteDeparture")));
                departureTime.setSecond(Integer.parseInt(request.getParameter("secondDeparture")));

                PlaceData destinationPlace = new PlaceData();
                destinationPlace.setStreet(request.getParameter("streetEnde"));
                destinationPlace.setTown(request.getParameter("townEnde"));

                TimeData  destinationTime = new TimeData();
                destinationTime.setHour(Integer.parseInt(request.getParameter("hourDestination")));
                destinationTime.setMinute(Integer.parseInt(request.getParameter("minuteDestination")));
                destinationTime.setSecond(Integer.parseInt(request.getParameter("secondDestination")));


                ContactData contactInformation = new ContactData();
                contactInformation.setName(request.getParameter("contactName"));
                contactInformation.setPhoneNumber(Integer.parseInt(request.getParameter("phoneNumber")));
                contactInformation.setEmailAddress(request.getParameter("emailAddress"));


                int numberOfSeat =  Integer.parseInt(request.getParameter("numberOfSeats"));
                float price = Float.valueOf(request.getParameter("price")) ;
                float lug = Float.valueOf(request.getParameter("sizeOfLuggag")) ;


                SMApplication.getInstance().createOffer(travelDate,startingPlace,departureTime,destinationPlace,destinationTime
                        ,numberOfSeat,lug
                        ,price,contactInformation );

                String resultat = "Das neue Offer wurde hinzugefügt";
                request.setAttribute("reponse", resultat);

                request.getRequestDispatcher("/Tamplates/merken.ftl").forward(request, response);


            }
            catch (NumberFormatException e) {
                request.getRequestDispatcher("/Tamplates/fail.flt").forward(request, response);
                e.printStackTrace();
            }

        }else if(!request.getParameter("search").equals("addOffer")){

            try {
                int id = Integer.parseInt(request.getParameter("search"));

                System.out.println(id);

                boolean Result = SMApplication.getInstance().bookOffer(id);

                    if(Result == true){
                        String feelback ="book is Successfully";

                        request.setAttribute("reponse", feelback );
                        request.getRequestDispatcher("/Tamplates/bookFeelback.ftl").forward(request, response);


                    }else{
                        String feelback ="book was not Successfully";
                        request.setAttribute("reponse", feelback );
                        request.getRequestDispatcher("/Tamplates/bookFeelback.ftl").forward(request, response);
                    }

            }catch (Exception e){
                request.getRequestDispatcher("/Tamplates/fail.flt").forward(request, response);
                e.printStackTrace();
            }

        }


        doGet(request, response);

    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(request.getParameter("search").equals("Search")) {
            try {
                request.getRequestDispatcher("/Tamplates/searchOffer.ftl").forward(request, response);
            }catch (Exception e){
                e.printStackTrace();
            }
        }else if(request.getParameter("search").equals("SearchOffer")){

            try {

                DateData travelDate = new DateData();
                travelDate.setDay(Integer.parseInt((request.getParameter("travelDay"))));
                travelDate.setMonth(Integer.parseInt(request.getParameter("travelMonth")));
                travelDate.setYear(Integer.parseInt(request.getParameter("travelYear")));


                PlaceData startingPlace = new PlaceData();
                startingPlace.setStreet(request.getParameter("streetStart"));
                startingPlace.setTown(request.getParameter("townStart"));

                TimeData  departureTime = new TimeData();
                departureTime.setHour(Integer.parseInt(request.getParameter("hourDeparture")));
                departureTime.setMinute(Integer.parseInt(request.getParameter("minuteDeparture")));
                departureTime.setSecond(Integer.parseInt(request.getParameter("secondDeparture")));

                PlaceData destinationPlace = new PlaceData();
                destinationPlace.setStreet(request.getParameter("streetEnde"));
                destinationPlace.setTown(request.getParameter("townEnde"));

                TimeData  destinationTime = new TimeData();
                destinationTime.setHour(Integer.parseInt(request.getParameter("hourDestination")));
                destinationTime.setMinute(Integer.parseInt(request.getParameter("minuteDestination")));
                destinationTime.setSecond(Integer.parseInt(request.getParameter("secondDestination")));


                int numberOfSeat =  Integer.parseInt(request.getParameter("numberOfSeats"));
                float lug = Float.valueOf(request.getParameter("sizeOfLuggag")) ;


                ArrayList<Offer>  result = SMApplication.getInstance().getSearchingOffer(travelDate,startingPlace,departureTime,destinationPlace,destinationTime
                        ,numberOfSeat,lug);

                request.setAttribute("list",result);

                request.getRequestDispatcher("/Tamplates/availableOffer.ftl").forward(request,response);

            }catch (Exception e){
                request.getRequestDispatcher("/Tamplates/failrepresentation.flt").forward(request, response);
                e.printStackTrace();
            }
        }else{
            System.out.println("Error");
        }

    }
}
