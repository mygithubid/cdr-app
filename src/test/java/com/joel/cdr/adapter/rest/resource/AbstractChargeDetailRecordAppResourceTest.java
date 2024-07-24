package com.joel.cdr.adapter.rest.resource;

import com.joel.cdr.app.Starter;
import com.joel.cdr.domain.usecase.cdr.create.Create;
import com.joel.cdr.domain.usecase.cdr.findbycar.FindByCar;
import com.joel.cdr.domain.usecase.cdr.findbyid.FindById;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = Starter.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties")
public abstract class AbstractChargeDetailRecordAppResourceTest {
    @Autowired
    protected MockMvc mockMvc;

    @MockBean
    protected FindById findById;
    @MockBean
    protected Create create;
    @MockBean
    protected FindByCar findByCar;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }
}