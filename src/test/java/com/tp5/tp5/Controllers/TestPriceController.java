package com.tp5.tp5.Controllers;

import com.models.Models.Price;
import com.models.payload.request.PriceRequest;
import com.tp5.tp5.Services.PriceService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest
public class TestPriceController {

    private PriceService priceService;
    private PriceController priceController;
    private Price price;
    private PriceRequest priceRequest;

    private List<Price> priceList;

    @Before
    public void config(){

        this.priceService = mock(PriceService.class);
        this.priceController = new PriceController(this.priceService);
        this.price = new Price();
        this.priceRequest = new PriceRequest();

        this.priceList = new ArrayList<>();
    }

    @Test
    public void addPriceTest() {
        this.priceController.addPrice(this.priceRequest);
        verify(this.priceService, times(1)).savePrice(this.priceRequest.getIdCabin(),
                this.priceRequest.getIdRoute(), this.priceRequest.getPrice(), this.priceRequest.getDesde(),
                this.priceRequest.getHasta());
    }

    @Test
    public void deletePriceTest() {
        this.priceController.deletePrice(1);
        verify(this.priceService, times(1)).deletePrice(1);
    }

    @Test
    public void updatePriceTest() {
        this.priceController.updatePrice(this.priceRequest);
        verify(this.priceService, times(1)).updatePrice(
                this.priceRequest.getId(), this.priceRequest.getPrice(), this.priceRequest.getDesde(),
                this.priceRequest.getHasta());
    }

    @Test
    public void getAllPriceTest(){
        List rtn = this.priceController.getAllPrices();
        Assert.assertEquals(this.priceList, rtn);
        verify(this.priceService, times(1)).getAllPrices();
    }
    @Test
    public void getCabinsAndPrices(){
        String dateString = "10-05-2008";
        this.priceController.getCabinsAndPrices("EZE","FKA",dateString);
        when(this.priceService.getPriceWhitRouteAndDate("EZE","FKA",LocalDate.of(2008,05,10))).thenReturn(new ArrayList());

    }
}
