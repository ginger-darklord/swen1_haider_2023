package org.example.application.service;

import org.example.application.models.Card;
import org.example.application.models.User;
import org.example.application.repository.CardRepository;
import org.example.application.repository.UserRepository;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BattleService {
    private CardRepository cardRepository = new CardRepository();
    private UserRepository userRepository = new UserRepository();
    private final Lock lock = new ReentrantLock(true);
    private Battle battle = new Battle();
    public Battle battle(User user1, User user2) {
        ArrayList<Card> deck1 = cardRepository.getDeck(user1.getUsername());
        ArrayList<Card> deck2 = cardRepository.getDeck(user2.getUsername());
        int round = 0;
        while (!deck1.isEmpty() && !deck2.isEmpty()) {
            Random random = new Random();
            int num1 = random.nextInt(deck1.size());
            int num2 = random.nextInt(deck2.size());

            Card card1 = deck1.get(num1);
            Card card2 = deck2.get(num2);

            //specialities
            Card loser = this.specialities(card1, card2);
            String log = null;
            if (loser.getId().equals(card1.getId())) {
                deck1.remove(loser);
                cardRepository.changeUser(user2.getUsername(), loser.getId());
                log = "Round: " + round +
                        "\nUser: " + user1.getUsername() + " vs User: " + user2.getUsername() +
                        "\nCard: " + card1.getName() + "(" + card1.getElement() + ") vs Card: " + card2.getName() + "(" + card2.getElement() + ")" +
                        "\nWinner: " + user2.getUsername();
            } else if (loser.getId().equals(card2.getId())) {
                deck2.remove(loser);
                cardRepository.changeUser(user1.getUsername(), loser.getId());
                log = "Round: " + round +
                        "\nUser: " + user1.getUsername() + " vs User: " + user2.getUsername() +
                        "\nCard: " + card1.getName() + "(" + card1.getElement() + ") vs Card: " + card2.getName() + "(" + card2.getElement() + ")" +
                        "\nWinner: " + user1.getUsername();
            }

            //BATTLE//
            if (card1.getType().equals("monster") && card2.getType().equals("monster")) { //monster
                if(card1.getDamage() < card2.getDamage()) {
                    deck1.remove(card1);
                    cardRepository.changeUser(user2.getUsername(), card1.getId());
                    log = "Round: " + round +
                            "\nUser: " + user1.getUsername() + " vs User: " + user2.getUsername() +
                            "\nCard: " + card1.getName() + "(" + card1.getElement() + ") vs Card: " + card2.getName() + "(" + card2.getElement() + ")" +
                            "\nWinner: " + user2.getUsername();
                } else if (card1.getDamage() > card2.getDamage()) {
                    deck2.remove(card2);
                    cardRepository.changeUser(user1.getUsername(), card2.getId());
                    log = "Round: " + round +
                            "\nUser: " + user1.getUsername() + " vs User: " + user2.getUsername() +
                            "\nCard: " + card1.getName() + "(" + card1.getElement() + ") vs Card: " + card2.getName() + "(" + card2.getElement() + ")" +
                            "\nWinner: " + user1.getUsername();
                } else {
                    log = "Round: " + round +
                            "\nUser: " + user1.getUsername() + " vs User: " + user2.getUsername() +
                            "\nCard: " + card1.getName() + "(" + card1.getElement() + ") vs Card: " + card2.getName() + "(" + card2.getElement() + ")" +
                            "\nWinner: draw";
                }
            } else if (card1.getType().equals("spell") && card2.getType().equals("spell")) { //spell
                if(card1.getElement().equals(card2.getElement())) {
                    if(card1.getDamage() < card2.getDamage()) {
                        deck1.remove(card1);
                        cardRepository.changeUser(user2.getUsername(), card1.getId());
                        log = "Round: " + round +
                                "\nUser: " + user1.getUsername() + " vs User: " + user2.getUsername() +
                                "\nCard: " + card1.getName() + "(" + card1.getElement() + ") vs Card: " + card2.getName() + "(" + card2.getElement() + ")" +
                                "\nWinner: " + user2.getUsername();
                    } else if (card1.getDamage() > card2.getDamage()) {
                        deck2.remove(card2);
                        cardRepository.changeUser(user1.getUsername(), card2.getId());
                        log = "Round: " + round +
                                "\nUser: " + user1.getUsername() + " vs User: " + user2.getUsername() +
                                "\nCard: " + card1.getName() + "(" + card1.getElement() + ") vs Card: " + card2.getName() + "(" + card2.getElement() + ")" +
                                "\nWinner: " + user1.getUsername();
                    } else {
                        log = "Round: " + round +
                                "\nUser: " + user1.getUsername() + " vs User: " + user2.getUsername() +
                                "\nCard: " + card1.getName() + "(" + card1.getElement() + ") vs Card: " + card2.getName() + "(" + card2.getElement() + ")" +
                                "\nWinner: draw";
                    }
                } else if (card1.getElement().equals("water")) {
                    if(card2.getElement().equals("fire")) {
                        card1.setDamage(card1.getDamage() * 2);
                        card2.setDamage(card2.getDamage() / 2);
                        if(card1.getDamage() < card2.getDamage()) {
                            deck1.remove(card1);
                            cardRepository.changeUser(user2.getUsername(), card1.getId());
                            log = "Round: " + round +
                                    "\nUser: " + user1.getUsername() + " vs User: " + user2.getUsername() +
                                    "\nCard: " + card1.getName() + "(" + card1.getElement() + ") vs Card: " + card2.getName() + "(" + card2.getElement() + ")" +
                                    "\nWinner: " + user2.getUsername();
                        } else if (card1.getDamage() > card2.getDamage()) {
                            deck2.remove(card2);
                            cardRepository.changeUser(user1.getUsername(), card2.getId());
                            log = "Round: " + round +
                                    "\nUser: " + user1.getUsername() + " vs User: " + user2.getUsername() +
                                    "\nCard: " + card1.getName() + "(" + card1.getElement() + ") vs Card: " + card2.getName() + "(" + card2.getElement() + ")" +
                                    "\nWinner: " + user1.getUsername();
                        } else {
                            log = "Round: " + round +
                                    "\nUser: " + user1.getUsername() + " vs User: " + user2.getUsername() +
                                    "\nCard: " + card1.getName() + "(" + card1.getElement() + ") vs Card: " + card2.getName() + "(" + card2.getElement() + ")" +
                                    "\nWinner: draw";
                        }
                    } else if (card2.getElement().equals("normal")) {
                        card1.setDamage(card1.getDamage() / 2);
                        card2.setDamage(card2.getDamage() * 2);
                        if(card1.getDamage() < card2.getDamage()) {
                            deck1.remove(card1);
                            cardRepository.changeUser(user2.getUsername(), card1.getId());
                            log = "Round: " + round +
                                    "\nUser: " + user1.getUsername() + " vs User: " + user2.getUsername() +
                                    "\nCard: " + card1.getName() + "(" + card1.getElement() + ") vs Card: " + card2.getName() + "(" + card2.getElement() + ")" +
                                    "\nWinner: " + user2.getUsername();
                        } else if (card1.getDamage() > card2.getDamage()) {
                            deck2.remove(card2);
                            cardRepository.changeUser(user1.getUsername(), card2.getId());
                            log = "Round: " + round +
                                    "\nUser: " + user1.getUsername() + " vs User: " + user2.getUsername() +
                                    "\nCard: " + card1.getName() + "(" + card1.getElement() + ") vs Card: " + card2.getName() + "(" + card2.getElement() + ")" +
                                    "\nWinner: " + user1.getUsername();
                        } else {
                            log = "Round: " + round +
                                    "\nUser: " + user1.getUsername() + " vs User: " + user2.getUsername() +
                                    "\nCard: " + card1.getName() + "(" + card1.getElement() + ") vs Card: " + card2.getName() + "(" + card2.getElement() + ")" +
                                    "\nWinner: draw";
                        }
                    }
                } else if (card1.getElement().equals("fire")) {
                    if(card2.getElement().equals("water")) {
                        card1.setDamage(card1.getDamage() / 2);
                        card2.setDamage(card2.getDamage() * 2);
                        if(card1.getDamage() < card2.getDamage()) {
                            deck1.remove(card1);
                            cardRepository.changeUser(user2.getUsername(), card1.getId());
                            log = "Round: " + round +
                                    "\nUser: " + user1.getUsername() + " vs User: " + user2.getUsername() +
                                    "\nCard: " + card1.getName() + "(" + card1.getElement() + ") vs Card: " + card2.getName() + "(" + card2.getElement() + ")" +
                                    "\nWinner: " + user2.getUsername();
                        } else if (card1.getDamage() > card2.getDamage()) {
                            deck2.remove(card2);
                            cardRepository.changeUser(user1.getUsername(), card2.getId());
                            log = "Round: " + round +
                                    "\nUser: " + user1.getUsername() + " vs User: " + user2.getUsername() +
                                    "\nCard: " + card1.getName() + "(" + card1.getElement() + ") vs Card: " + card2.getName() + "(" + card2.getElement() + ")" +
                                    "\nWinner: " + user1.getUsername();
                        } else {
                            log = "Round: " + round +
                                    "\nUser: " + user1.getUsername() + " vs User: " + user2.getUsername() +
                                    "\nCard: " + card1.getName() + "(" + card1.getElement() + ") vs Card: " + card2.getName() + "(" + card2.getElement() + ")" +
                                    "\nWinner: draw";
                        }
                    } else if (card2.getElement().equals("normal")) {
                        card1.setDamage(card1.getDamage() * 2);
                        card2.setDamage(card2.getDamage() / 2);
                        if(card1.getDamage() < card2.getDamage()) {
                            deck1.remove(card1);
                            cardRepository.changeUser(user2.getUsername(), card1.getId());
                            log = "Round: " + round +
                                    "\nUser: " + user1.getUsername() + " vs User: " + user2.getUsername() +
                                    "\nCard: " + card1.getName() + "(" + card1.getElement() + ") vs Card: " + card2.getName() + "(" + card2.getElement() + ")" +
                                    "\nWinner: " + user2.getUsername();
                        } else if (card1.getDamage() > card2.getDamage()) {
                            deck2.remove(card2);
                            cardRepository.changeUser(user1.getUsername(), card2.getId());
                            log = "Round: " + round +
                                    "\nUser: " + user1.getUsername() + " vs User: " + user2.getUsername() +
                                    "\nCard: " + card1.getName() + "(" + card1.getElement() + ") vs Card: " + card2.getName() + "(" + card2.getElement() + ")" +
                                    "\nWinner: " + user1.getUsername();
                        } else {
                            log = "Round: " + round +
                                    "\nUser: " + user1.getUsername() + " vs User: " + user2.getUsername() +
                                    "\nCard: " + card1.getName() + "(" + card1.getElement() + ") vs Card: " + card2.getName() + "(" + card2.getElement() + ")" +
                                    "\nWinner: draw";
                        }
                    }
                } else if (card1.getElement().equals("normal")) {
                    if (card2.getElement().equals("water")) {
                        card1.setDamage(card1.getDamage() * 2);
                        card2.setDamage(card2.getDamage() / 2);
                        if(card1.getDamage() < card2.getDamage()) {
                            deck1.remove(card1);
                            cardRepository.changeUser(user2.getUsername(), card1.getId());
                            log = "Round: " + round +
                                    "\nUser: " + user1.getUsername() + " vs User: " + user2.getUsername() +
                                    "\nCard: " + card1.getName() + "(" + card1.getElement() + ") vs Card: " + card2.getName() + "(" + card2.getElement() + ")" +
                                    "\nWinner: " + user2.getUsername();
                        } else if (card1.getDamage() > card2.getDamage()) {
                            deck2.remove(card2);
                            cardRepository.changeUser(user1.getUsername(), card2.getId());
                            log = "Round: " + round +
                                    "\nUser: " + user1.getUsername() + " vs User: " + user2.getUsername() +
                                    "\nCard: " + card1.getName() + "(" + card1.getElement() + ") vs Card: " + card2.getName() + "(" + card2.getElement() + ")" +
                                    "\nWinner: " + user1.getUsername();
                        } else {
                            log = "Round: " + round +
                                    "\nUser: " + user1.getUsername() + " vs User: " + user2.getUsername() +
                                    "\nCard: " + card1.getName() + "(" + card1.getElement() + ") vs Card: " + card2.getName() + "(" + card2.getElement() + ")" +
                                    "\nWinner: draw";
                        }
                    } else if (card2.getElement().equals("fire")) {
                        card1.setDamage(card1.getDamage() / 2);
                        card2.setDamage(card2.getDamage() * 2);
                        if(card1.getDamage() < card2.getDamage()) {
                            deck1.remove(card1);
                            cardRepository.changeUser(user2.getUsername(), card1.getId());
                            log = "Round: " + round +
                                    "\nUser: " + user1.getUsername() + " vs User: " + user2.getUsername() +
                                    "\nCard: " + card1.getName() + "(" + card1.getElement() + ") vs Card: " + card2.getName() + "(" + card2.getElement() + ")" +
                                    "\nWinner: " + user2.getUsername();
                        } else if (card1.getDamage() > card2.getDamage()) {
                            deck2.remove(card2);
                            cardRepository.changeUser(user1.getUsername(), card2.getId());
                            log = "Round: " + round +
                                    "\nUser: " + user1.getUsername() + " vs User: " + user2.getUsername() +
                                    "\nCard: " + card1.getName() + "(" + card1.getElement() + ") vs Card: " + card2.getName() + "(" + card2.getElement() + ")" +
                                    "\nWinner: " + user1.getUsername();
                        } else {
                            log = "Round: " + round +
                                    "\nUser: " + user1.getUsername() + " vs User: " + user2.getUsername() +
                                    "\nCard: " + card1.getName() + "(" + card1.getElement() + ") vs Card: " + card2.getName() + "(" + card2.getElement() + ")" +
                                    "\nWinner: draw";
                        }
                    }
                }
            } else if (!card1.getType().equals(card2.getType())) { //mixed spell
                if(card1.getElement().equals(card2.getElement())) {
                    if(card1.getDamage() < card2.getDamage()) {
                        deck1.remove(card1);
                        cardRepository.changeUser(user2.getUsername(), card1.getId());
                        log = "Round: " + round +
                                "\nUser: " + user1.getUsername() + " vs User: " + user2.getUsername() +
                                "\nCard: " + card1.getName() + "(" + card1.getElement() + ") vs Card: " + card2.getName() + "(" + card2.getElement() + ")" +
                                "\nWinner: " + user2.getUsername();
                    } else if (card1.getDamage() > card2.getDamage()) {
                        deck2.remove(card2);
                        cardRepository.changeUser(user1.getUsername(), card2.getId());
                        log = "Round: " + round +
                                "\nUser: " + user1.getUsername() + " vs User: " + user2.getUsername() +
                                "\nCard: " + card1.getName() + "(" + card1.getElement() + ") vs Card: " + card2.getName() + "(" + card2.getElement() + ")" +
                                "\nWinner: " + user1.getUsername();
                    } else {
                        log = "Round: " + round +
                                "\nUser: " + user1.getUsername() + " vs User: " + user2.getUsername() +
                                "\nCard: " + card1.getName() + "(" + card1.getElement() + ") vs Card: " + card2.getName() + "(" + card2.getElement() + ")" +
                                "\nWinner: draw";
                    }
                } else if (card1.getElement().equals("water")) {
                    if(card2.getElement().equals("fire")) {
                        card1.setDamage(card1.getDamage() * 2);
                        card2.setDamage(card2.getDamage() / 2);
                        if(card1.getDamage() < card2.getDamage()) {
                            deck1.remove(card1);
                            cardRepository.changeUser(user2.getUsername(), card1.getId());
                            log = "Round: " + round +
                                    "\nUser: " + user1.getUsername() + " vs User: " + user2.getUsername() +
                                    "\nCard: " + card1.getName() + "(" + card1.getElement() + ") vs Card: " + card2.getName() + "(" + card2.getElement() + ")" +
                                    "\nWinner: " + user2.getUsername();
                        } else if (card1.getDamage() > card2.getDamage()) {
                            deck2.remove(card2);
                            cardRepository.changeUser(user1.getUsername(), card2.getId());
                            log = "Round: " + round +
                                    "\nUser: " + user1.getUsername() + " vs User: " + user2.getUsername() +
                                    "\nCard: " + card1.getName() + "(" + card1.getElement() + ") vs Card: " + card2.getName() + "(" + card2.getElement() + ")" +
                                    "\nWinner: " + user1.getUsername();
                        } else {
                            log = "Round: " + round +
                                    "\nUser: " + user1.getUsername() + " vs User: " + user2.getUsername() +
                                    "\nCard: " + card1.getName() + "(" + card1.getElement() + ") vs Card: " + card2.getName() + "(" + card2.getElement() + ")" +
                                    "\nWinner: draw";
                        }
                    } else if (card2.getElement().equals("normal")) {
                        card1.setDamage(card1.getDamage() / 2);
                        card2.setDamage(card2.getDamage() * 2);
                        if(card1.getDamage() < card2.getDamage()) {
                            deck1.remove(card1);
                            cardRepository.changeUser(user2.getUsername(), card1.getId());
                            log = "Round: " + round +
                                    "\nUser: " + user1.getUsername() + " vs User: " + user2.getUsername() +
                                    "\nCard: " + card1.getName() + "(" + card1.getElement() + ") vs Card: " + card2.getName() + "(" + card2.getElement() + ")" +
                                    "\nWinner: " + user2.getUsername();
                        } else if (card1.getDamage() > card2.getDamage()) {
                            deck2.remove(card2);
                            cardRepository.changeUser(user1.getUsername(), card2.getId());
                            log = "Round: " + round +
                                    "\nUser: " + user1.getUsername() + " vs User: " + user2.getUsername() +
                                    "\nCard: " + card1.getName() + "(" + card1.getElement() + ") vs Card: " + card2.getName() + "(" + card2.getElement() + ")" +
                                    "\nWinner: " + user1.getUsername();
                        } else {
                            log = "Round: " + round +
                                    "\nUser: " + user1.getUsername() + " vs User: " + user2.getUsername() +
                                    "\nCard: " + card1.getName() + "(" + card1.getElement() + ") vs Card: " + card2.getName() + "(" + card2.getElement() + ")" +
                                    "\nWinner: draw";
                        }
                    }
                } else if (card1.getElement().equals("fire")) {
                    if(card2.getElement().equals("water")) {
                        card1.setDamage(card1.getDamage() / 2);
                        card2.setDamage(card2.getDamage() * 2);
                        if(card1.getDamage() < card2.getDamage()) {
                            deck1.remove(card1);
                            cardRepository.changeUser(user2.getUsername(), card1.getId());
                            log = "Round: " + round +
                                    "\nUser: " + user1.getUsername() + " vs User: " + user2.getUsername() +
                                    "\nCard: " + card1.getName() + "(" + card1.getElement() + ") vs Card: " + card2.getName() + "(" + card2.getElement() + ")" +
                                    "\nWinner: " + user2.getUsername();
                        } else if (card1.getDamage() > card2.getDamage()) {
                            deck2.remove(card2);
                            cardRepository.changeUser(user1.getUsername(), card2.getId());
                            log = "Round: " + round +
                                    "\nUser: " + user1.getUsername() + " vs User: " + user2.getUsername() +
                                    "\nCard: " + card1.getName() + "(" + card1.getElement() + ") vs Card: " + card2.getName() + "(" + card2.getElement() + ")" +
                                    "\nWinner: " + user1.getUsername();
                        } else {
                            log = "Round: " + round +
                                    "\nUser: " + user1.getUsername() + " vs User: " + user2.getUsername() +
                                    "\nCard: " + card1.getName() + "(" + card1.getElement() + ") vs Card: " + card2.getName() + "(" + card2.getElement() + ")" +
                                    "\nWinner: draw";
                        }
                    } else if (card2.getElement().equals("normal")) {
                        card1.setDamage(card1.getDamage() * 2);
                        card2.setDamage(card2.getDamage() / 2);
                        if(card1.getDamage() < card2.getDamage()) {
                            deck1.remove(card1);
                            cardRepository.changeUser(user2.getUsername(), card1.getId());
                            log = "Round: " + round +
                                    "\nUser: " + user1.getUsername() + " vs User: " + user2.getUsername() +
                                    "\nCard: " + card1.getName() + "(" + card1.getElement() + ") vs Card: " + card2.getName() + "(" + card2.getElement() + ")" +
                                    "\nWinner: " + user2.getUsername();
                        } else if (card1.getDamage() > card2.getDamage()) {
                            deck2.remove(card2);
                            cardRepository.changeUser(user1.getUsername(), card2.getId());
                            log = "Round: " + round +
                                    "\nUser: " + user1.getUsername() + " vs User: " + user2.getUsername() +
                                    "\nCard: " + card1.getName() + "(" + card1.getElement() + ") vs Card: " + card2.getName() + "(" + card2.getElement() + ")" +
                                    "\nWinner: " + user1.getUsername();
                        } else {
                            log = "Round: " + round +
                                    "\nUser: " + user1.getUsername() + " vs User: " + user2.getUsername() +
                                    "\nCard: " + card1.getName() + "(" + card1.getElement() + ") vs Card: " + card2.getName() + "(" + card2.getElement() + ")" +
                                    "\nWinner: draw";
                        }
                    }
                } else if (card1.getElement().equals("normal")) {
                    if (card2.getElement().equals("water")) {
                        card1.setDamage(card1.getDamage() * 2);
                        card2.setDamage(card2.getDamage() / 2);
                        if(card1.getDamage() < card2.getDamage()) {
                            deck1.remove(card1);
                            cardRepository.changeUser(user2.getUsername(), card1.getId());
                            log = "Round: " + round +
                                    "\nUser: " + user1.getUsername() + " vs User: " + user2.getUsername() +
                                    "\nCard: " + card1.getName() + "(" + card1.getElement() + ") vs Card: " + card2.getName() + "(" + card2.getElement() + ")" +
                                    "\nWinner: " + user2.getUsername();
                        } else if (card1.getDamage() > card2.getDamage()) {
                            deck2.remove(card2);
                            cardRepository.changeUser(user1.getUsername(), card2.getId());
                            log = "Round: " + round +
                                    "\nUser: " + user1.getUsername() + " vs User: " + user2.getUsername() +
                                    "\nCard: " + card1.getName() + "(" + card1.getElement() + ") vs Card: " + card2.getName() + "(" + card2.getElement() + ")" +
                                    "\nWinner: " + user1.getUsername();
                        } else {
                            log = "Round: " + round +
                                    "\nUser: " + user1.getUsername() + " vs User: " + user2.getUsername() +
                                    "\nCard: " + card1.getName() + "(" + card1.getElement() + ") vs Card: " + card2.getName() + "(" + card2.getElement() + ")" +
                                    "\nWinner: draw";
                        }
                    } else if (card2.getElement().equals("fire")) {
                        card1.setDamage(card1.getDamage() / 2);
                        card2.setDamage(card2.getDamage() * 2);
                        if(card1.getDamage() < card2.getDamage()) {
                            deck1.remove(card1);
                            cardRepository.changeUser(user2.getUsername(), card1.getId());
                            log = "Round: " + round +
                                    "\nUser: " + user1.getUsername() + " vs User: " + user2.getUsername() +
                                    "\nCard: " + card1.getName() + "(" + card1.getElement() + ") vs Card: " + card2.getName() + "(" + card2.getElement() + ")" +
                                    "\nWinner: " + user2.getUsername();
                        } else if (card1.getDamage() > card2.getDamage()) {
                            deck2.remove(card2);
                            cardRepository.changeUser(user1.getUsername(), card2.getId());
                            log = "Round: " + round +
                                    "\nUser: " + user1.getUsername() + " vs User: " + user2.getUsername() +
                                    "\nCard: " + card1.getName() + "(" + card1.getElement() + ") vs Card: " + card2.getName() + "(" + card2.getElement() + ")" +
                                    "\nWinner: " + user1.getUsername();
                        } else {
                            log = "Round: " + round +
                                    "\nUser: " + user1.getUsername() + " vs User: " + user2.getUsername() +
                                    "\nCard: " + card1.getName() + "(" + card1.getElement() + ") vs Card: " + card2.getName() + "(" + card2.getElement() + ")" +
                                    "\nWinner: draw";
                        }
                    }
                }
            }
            lock.lock();
            try {
                battle.setLog(battle.getLog() + "\n" + log);
                round++;
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }

        if (deck1.isEmpty()) {
            battle.setWinner(user2);
            user1.setElo(user1.getElo() - 5);
        } else if (deck2.isEmpty()) {
            battle.setWinner(user1);
            user2.setElo(user2.getElo() - 5);
        }
        System.out.println(battle.getWinner().getUsername() + " has won the battle.");
        battle.getWinner().setElo(battle.getWinner().getElo() + 3);

        userRepository.updateElo(user1);
        userRepository.updateElo(user2);

        battle.setUser1(user1);
        battle.setUser2(user2);
        return battle;
    }

    public Card specialities(Card card1, Card card2) {
        if (card2.getName().endsWith("Dragon")) {
            if(card1.getName().endsWith("Goblin")) {
                return card1;
            } else if (card1.getName().endsWith("Elf")) {
                return card2;
            }
        } else if (card1.getName().endsWith("Dragon")) {
            if (card2.getName().endsWith("Goblin")) {
                return card2;
            } else if (card2.getName().endsWith("Elf")) {
                return card1;
            }
        } else if (card1.getName().equals("Kraken") && card2.getType().equals("spell")) {
            return card2;
        } else if (card2.getName().equals("Kraken") && card1.getType().equals("spell")) {
            return card1;
        } else if (card1.getName().equals("Wizard") && card2.getName().equals("Ork")) {
            return card2;
        } else if (card2.getName().equals("Wizard") && card1.getName().equals("Ork")) {
            return card1;
        } else if (card1.getName().equals("Knight") && card2.getName().equals("WaterSpell")) {
            return card1;
        } else if (card2.getName().equals("Knight") && card1.getName().equals("WaterSpell")) {
            return card2;
        }
        return new Card("123", "Elf", 20, "monster", "water");
    }



}
