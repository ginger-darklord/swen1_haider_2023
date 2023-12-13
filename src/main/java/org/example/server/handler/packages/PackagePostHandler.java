package org.example.server.handler.packages;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.application.models.Card;
import org.example.application.repository.CardRepository;
import org.example.application.repository.UserRepository;
import org.example.application.service.LoginService;
import org.example.server.StatusCode;
import org.example.server.handler.Handler;
import org.example.server.util.Request;
import org.example.server.util.Response;

import java.util.ArrayList;

public class PackagePostHandler implements Handler {
    private LoginService loginService = new LoginService();
    private CardRepository cardRepository = new CardRepository();
    private ArrayList<String> cardPackage = new ArrayList<>();

    @Override
    public Response handle(Request request) throws JsonProcessingException {
        Response response = new Response();

        if (request.getMethod().equals("POST")) {
            if (request.getContentType().startsWith("Content-Type: ")) {
                //parse json data using jackson//
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

                if (loginService.adminLogin(request.getAuthorization())) {
                    this.splitPackage(request.getBody());
                    for(String part : cardPackage) {
                        //too many """""""""'
                        //part = part.replaceAll();
                        Card card = objectMapper.readValue(part, Card.class);
                         cardRepository.createCard(card);
                    }
                }

                response.setStatusCode(StatusCode.OK);
            }
        } else {
            response.setStatusCode(StatusCode.METHOD_NOT_ALLOWED);
        }
        return response;
    }

    public void splitPackage(String body) {
        String[] bodyParts = body.split(", (?=\\{)");

        for(String part : bodyParts) {
            part = part.replaceAll("\\[|\\]", "");
            this.cardPackage.add(part);
        }
    }

}
