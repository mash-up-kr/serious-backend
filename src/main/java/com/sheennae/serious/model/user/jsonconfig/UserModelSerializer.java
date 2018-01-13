package com.sheennae.serious.model.user.jsonconfig;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.sheennae.serious.model.user.UserModel;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class UserModelSerializer extends JsonSerializer<UserModel> {

    @Override
    public void serialize(UserModel userModel,
                          JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider) throws IOException, JsonProcessingException {

        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("id", Integer.toString(userModel.getId()));
        jsonGenerator.writeStringField("nickname", userModel.getNickname());
        jsonGenerator.writeStringField("ageRange",  Integer.toString(userModel.getAgeRange()));
        jsonGenerator.writeStringField("gender", userModel.getGender().toString());
        jsonGenerator.writeStringField("bias",
                userModel.getBias().getBias().toString());
        jsonGenerator.writeStringField("introduce", userModel.getIntroduce());
        jsonGenerator.writeEndObject();

    }
}
