package com.joel.cdr.adapter.rest.resource;

import com.joel.cdr.domain.usecase.cdr.model.ChargeDetailRecord;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


class ChargeDetailRecordResourceTest extends AbstractChargeDetailRecordAppResourceTest {

    @Test
    public void createRecordSuccess() throws Exception {
        var recordJson = """
                {
                  "carId": "string",
                  "startTime": "2024-01-30T23:52:31.128361",
                  "endTime": "2024-01-30T23:52:32.128362",
                  "cost": 1.0
                }
                """;
        when(create.execute(any())).thenReturn(
                new ChargeDetailRecord(
                        1L,
                        "string",
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        1.0
                )
        );
        var mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/cdr")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(recordJson))
                .andReturn();
        var status = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(), status);
    }

    @Test
    void findByIdSuccess() throws Exception {
        when(findById.query(any())).thenReturn(
                Optional.of(new ChargeDetailRecord(
                        1L,
                        "string",
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        1.0
                        )
                )
        );
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/cdr/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();

        int status = mvcResult
                .getResponse()
                .getStatus();
        assertEquals(HttpStatus.OK.value(), status);
        String content = mvcResult
                .getResponse()
                .getContentAsString();
        assertNotNull(content);
    }

    @Test
    void findByCarSuccess() throws Exception {
        when(findByCar.query(any())).thenReturn(
                List.of(new ChargeDetailRecord(
                                1L,
                                "string",
                                LocalDateTime.now(),
                                LocalDateTime.now(),
                                1.0
                        )
                )
        );
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/cdr/bycar/aCar")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();

        int status = mvcResult
                .getResponse()
                .getStatus();
        assertEquals(HttpStatus.OK.value(), status);
        String content = mvcResult
                .getResponse()
                .getContentAsString();
        assertNotNull(content);
    }
}