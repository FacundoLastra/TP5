package com.tp5.tp5.Controllers;

import com.tp5.tp5.Services.StateServices;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@NoArgsConstructor
@RequestMapping("/state")
public class StateControllers {

    @Autowired
    private StateServices stateServices;


    @DeleteMapping("/")
    public void deleteState (@PathVariable Long id) {

        this.stateServices.deleteState(id);
    }

    @PutMapping("/")
    public void addState (@PathVariable String iataCode, @PathVariable String name, @PathVariable String codeCountry ) {

        this.stateServices.saveState(iataCode, name, codeCountry);
    }

    @PatchMapping("/")
    public void modifyState (@PathVariable String iataCode, @PathVariable String name, @PathVariable String codeCountry) {

        this.stateServices.modifyState(iataCode, name, codeCountry);
    }

}
