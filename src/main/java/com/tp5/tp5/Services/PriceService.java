package com.tp5.tp5.Services;

import com.tp5.tp5.Models.Cabin_Route;
import com.tp5.tp5.Models.Price;
import com.tp5.tp5.Models.Route;
import com.tp5.tp5.Repository.PriceRepository;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PriceService {

    @Autowired
    private PriceRepository priceRepository;
    @Autowired
    private CabinService cabinService;
    @Autowired
    private RouteService routeService;

    public void savePrice(long idCabin,long idRoute, float price, DateTime desde, DateTime hasta)
    {
        Route route = this.routeService.getById(idRoute);
        Optional<Cabin_Route> cabinRoute = route.getCabinRouteSet().stream().filter(x -> x.getCabin().getId() == idCabin).findFirst();
        if (cabinRoute.isPresent()) {
            this.priceRepository.save(new Price(price, desde, hasta, cabinRoute.get()));
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
