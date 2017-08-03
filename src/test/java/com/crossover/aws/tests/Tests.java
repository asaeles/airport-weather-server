/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.crossover.aws.tests;

import com.crossover.aws.repository.AirportRepository;
import static org.hamcrest.Matchers.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class Tests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AirportRepository airportRepository;

    @Before
    public void deleteAllBeforeTests() throws Exception {
        airportRepository.deleteAll();
    }

    // List of all endpoints that should be tested:
    // 
    //   GET    "/collect/ping"
    //   POST   "/collect/airport/{iata}/{lat}/{lon}"
    //   GET    "/collect/airports"
    //   GET    "/collect/airport/{iata}"
    //   DELETE "/collect/airport/{iata}"
    //   POST   "/collect/weather/{iata}/{type}"
    //   GET    "query/ping"
    //   GET    "/query/weather/{iata}/{radius}"
    //
    // GET:"/collect/ping"
    @Test
    public void shouldReturnPing() throws Exception {
        mockMvc.perform(get("/collect/ping").content("ready").contentType(MediaType.TEXT_PLAIN));
    }

    // POST:"/collect/airport/{iata}/{lat}/{lon}"
    @Test
    public void shouldCreateAirportWithoutBody() throws Exception {
        mockMvc.perform(
                post("/collect/airport/HOBBA/10/20")
                .content("done").contentType(MediaType.TEXT_PLAIN));
    }
//
//    @Test
//    public void shouldRetrieveEntity() throws Exception {
//        MvcResult mvcResult = mockMvc.perform(post("/people").content(
//                "{\"firstName\": \"Frodo\", \"lastName\":\"Baggins\"}")).andExpect(
//                        status().isCreated()).andReturn();
//
//        String location = mvcResult.getResponse().getHeader("Location");
//        mockMvc.perform(get(location)).andExpect(status().isOk()).andExpect(
//                jsonPath("$.firstName").value("Frodo")).andExpect(
//                jsonPath("$.lastName").value("Baggins"));
//    }
//
//    @Test
//    public void shouldQueryEntity() throws Exception {
//        mockMvc.perform(post("/people").content(
//                "{ \"firstName\": \"Frodo\", \"lastName\":\"Baggins\"}")).andExpect(
//                        status().isCreated());
//
//        mockMvc.perform(
//                get("/people/search/findByLastName?name={name}", "Baggins")).andExpect(
//                status().isOk()).andExpect(
//                        jsonPath("$._embedded.people[0].firstName").value(
//                        "Frodo"));
//    }
//
//    @Test
//    public void shouldUpdateEntity() throws Exception {
//
//        MvcResult mvcResult = mockMvc.perform(post("/people").content(
//                "{\"firstName\": \"Frodo\", \"lastName\":\"Baggins\"}")).andExpect(
//                        status().isCreated()).andReturn();
//
//        String location = mvcResult.getResponse().getHeader("Location");
//
//        mockMvc.perform(put(location).content(
//                "{\"firstName\": \"Bilbo\", \"lastName\":\"Baggins\"}")).andExpect(
//                        status().isNoContent());
//
//        mockMvc.perform(get(location)).andExpect(status().isOk()).andExpect(
//                jsonPath("$.firstName").value("Bilbo")).andExpect(
//                jsonPath("$.lastName").value("Baggins"));
//    }
//
//    @Test
//    public void shouldPartiallyUpdateEntity() throws Exception {
//
//        MvcResult mvcResult = mockMvc.perform(post("/people").content(
//                "{\"firstName\": \"Frodo\", \"lastName\":\"Baggins\"}")).andExpect(
//                        status().isCreated()).andReturn();
//
//        String location = mvcResult.getResponse().getHeader("Location");
//
//        mockMvc.perform(
//                patch(location).content("{\"firstName\": \"Bilbo Jr.\"}")).andExpect(
//                status().isNoContent());
//
//        mockMvc.perform(get(location)).andExpect(status().isOk()).andExpect(
//                jsonPath("$.firstName").value("Bilbo Jr.")).andExpect(
//                jsonPath("$.lastName").value("Baggins"));
//    }
//
//    @Test
//    public void shouldDeleteEntity() throws Exception {
//
//        MvcResult mvcResult = mockMvc.perform(post("/people").content(
//                "{ \"firstName\": \"Bilbo\", \"lastName\":\"Baggins\"}")).andExpect(
//                        status().isCreated()).andReturn();
//
//        String location = mvcResult.getResponse().getHeader("Location");
//        mockMvc.perform(delete(location)).andExpect(status().isNoContent());
//
//        mockMvc.perform(get(location)).andExpect(status().isNotFound());
//    }
}
