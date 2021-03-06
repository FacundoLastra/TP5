package com.tp5.tp5.Services;

import com.models.Models.Cabin_Route;
import com.models.Models.Price;
import com.models.Models.Route;
import com.models.payload.response.PriceResponse;
import com.tp5.tp5.Repository.PriceRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
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

    public HttpStatus savePrice(long idCabin, long idRoute, float price, String desde, String hasta) {

        Optional<Cabin_Route> cabinRoute = Optional.empty();
        Optional<Route> route = this.routeService.getById(idRoute);
        Price rtn = null;

        if (route.isPresent()) {

            cabinRoute = route.get().getCabinRouteSet().stream().filter(x -> x.getCabin().getId() == idCabin).findFirst();
        }

        if (cabinRoute.isPresent()) {

            rtn = this.priceRepository.save(new Price(price, desde, hasta, cabinRoute.get()));
        }

        return HttpStatus.valueOf((rtn != null) ? (HttpServletResponse.SC_OK) : (HttpServletResponse.SC_BAD_REQUEST));
    }

    public void deletePrice(long idPrice) {
        this.priceRepository.deleteById(idPrice);
    }

    public Price updatePrice(long priceId, float price, String desde, String hasta) {
        Optional<Price> priceToUpdate = this.priceRepository.findById(priceId);
        if (priceToUpdate.isPresent()) {
            priceToUpdate.get().setPrice(price);
            priceToUpdate.get().setDesde(desde);
            priceToUpdate.get().setHasta(hasta);
            this.priceRepository.save(priceToUpdate.get());
        }

        return priceToUpdate.isPresent() ? priceToUpdate.get() : null;
    }
    public List getAllPrices() {
        List<PriceResponse> response = new ArrayList<>();
        this.priceRepository.findAll().forEach(c -> response.add(new PriceResponse(c)));
        return response;
    }

    public List getPriceWhitRouteAndDate(String iataOrigin, String iataDestination, LocalDate fecha) {
       Route route = this.routeService.getRouteByAirportIataOriginAndDestination(iataOrigin, iataDestination);
        List<PriceResponse> pricesResponse = null;
       if (route != null) {
            pricesResponse = new ArrayList<>();
           for (Cabin_Route cabinRoute: route.getCabinRouteSet()) {
               for (Price price:cabinRoute.getPriceList()) {
                   if (price.getDesde().isBefore(fecha) && price.getHasta().isAfter(fecha)) {
                       pricesResponse.add(new PriceResponse(price));
                       break;
                   }
               }
           }
       }
       return pricesResponse;
    }

}
