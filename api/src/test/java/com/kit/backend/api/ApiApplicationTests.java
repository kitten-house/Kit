package com.kit.backend.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kit.backend.api.entity.Address;
import com.kit.backend.api.entity.House;
import com.kit.backend.api.repo.AddressRepo;
import com.kit.backend.api.repo.HousesRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.MOCK,
    classes = ApiApplication.class
)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
class ApiApplicationTests {

    @Autowired
    public HousesRepo repo;

    @Autowired
    public AddressRepo addressRepo;

    @Autowired
    public ARepo aRepo;

    public ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void before() {
        repo.deleteAll();
        addressRepo.deleteAll();
    }

    @Test
    void contextLoads() throws JsonProcessingException {
//        ClassLoader classLoader = getClass().getClassLoader();
//        File file = new File(classLoader.getResource("mock/house.xml").getFile());

//        Amenities a = new Amenities(1, true, false, true, false, true, true);
        String textJson = "{\"id\":1,\"id_user\":12,\"property_type\":\"house\",\"price\":100,\"house_area\":32.0,\"room\":4,\"amenities\":{\"id\":1,\"wi_fi\":true,\"air_conditioning\":false,\"washing_machine\":true,\"heating\":false,\"pool\":true,\"indoor_fireplace\":true},\"address\":{\"id\":1,\"country\":\"Georgia\",\"city\":\"Tbilisy\",\"street\":\"Chavchavadze\",\"house_number\":\"52\",\"apartment\":\"140\"}}";

//        System.out.println(objectMapper.writeValueAsString(a));
//        Address address = new Address(1, "Georgia", "Tbilisy", "Chavchavadze", "52", "140");
//        House house = new House(1, 12,"house",100,32,4, 1, address);

        House house = objectMapper.readValue(textJson, House.class);
        System.out.println(house);
        String data = objectMapper.writeValueAsString(house);
        assertEquals(data, textJson);

        repo.save(house);

        House newHouse = repo.findById(1).get();
        assertEquals(repo.count(), 1);
        assertEquals(newHouse, house);

        Address address = addressRepo.findById(1).get();
        assertEquals(address, house.getAddress());

        System.out.println(repo.findAll(Pageable.ofSize(20)).get().collect(Collectors.toList()));

    }
}
