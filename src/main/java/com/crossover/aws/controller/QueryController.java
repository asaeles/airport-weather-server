package com.crossover.aws.controller;

import com.crossover.aws.dao.Airport;
import com.crossover.aws.dao.AirportRequestFrequency;
import com.crossover.aws.dao.AtmosphericInformation;
import com.crossover.aws.dao.RadiusRequestFrequency;
import com.crossover.aws.exception.AirportNotFoundException;
import com.crossover.aws.repository.AirportRepository;
import com.crossover.aws.repository.AirportRequestFrequencyRepository;
import com.crossover.aws.repository.AtmosphericInformationRepository;
import com.crossover.aws.repository.RadiusRequestFrequencyRepository;
import com.crossover.aws.representaion.ReadAtmosphericInformation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.springframework.beans.BeanUtils.copyProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * The controller containing all the endpoints regarding /query/ API
 *
 * @author asaeles@gmail.com
 */
@Controller
//@RequestMapping(value = "/")
public class QueryController {

    @Autowired
    private AirportRepository repA;
    @Autowired
    private AtmosphericInformationRepository repAI;
    @Autowired
    private AirportRequestFrequencyRepository repARF;
    @Autowired
    private RadiusRequestFrequencyRepository repRRF;

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
     * Retrieves health and status information for the the query API. Returns
     * information about how many Data Points currently available, the frequency
     * of requests for each IATA code and the frequency of requests for each
     * radius.
     *
     * @return a JSON formatted dictionary with health information.
     */
    @RequestMapping(method = RequestMethod.GET, value = "query/ping", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    Map<String, Object> pingQuery() {
        Map<String, Object> retval = new HashMap<>();

        //Number of Airports having any Data Points within the 24h
        long count = repAI.countByHasDataAndLastUpdateTimeGreaterThan(true,
                System.currentTimeMillis() - 86400000);
        retval.put("datasize", count);
        Map<String, Double> freq = new HashMap<>();
        //Number of query hits on each Airport divided by total number
        // of queried Airports
        count = repARF.count();
        if (count > 0) {
            for (Airport airport : repA.findAll()) {
                double frac = repARF.findByAirport(airport)
                        .orElse(new AirportRequestFrequency()).getFrquency() / count;
                freq.put(airport.getIata(), frac);
            }
        } else {
            freq = null;
        }
        retval.put("iata_freq", freq);

        int[] hist = new int[10];
        for (RadiusRequestFrequency rRF : repRRF.findAll()) {
            int i = ((Double) rRF.getRadius()).intValue() % 10;
            hist[i] += rRF.getFrquency();
        }
        retval.put("radius_freq", hist);
        return retval;
    }

    /**
     * Retrieves the most up to date Atmospheric Information from the given
     * airport and other Airports in the given radius.
     *
     * @param iata the three letter airport code
     * @param radius the radius, in km, from which to collect weather data
     *
     * @return a JSON formatted dictionary containing a list of
     * {@link AtmosphericInformation} from the requested airport and other
     * Airports in the given radius
     */
    @RequestMapping(method = RequestMethod.GET, value = "/query/weather/{iata}/{radius}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    List<ReadAtmosphericInformation> readAtmosphericInformation(@PathVariable String iata, @PathVariable Double radius) {
        //Increase Radius frequency
        RadiusRequestFrequency rRF = repRRF.findByRadius(radius)
                .orElse(new RadiusRequestFrequency());
        rRF.setRadius(radius);
        rRF.inc();
        repRRF.save(rRF);
        // First check IATA and also get Airport object
        Airport airport = validateAirport(iata);
        if (airport != null) {
            //Increase Airport frequency
            AirportRequestFrequency aRF = repARF.findByAirport(airport)
                    .orElse(new AirportRequestFrequency());
            aRF.setAirport(airport);
            aRF.inc();
            repARF.save(aRF);
            //Prepare results
            List<ReadAtmosphericInformation> retval = new ArrayList<>();
            ReadAtmosphericInformation rAI = new ReadAtmosphericInformation();
            //If radius is zero just return the data for the supplied airport only
            if (radius == 0) {
                copyProperties(repAI.findByAirport(airport)
                        .orElse(new AtmosphericInformation()), rAI);
                retval.add(rAI);
                return retval;
            }
            //Otherwise check on the distnce between all the airports and the
            // supplied one if smaller than radius the add its info
            for (Airport a : repA.findAll()) {
                if (airport.distanceTo(a) <= radius) {
                    rAI = new ReadAtmosphericInformation();
                    copyProperties(repAI.findByAirport(a)
                            .orElse(new AtmosphericInformation()), rAI);
                    retval.add(rAI);
                }
            }
            return retval;
        } else {
            return null;
        }
    }
}
