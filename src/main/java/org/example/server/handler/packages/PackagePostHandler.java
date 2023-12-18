package org.example.server.handler.packages;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.application.CardType;
import org.example.application.ElementType;
import org.example.application.models.Card;
import org.example.application.repository.CardRepository;
import org.example.application.repository.UserRepository;
import org.example.application.service.LoginService;
import org.example.server.StatusCode;
import org.example.server.handler.Handler;
import org.example.server.util.Request;
import org.example.server.util.Response;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

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

                if (loginService.adminTokenLogin(request.getAuthorization())) {
                    this.splitPackage(request.getBody());

                    Iterator<String> iterator = cardPackage.iterator();
                    while (iterator.hasNext()) {
                        String part = iterator.next();
                        Card card = objectMapper.readValue(part, Card.class);
                        this.setTypeAndElement(card);
                        cardRepository.createCard(card);
                        iterator.remove();
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

    public void setTypeAndElement(Card card) {
        //CARDTYPE
        if(card.getName().endsWith("Spell")) {
            card.setCardType(CardType.SPELL);
            //ELEMENTTYPE
            if(card.getName().startsWith("Water")) {
                card.setElementType(ElementType.WATER);
            } else if(card.getName().startsWith("Fire")) {
                card.setElementType(ElementType.FIRE);
            } else if (card.getName().startsWith("Regular")) {
                card.setElementType(ElementType.NORMAL);
            }
        } else {
            card.setCardType(CardType.MONSTER);
            //ELEMENTTYPE
            if(card.getName().startsWith("Water")) {
                card.setElementType(ElementType.WATER);
            } else if (card.getName().startsWith("Dragon")) {
                card.setElementType(ElementType.FIRE);
            } else if (card.getName().startsWith("Ork")) {
                card.setElementType(ElementType.NORMAL);
            }


        }

    }

}
