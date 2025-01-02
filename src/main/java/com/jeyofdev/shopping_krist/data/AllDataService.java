package com.jeyofdev.shopping_krist.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.ToString;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@ToString
public class AllDataService {
    public AllDataResponse getAllDatas() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ClassPathResource resource = new ClassPathResource("data.json");

        return objectMapper.readValue(resource.getFile(), AllDataResponse.class);
    }
}
