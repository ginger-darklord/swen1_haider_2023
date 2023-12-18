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

public class TransactionPostHandler implements Handler
{
    private Response response;
    private CardRepository cardRepository;
    private ObjectMapper objectMapper;
    private LoginService loginService;
    private User user;
    private Card card;
    private UserRepository userRepository;
    @Override
    public Response handle(Request request) throws JsonProcessingException, IOException {
        response = new Response();

        if(request.getMethod().equals("POST")) {
            if(request.getContentType() != null && request.getContentType().startsWith("Content-Type: application/json")) {
                loginService = new LoginService();
                if(loginService.userTokenLogin(request.getAuthorization())) {
                    userRepository = new UserRepository();
                    user = userRepository.getUserWithToken(request.getAuthorization());
                    if(user.getCoin() <= 0) {
                        System.out.println("User" + user.getUsername() + "does not have enough money to acquire a package");
                    } else {
                        userRepository.buyWithCoin(5, user);
                        if (cardRepository.enoughCardsExist()) {
                            int cnt = 0;
                            while (cnt < 5) {
                                card = cardRepository.getCard("100");
                                //while user saves cards maybe sent into database which user has which card?
                                user.saveCardsStack(card);
                                user.showStack(user);
                                cnt++;
                            }
                            response.setStatusCode(StatusCode.OK);
                        } else {
                            response.setStatusCode(StatusCode.NOT_FOUND);
                        }
                    }

                }
            }
        }


        return response;
    }
}
