package org.example.server.handler.battle;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.example.application.models.User;
import org.example.application.repository.UserRepository;
import org.example.application.service.Battle;
import org.example.application.service.BattleQueuer;
import org.example.application.service.BattleService;
import org.example.server.StatusCode;
import org.example.server.handler.Handler;
import org.example.server.util.Request;
import org.example.server.util.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class BattlePostHandler implements Handler {
    private boolean ready = false;
    private BattleService battleService = new BattleService();

    @Override
    public Response handle(Request request) throws JsonProcessingException, IOException {
        Response response = new Response();
        if(request.getMethod().equals("POST")) {
            UserRepository userRepository = new UserRepository();
            if(userRepository.tokenExist(request.getAuthorization())) {
                User user = userRepository.getUserWithToken(request.getAuthorization());
                BattleQueuer.instance.getList().add(user);
                try {
                    Battle battle;
                    Object lock = BattleQueuer.instance.getLock();
                    if (BattleQueuer.instance.getList().size() < 2) {
                        synchronized (lock) {
                            lock.wait();
                        }
                        battle = BattleQueuer.instance.getResults().get(user.getUsername());
                    } else {
                        User user1 = BattleQueuer.instance.getList().get(0);
                        User user2 = BattleQueuer.instance.getList().get(1);
                        battle = battleService.battle(user1, user2);
                        BattleQueuer.instance.getResults().put(user1.getUsername(), battle);
                        synchronized (lock) {
                            lock.notify();
                        }
                    }
                    response.setStatusCode(StatusCode.OK);
                    response.setBody(battle.getLog());
                    response.countMessageLength(response);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }


            } else {
                response.setStatusCode(StatusCode.NOT_FOUND);
            }
        } else {
            response.setStatusCode(StatusCode.METHOD_NOT_ALLOWED);
        }

        return response;
    }
}
