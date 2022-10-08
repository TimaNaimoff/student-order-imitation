package edu.javacourse.studentorder.reg_checkers;

import edu.javacourse.studentorder.config.Config;
import edu.javacourse.studentorder.domain.Person;
import edu.javacourse.studentorder.exception.CityRegisterException;


import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

public class RealSityRegisterChecker implements CityRegisterChecker{

    public CityRegitsterResponse checkPerson (Person person)throws CityRegisterException {
        try {
            CityRegisterRequest request = new CityRegisterRequest(person);
            Client client = ClientBuilder.newClient();
            CityRegitsterResponse resp = client.target(Config.getProperty(Config.CR_URL)).request(MediaType.APPLICATION_JSON).post(Entity.entity(request, MediaType.APPLICATION_JSON)).
                    readEntity(CityRegitsterResponse.class);
            return resp;
        }catch(Exception ex){
            throw new CityRegisterException("1",ex.getMessage(),ex);
        }

    }
}
