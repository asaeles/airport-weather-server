package com.crossover.aws;

import com.crossover.aws.dao.Airport;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import static org.apache.commons.beanutils.BeanUtils.setProperty;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

/**
 * A reference implementation for the weather client. Consumers of the REST API
 * can look at WeatherClient to understand API semantics. This existing client
 * populates the REST endpoint with dummy data useful for testing.
 *
 * @author asaeles@gmail.com
 */
public class AirportLoader {

    private static final String BASE_URI = "http://localhost:9090";

    private enum HEADERS {
        id, name, city, country, iata, icao, latitude, longitude, altitude, timezone, dst
    }

    /**
     * end point to supply updates
     */
    private WebTarget collect;

    private AirportLoader() {
        Client client = ClientBuilder.newClient();
        collect = client.target(BASE_URI + "/collect");
    }

    private String pingCollect() {
        WebTarget path = collect.path("/ping");
        Response response = path.request().get();
        return response.readEntity(String.class);
    }

    private String addAirport(Airport airport) {
        WebTarget path = collect.path("/airport/"
                + airport.getIata() + "/"
                + String.valueOf(airport.getLatitude()) + "/"
                + String.valueOf(airport.getLongitude()));
        Response response = path.request().post(Entity.json(airport));
        return response.readEntity(String.class);
    }

    private void load(String fileName)
            throws FileNotFoundException, IOException,
            IllegalAccessException, InvocationTargetException {
        Reader in = new FileReader(fileName);
        Iterable<CSVRecord> records = CSVFormat.RFC4180.withHeader(HEADERS.class).parse(in);
        for (CSVRecord record : records) {
            Airport airport = new Airport();
            for (HEADERS h : HEADERS.values()) {
                setProperty(airport, h.toString(), record.get(h));
            }
            addAirport(airport);
            System.out.print("Added airport: " + airport.getIata() + "\n");
        }
    }

    public static void main(String[] args) {
        try {
            if (args.length == 0) {
                System.out.print("File name not supplied");
                return;
            }
        } catch (NullPointerException e) {
            System.out.print("File name not supplied");
            return;
        }
        AirportLoader al = new AirportLoader();

        String tryPing = al.pingCollect();
        if (tryPing == null || tryPing.equals("")) {
            System.out.print("AWS seems down");
            return;
        }

        try {
            al.load(args[0]);
        } catch (Exception e) {
            System.out.print("Error loadind CSV file" + args[0]);
            return;
        }
        System.out.print("complete");
        System.exit(0);
    }
}
