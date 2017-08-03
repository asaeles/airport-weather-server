package com.crossover.aws.controller;

import com.crossover.aws.dao.Airport;
import com.crossover.aws.dao.AtmosphericInformation;
import com.crossover.aws.dao.DataPoint;
import com.crossover.aws.exception.AirportNotFoundException;
import com.crossover.aws.repository.AirportRepository;
import com.crossover.aws.repository.AtmosphericInformationRepository;
import com.crossover.aws.repository.DataPointRepository;
import com.crossover.aws.representaion.ReadAirport;
import java.util.List;
import java.util.stream.Collectors;
import static org.springframework.beans.BeanUtils.copyProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * The controller containing all the endpoints regarding /collect/ API
 *
 * @author asaeles@gmail.com
 */
@Controller
public class CollectController {

    @Autowired
    private AirportRepository repA;
    @Autowired
    private DataPointRepository repDP;
    @Autowired
    private AtmosphericInformationRepository repAI;

    /**
     * IATA code validator.
     *
     * @param iata the 3 letter Airport code
     *
     * @return Airport if correct otherwise throw exception
     */
    private Airport validateAirport(String iata) {
        return repA.findByIata(iata).orElseThrow(
                () -> new AirportNotFoundException(iata));
    }

    /**
     * A liveliness check for the collection endpoint.
     *
     * @return 1 if the endpoint is alive functioning, 0 otherwise
     */
    @RequestMapping(method = RequestMethod.GET, value = "collect/ping", produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    String pingCollect() {
        return "ready";
    }

    /**
     * Add a new Airport to the known Airports list. If IATA already exists, it
     * updates the Airport parameters.
     *
     * @param iata the 3 letter airport code of the new Airport
     * @param lat the airport's latitude in degrees as a string [-90, 90]
     * @param longString the airport's longitude in degrees as a string [-180,
     * 180]
     * @param jsonAirport (request body) a JSON representation of any required
     * fields from {@link Airport}
     *
     * @return HTTP Response code for the add operation
     */
    @RequestMapping(method = RequestMethod.POST, value = "collect/airport/{iata}/{lat}/{lon}", produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    String createUpdateAirport(@PathVariable String iata, @PathVariable Double lat, @PathVariable Double lon, @RequestBody(required = false) Airport jsonAirport) {
        Airport airport = repA.findByIata(iata).orElse(jsonAirport);
        //As request body is now optional I have to handle null value
        if (airport == null) {
            airport = new Airport();
        } else {
            Long tempId = airport.getId();
            copyProperties(jsonAirport, airport);
            airport.setId(tempId);
        }
        airport.setIata(iata);
        airport.setLatitude(lat);
        airport.setLongitude(lon);
        repA.save(airport);
        return "done";
    }

    /**
     * Remove an airport from the known Airports list.
     *
     * @param iata the 3 letter airport code
     *
     * @return HTTP Response code for the delete operation
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "collect/airport/{iata}", produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    String deleteAirport(@PathVariable String iata) {
        Airport airport = validateAirport(iata);
        if (airport != null) {
            repA.delete(airport);
            return "done";
        } else {
            return iata + " not found";
        }
    }

    /**
     * Retrieve airport data, including latitude and longitude for a particular
     * airport.
     *
     * @param iata the 3 letter airport code
     *
     * @return an HTTP Response with a JSON representation of
     * {@link ReadAirport}
     */
    @RequestMapping(method = RequestMethod.GET, value = "collect/airport/{iata}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    ReadAirport readAirport(@PathVariable String iata) {
        Airport airport = validateAirport(iata);
        ReadAirport retVal = new ReadAirport();
        copyProperties(airport, retVal);
        return retVal;
    }

    /**
     * Return a list of known airports as a JSON formatted list
     *
     * @return HTTP Response code and a JSON formatted list of IATA codes
     */
    @RequestMapping(method = RequestMethod.GET, value = "collect/airports", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    List<String> getListOfAllAirportsIata() {
        List<Airport> allAirports = (List<Airport>) repA.findAll();
        List<String> allIatas = allAirports.stream().map(Airport::getIata).collect(Collectors.toList());
        return allIatas;
    }

    /**
     * Update the Atmospheric Information for a particular Airport and Data
     * Point type with JSON formatted data point information.
     *
     * @param iata the 3 letter Airport code
     * @param type the Data Point type
     * @param jsonDataPoint a JSON dictionary containing mean, first, second,
     * third and count keys
     *
     * @return HTTP Response code
     */
    @RequestMapping(method = RequestMethod.POST, value = "/collect/weather/{iata}/{type}", produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    String updateAtmosphericInformation(@PathVariable String iata, @PathVariable String type, @RequestBody DataPoint jsonDataPoint) {
        // First check IATA and also get Airport object
        Airport airport = validateAirport(iata);
        if (airport != null) {
            // In all cases save the Data Point
            // This will ensure the validity of the Data Point
            // and also becuase the Data Point does not necessarily
            // reflect in the Atmospheric Data
            jsonDataPoint.setAirport(airport);
            jsonDataPoint.setType(type.toUpperCase());
            DataPoint dPoint = repDP.save(jsonDataPoint);

            // Check if there is an Atmospheric Information for this airport
            AtmosphericInformation aInfo = repAI.findByAirport(airport)
                    .orElse(new AtmosphericInformation());
            aInfo.setAirport(airport);

            // Automate the setting of the atmospheric info needed
            // by using setProperty
            aInfo.setDataPoint(dPoint);
            repAI.save(aInfo);
        } else {
            return "Worng IATA";
        }
        return "done";
    }

    /**
     * A useless exit endpoint that shouldn't be there.
     *
     * @return HTTP Response code
     */
    @RequestMapping(method = RequestMethod.GET, value = "/exit", produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    String exit() {
        System.exit(0);
        return "See me if you can :P";
    }
}
