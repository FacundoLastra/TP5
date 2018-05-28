package com.tp5.tp5.Services;

import com.tp5.tp5.Models.Cabin;
import com.tp5.tp5.Repository.CabinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CabinService {

    @Autowired
    CabinRepository cabinRepository;

    public Cabin saveCabin(String name){
        Cabin verificationCabin = this.cabinRepository.findByName(name).get();

        if (verificationCabin == null){
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
        return this.cabinRepository.findAll();
    }

    public Cabin getbyId(long id){
        return this.cabinRepository.findById(id).get();
    }


}
