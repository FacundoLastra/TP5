package com.tp5.tp5.Services;

import com.tp5.tp5.Models.Cabin_Route;
import com.tp5.tp5.Models.Price;
import com.tp5.tp5.Models.Route;
import com.tp5.tp5.Repository.PriceRepository;
import com.tp5.tp5.payload.response.PriceResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service @AllArgsConstructor
public class PriceService {

    @Autowired
    private PriceRepository priceRepository;
    @Autowired
    private CabinService cabinService;
    @Autowired
    private RouteService routeService;

    public HttpStatus savePrice(long idCabin,long idRoute, float price, String desde, String hasta) {

        Optional<Cabin_Route> cabinRoute = Optional.empty();
        Optional<Route> route = this.routeService.getById(idRoute);
        Price rtn = null;

        if (route.isPresent()) {

            cabinRoute = route.get().getCabinRouteSet().stream().filter(x -> x.getCabin().getId() == idCabin).findFirst();
        }

        if (cabinRoute.isPresent()) {

            rtn = this.priceRepository.save(new Price(price, desde, hasta, cabinRoute.get()));
        }

        return HttpStatus.valueOf ((rtn != null) ? (HttpServletResponse.SC_OK) : (HttpServletResponse.SC_BAD_REQUEST));
    }

    public void deletePrice(long idPrice){
        this.priceRepository.deleteById(idPrice);
    }

    public Price updatePrice(long priceId,float price, String desde, String hasta){
        Optional<Price> priceToUpdate = this.priceRepository.findById(priceId);
        if (priceToUpdate.isPresent()){
            priceToUpdate.get().setPrice(price);
            priceToUpdate.get().setDesde(desde);
            priceToUpdate.get().setHasta(hasta);
            this.priceRepository.save(priceToUpdate.get());
        }

        return priceToUpdate.isPresent()?priceToUpdate.get():null;
    }
    public List getAllPrices(){
        List<PriceResponse> response = new ArrayList<>();
        this.priceRepository.findAll().forEach(c->response.add(new PriceResponse(c)));
        return response;
    }


}
