package org.example.server.handler.transaction;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.application.models.Card;
import org.example.application.models.User;
import org.example.application.repository.CardRepository;
import org.example.application.repository.UserRepository;
import org.example.application.service.LoginService;
import org.example.server.StatusCode;
import org.example.server.handler.Handler;
import org.example.server.util.Request;
import org.example.server.util.Response;

import java.io.IOException;
import java.util.ArrayList;

public class TransactionPostHandler implements Handler
{
    private Response response;
    private CardRepository cardRepository;
    private LoginService loginService;
    private User user;
    private UserRepository userRepository;
    private ArrayList<Card> packages = new ArrayList<>();
    @Override
    public Response handle(Request request) throws JsonProcessingException, IOException {
        response = new Response();

        if(request.getMethod().equals("POST")) {
            if(request.getContentType() != null && request.getContentType().startsWith("Content-Type: application/json")) {
                loginService = new LoginService();
                if(loginService.userTokenLogin(request.getAuthorization())) {
                    userRepository = new UserRepository();
                    cardRepository = new CardRepository();
                    //checks if token exists
                    if(userRepository.tokenExist(request.getAuthorization())) {
                        user = userRepository.getUserWithToken(request.getAuthorization());
                    } else {
                        response.setStatusCode(StatusCode.BAD_REQUEST);
                    }

                    //checks money
                    if(user.getCoin() <= 0) {
                        System.out.println("User " + user.getUsername() + " does not have enough money to acquire a package");
                        response.setStatusCode(StatusCode.BAD_REQUEST);
                    } else {
                        if (cardRepository.enoughCardsExist()) {
                            //selects card with limit of 5 and returns them in arrayList
                            packages = cardRepository.getPackage();
                            user.setCoin(user.getCoin() - 5);
                            userRepository.buyWithCoin(user.getCoin(), user);

                            //saves which user has which card
                            cardRepository.saveUsername(user.getUsername(), packages);
                            user.saveInStack(packages, user);
                            response.setStatusCode(StatusCode.OK);
                        } else {
                            response.setStatusCode(StatusCode.BAD_REQUEST);
                        }
                    }

                }
            }
        }

        return response;
    }
}
