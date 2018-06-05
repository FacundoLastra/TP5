package com.tp5.tp5.Services;

import com.tp5.tp5.Models.Cabin;
import com.tp5.tp5.Repository.CabinRepository;
import com.tp5.tp5.payload.response.CabinResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CabinService {

    @Autowired
    CabinRepository cabinRepository;

    public Cabin saveCabin(String name){
        Optional<Cabin> cabinOptional = this.cabinRepository.findByName(name);

        if (!cabinOptional.isPresent()){
            this.cabinRepository.save(new Cabin(name));
        }
        return this.cabinRepository.findByName(name).get();
    }

    public void modifyCabin(long id,String name){

        Cabin cabin= this.cabinRepository.findById(id).get();

        if (cabin != null){
            cabin.setName(name);
            this.cabinRepository.save(cabin);
        }
    }

    public void deleteCabin (long id){

        this.cabinRepository.deleteById(id);
    }

    public List getAllCabins(){
        List<CabinResponse> response = new ArrayList<>();
        this.cabinRepository.findAll().forEach(c->response.add(new CabinResponse(c)));
        return response;
    }

    public Cabin getbyId(long id){

        return this.cabinRepository.findById(id).get();
    }


}
