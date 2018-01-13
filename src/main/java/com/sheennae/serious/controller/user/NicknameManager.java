package com.sheennae.serious.controller.user;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.sheennae.serious.model.user.UserModel;
import org.springframework.core.io.ClassPathResource;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NicknameManager {

    private Random random;

    private List<String> adjectives;
    private List<String> nouns;
    private List<String> nicknames;

    public NicknameManager(List<UserModel> users) throws IOException {
        this.random = new Random();
        this.nicknames = new ArrayList<>();
        for (UserModel user : users) {
            nicknames.add(user.getNickname());
        }

        Gson gson = new GsonBuilder().create();
        JsonReader adjectiveReader = new JsonReader(new FileReader(new ClassPathResource("adjective.json").getFile()));
        JsonReader nounReader = new JsonReader(new FileReader(new ClassPathResource("noun.json").getFile()));

        adjectives = gson.fromJson(adjectiveReader, new TypeToken<List<String>>(){}.getType());
        nouns = gson.fromJson(nounReader, new TypeToken<List<String>>(){}.getType());
    }

    public void put(String nickname) {
        if (nicknames.contains(nickname)) {
            return;
        }

        nicknames.add(nickname);
    }

    public String generate() {
        String nickname = "";

        while (true) {
            int adjectiveRandomInt = (Math.abs(random.nextInt())) % adjectives.size();
            int nounRandomInt = (Math.abs(random.nextInt())) % nouns.size();

            nickname = String.format("%s %s", adjectives.get(adjectiveRandomInt), nouns.get(nounRandomInt));
            if (nicknames.contains(nickname))
                continue;

            break;
        }

        return nickname;
    }

}
