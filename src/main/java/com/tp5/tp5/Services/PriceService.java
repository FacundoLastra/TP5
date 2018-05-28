package com.tp5.tp5.Services;

import com.tp5.tp5.Models.Price;
import com.tp5.tp5.Repository.PriceRepository;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceService {

    @Autowired
    private PriceRepository priceRepository;
    @Autowired
    private CabinService cabinService;

    public void savePrice(long idCabin, float price, DateTime desde, DateTime hasta)
    {
        Price pricetoSave= new Price(price,desde,hasta,this.cabinService.getbyId(idCabin));

        if(pricetoSave.getCabin()!= null){
            this.priceRepository.save(pricetoSave);
        }

    }

    public void deletePrice(long idPrice){
        this.priceRepository.deleteById(idPrice);
    }

    public Price updatePrice(long priceId,float price, DateTime desde, DateTime hasta){
        Price priceToUpdate = this.priceRepository.findById(priceId).get();
        if (priceToUpdate != null){
            priceToUpdate.setPrice(price);
            priceToUpdate.setDesde(desde);
            priceToUpdate.setHasta(hasta);
            this.priceRepository.save(priceToUpdate);
        }

        return priceToUpdate;
    }
    public List getAllPrices(){
        return this.priceRepository.findAll();
    }


}
