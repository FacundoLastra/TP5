package com.tp5.tp5.Controllers;

import com.tp5.tp5.Services.StateServices;
import com.tp5.tp5.payload.request.StateRequest;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;

@RestController
@RequestMapping("/state")
@FieldDefaults(level = PRIVATE, makeFinal = true)
@AllArgsConstructor(access = PACKAGE)
public class StateControllers {

    @Autowired
    private StateServices stateServices;


    @DeleteMapping("{id}")
    public void deleteState (@PathVariable Long id) {

        this.stateServices.deleteState(id);
    }

    @PutMapping
    public void addState(@RequestBody StateRequest stateRequest) {

        try{

            this.stateServices.saveState(stateRequest.getIata_code(), stateRequest.getName(), stateRequest.getCountryCode());

        }catch (Exception e) {

        }
    }

    @PostMapping
    public void modifyState (@RequestBody StateRequest stateRequest) {

        try {

            this.stateServices.modifyState(stateRequest.getIata_code(), stateRequest.getName(), stateRequest.getCountryCode());

        }catch (Exception e) {

        }
    }

    @GetMapping
    public List getAll() {

        return this.stateServices.getAllStates();
    }

}
