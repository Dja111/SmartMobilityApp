<#include "header.ftl">

<table cellspacing="30">
    <thead>
    <tr>
        <th>PleaseBook</th>
        <th>travelDay</th>
        <th>travelMonth</th>
        <th>travelYear</th>
        <th>streetStart</th>
        <th>townStart</th>
        <th>hourDeparture</th>
        <th>minuteDeparture</th>
        <th>secondDeparture</th>
        <th>streetDestination</th>
        <th>townDestination</th>
        <th>hourDestination</th>
        <th>minuteDestination</th>
        <th>secondDestination</th>
        <th>numberOfFreeSeats</th>
        <th>TrunkSizeForLuggage</th>
        <th>priceForTravel</th>
        <th>contactName</th>
        <th>phoneNumber</th>
    </tr>
    </thead>
	<#list list as result>
     <tbody>

	<tr >

        <form method ="POST" action ="StakeHoldersWebPage">
        <td type ="text" name ="travelDay" value="${result.travelDate.day}">${result.travelDate.day}</td><br>
        <td type ="text" nam="travelMonth" value="${result.travelDate.month}">${result.travelDate.month}</td>
        <td type ="text" name="travelYear" value="${result.travelDate.year}">${result.travelDate.year}</td>
        <td type ="text" name="streetStart" value="${result.startingPlace.street}">${result.startingPlace.street}</td>
        <td type ="text" name="townStart" value="${result.startingPlace.town}">${result.startingPlace.town}</td>
        <td type ="text" name="hourDeparture" value="${result.departureTime.hour}">${result.departureTime.hour}</td>
        <td type ="text" name="minuteDeparture" value="${result.departureTime.minute}">${result.departureTime.minute}</td>
        <td type ="text" name="secondDeparture" value="${result.departureTime.second}">${result.departureTime.second}</td>
        <td type ="text" name="streetEnde" value="${result.destinationPlace.street}">${result.destinationPlace.street}</td>
        <td type ="text" name="townEnde" value="${result.destinationPlace.town}">${result.destinationPlace.town}</td>

        <td type ="text" name="hourDestination" value="${result.destinationTime.hour}">${result.destinationTime.hour}</td>
        <td type ="text" name="minuteDestination" value="${result.destinationTime.minute}">${result.destinationTime.minute}</td>
        <td type ="text" name="secondDestination" value="${result.destinationTime.second}">${result.destinationTime.second}</td>

        <td type ="text" name="numberOfSeats" value="${result.numberOfFreeSeats}">${result.numberOfFreeSeats}</td>
        <td type ="text" name="sizeOfLuggag" value="${result.trunkSizeForLuggage}">${result.trunkSizeForLuggage}</td>
        <td type ="text" name="price" value="${result.priceForTravel}">${result.priceForTravel}</td>
        <td type ="text" name ="name" value="${result.contactInformation.name}">${result.contactInformation.name}</td>
        <td type ="text" nmae ="phoneNumber" value="${result.contactInformation.phoneNumber}">${result.contactInformation.phoneNumber}</td>

            <td><button type="submit" name="search" value="${result.id}" >BookThisOffer</button></td>

        </form>

    </tr>



     </tbody>
	</#list>

</table>

<#include "footer.ftl">