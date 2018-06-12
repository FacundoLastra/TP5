package com.tp5.tp5.Controllers;

import com.tp5.tp5.Services.StateServices;
import com.tp5.tp5.payload.request.StateRequest;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
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
    public  int saveState(@RequestBody StateRequest stateRequest) {

        int res = HttpServletResponse.SC_BAD_REQUEST;

        try{

            if (this.stateServices.saveState(stateRequest.getIata_code(), stateRequest.getName(), stateRequest.getCountryCode())!=null){
                res = HttpServletResponse.SC_ACCEPTED;
            }

        }catch (Exception e) {

        }

        return res;
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
