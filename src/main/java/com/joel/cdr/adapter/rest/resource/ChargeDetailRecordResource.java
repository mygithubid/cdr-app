package com.joel.cdr.adapter.rest.resource;

import com.joel.cdr.adapter.rest.resource.model.ChargeDetailRecord;
import com.joel.cdr.domain.usecase.cdr.create.Create;
import com.joel.cdr.domain.usecase.cdr.findbycar.FindByCar;
import com.joel.cdr.domain.usecase.cdr.findbyid.FindById;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/cdr")
public class ChargeDetailRecordResource {

    private final FindById findById;
    private final Create create;
    private final FindByCar findByCar;

    public ChargeDetailRecordResource(
            FindById findById,
            Create create,
            FindByCar findByCar) {
        this.findById = findById;
        this.create = create;
        this.findByCar = findByCar;
    }

    @GetMapping("/{id}")
    public ChargeDetailRecord getRecord(@PathVariable("id") long id) {
        var record = findById.query(id);
        if (record.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "cdr #" + id + " not found"
            );
        }

        return mapToRecord(record.orElseThrow());
    }

    @PostMapping
    public ChargeDetailRecord createRecord(@RequestBody ChargeDetailRecord request) {
        var recordRequest = new com.joel.cdr.domain.usecase.cdr.model.ChargeDetailRecord(
                null,
                request.carId(),
                request.startTime(),
                request.endTime()
        );

        var user = create.execute(recordRequest);
        return mapToRecord(user);
    }

    @GetMapping("/bycar/{carId}")
    public List<ChargeDetailRecord> getRecordsByCar(@PathVariable("carId") String carId) {
        var records = findByCar.query(carId);
        return records.stream().map(this::mapToRecord).toList();
    }

    private ChargeDetailRecord mapToRecord(com.joel.cdr.domain.usecase.cdr.model.ChargeDetailRecord chargeDetailRecord) {
        return new ChargeDetailRecord(
                chargeDetailRecord.id(),
                chargeDetailRecord.carId(),
                chargeDetailRecord.startTime(),
                chargeDetailRecord.endTime()
        );
    }
}
