CREATE CACHED TABLE IF NOT EXISTS airport (id BIGINT generated BY DEFAULT AS IDENTITY, altitude DOUBLE, city VARCHAR(255), country VARCHAR(255), dst VARCHAR(255), iata VARCHAR(5) NOT NULL, icao VARCHAR(4), latitude DOUBLE NOT NULL, longitude DOUBLE NOT NULL, name VARCHAR(255), timezone DOUBLE, PRIMARY KEY (id));
CREATE CACHED TABLE IF NOT EXISTS airport_request_frequency (id BIGINT generated BY DEFAULT AS IDENTITY, frquency INTEGER NOT NULL, airport_id BIGINT, PRIMARY KEY (id));
CREATE CACHED TABLE IF NOT EXISTS atmospheric_information (id BIGINT generated BY DEFAULT AS IDENTITY, has_data BOOLEAN NOT NULL, last_update_time BIGINT NOT NULL, airport_id BIGINT, cloud_cover_id BIGINT, humidity_id BIGINT, precipitation_id BIGINT, pressure_id BIGINT, temperature_id BIGINT, wind_id BIGINT, PRIMARY KEY (id));
CREATE CACHED TABLE IF NOT EXISTS data_point (id BIGINT generated BY DEFAULT AS IDENTITY, COUNT INTEGER NOT NULL, FIRST INTEGER NOT NULL, last_update_time BIGINT NOT NULL, mean DOUBLE NOT NULL, SECOND INTEGER NOT NULL, third INTEGER NOT NULL, TYPE VARCHAR(255), airport_id BIGINT, PRIMARY KEY (id));
CREATE CACHED TABLE IF NOT EXISTS radius_request_frequency (id BIGINT generated BY DEFAULT AS IDENTITY, frquency INTEGER NOT NULL, radius DOUBLE NOT NULL, PRIMARY KEY (id));
ALTER TABLE airport ADD CONSTRAINT IF NOT EXISTS uk_airport_iata UNIQUE (iata);
ALTER TABLE radius_request_frequency ADD CONSTRAINT IF NOT EXISTS uk_rrf_radius UNIQUE (radius);
ALTER TABLE airport_request_frequency ADD CONSTRAINT IF NOT EXISTS fk_arf_aiporrt FOREIGN KEY (airport_id) REFERENCES airport;
ALTER TABLE data_point ADD CONSTRAINT IF NOT EXISTS fk_dp_airport FOREIGN KEY (airport_id) REFERENCES airport;
ALTER TABLE atmospheric_information ADD CONSTRAINT IF NOT EXISTS fk_ai_airport FOREIGN KEY (airport_id) REFERENCES airport;
ALTER TABLE atmospheric_information ADD CONSTRAINT IF NOT EXISTS fk_ai_cloud_datapoint FOREIGN KEY (cloud_cover_id) REFERENCES data_point;
ALTER TABLE atmospheric_information ADD CONSTRAINT IF NOT EXISTS fk_ai_humidity_datapoint FOREIGN KEY (humidity_id) REFERENCES data_point;
ALTER TABLE atmospheric_information ADD CONSTRAINT IF NOT EXISTS fk_ai_precipt_datapoint FOREIGN KEY (precipitation_id) REFERENCES data_point;
ALTER TABLE atmospheric_information ADD CONSTRAINT IF NOT EXISTS fk_ai_pressure_datapoint FOREIGN KEY (pressure_id) REFERENCES data_point;
ALTER TABLE atmospheric_information ADD CONSTRAINT IF NOT EXISTS fk_ai_temperature_datapoint FOREIGN KEY (temperature_id) REFERENCES data_point;
ALTER TABLE atmospheric_information ADD CONSTRAINT IF NOT EXISTS fk_ai_wind_datapoint FOREIGN KEY (wind_id) REFERENCES data_point;
