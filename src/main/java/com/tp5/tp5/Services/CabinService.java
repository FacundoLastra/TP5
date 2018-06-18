package com.tp5.tp5.Services;

import com.models.Models.Cabin;
import com.models.payload.response.CabinResponse;
import com.tp5.tp5.Repository.CabinRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service @AllArgsConstructor
public class CabinService {

    @Autowired
    CabinRepository cabinRepository;

    public Cabin saveCabin(String name) {

        Optional<Cabin> cabin = this.cabinRepository.findByName(name);
        Cabin newCabin = null;

        if (!cabin.isPresent()){

            newCabin = this.cabinRepository.save(new Cabin(name));
        }

        return newCabin; //Ver que queremos que retorne....
    }

    public HttpStatus modifyCabin(long id, String name) {

        Optional<Cabin> cabin = this.cabinRepository.findById(id);

        if (cabin.isPresent()){

            cabin.get().setName(name);
            this.cabinRepository.save(cabin.get());
        }

        return HttpStatus.valueOf ((cabin.isPresent()==true) ? (HttpServletResponse.SC_OK) : (HttpServletResponse.SC_CREATED));
    }

    public HttpStatus deleteCabin (long id) {

        Optional<Cabin> cabin = this.cabinRepository.findById(id);

        if (cabin.isPresent()){

            this.cabinRepository.deleteById(id); }

        return HttpStatus.valueOf ((cabin.isPresent()==true) ? (HttpServletResponse.SC_OK) : (HttpServletResponse.SC_CREATED));
    }

    public List getAllCabins() {

        List<CabinResponse> response = new ArrayList<>();
        this.cabinRepository.findAll().forEach(c->response.add(new CabinResponse(c)));

        return response;
    }

    public Cabin getById(long id){

        Optional<Cabin> cabin = this.cabinRepository.findById(id);

        return cabin.get(); //Si es null la controladora en este caso debe crear el HTTP status.
    }


}
