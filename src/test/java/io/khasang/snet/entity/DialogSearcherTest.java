package io.khasang.snet.entity;


import io.khasang.snet.entity.userauth.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.*;
import java.util.stream.Collectors;

public class DialogSearcherTest {

    private List<ChatRegistryUnit> hayStack;
    private User first;
    private User second;
    private Chat needle;
    private static final int amountSamples = 100;

    @Before
    public void setUp() {
        Random random = new Random(47);
        hayStack = new ArrayList<>(amountSamples);

        for (int i = 0; i < amountSamples; i++) {
            User user = new User();
            user.setID(random.nextInt(2));

            Chat chat = new Chat();
            chat.setID((long) random.nextInt(50));
            hayStack.add(new ChatRegistryUnit(chat, user));
        }

        first = new User();
        first.setID(random.nextInt(2));

        second = new User();
        second.setID(random.nextInt(2));

        needle = new Chat();
        needle.setID(random.nextLong());

        int tmp = random.nextInt(2);
        hayStack.get(tmp).setChat(needle);
        hayStack.get(tmp).setUser(first);

        tmp = random.nextInt(2);
        hayStack.get(tmp).setChat(needle);
        hayStack.get(tmp).setUser(second);

    }

    @Test
    public void testDialogSearching() {
        // Sanity check for test
        assertEquals("Sanity check for test failed: Needles must be 2",2,amountOfNeedles());

        List<Chat> dialogs = searchingAlgorithm();
        int amountParticipants;
        for (Chat dialog : dialogs) {
            amountParticipants = 0;
            for (ChatRegistryUnit registryUnit : hayStack) {
                if (registryUnit.getChat().equals(dialog)) {
                    amountParticipants++;
                    System.out.println(registryUnit);
                }
            }
            if (amountParticipants > 2) fail("In dialog must be only two participants");
        }


    }

    private List<Chat> searchingAlgorithm() {
        List<Chat> result = new ArrayList<>();

        Map<Chat, List<User>> discusses = new HashMap<>();

        for (ChatRegistryUnit registryUnit : hayStack) {
            if (discusses.containsKey(registryUnit.getChat())) {
                discusses.get(registryUnit.getChat()).add(registryUnit.getUser());
            } else {
                discusses.put(registryUnit.getChat(), new ArrayList<>());
                discusses.get(registryUnit.getChat()).add(registryUnit.getUser());
            }

        }

        for (Map.Entry<Chat, List<User>> chatListEntry : discusses.entrySet()) {
            if (chatListEntry.getValue().size()==2) {
                result.add(chatListEntry.getKey());
            }
        }

        return result;
    }

    private int amountOfNeedles() {
        int amount = 0;
        for (ChatRegistryUnit registryUnit : hayStack)
            if (registryUnit.getChat().getID().equals(needle.getID())) amount++;

        return amount;
    }
}
