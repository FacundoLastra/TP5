package com.tp5.tp5.Services;

import com.tp5.tp5.Models.Cabin;
import com.tp5.tp5.Repository.CabinRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest
public class TestCabinService {

    private CabinService cabinService;
    private CabinRepository cabinRepository;

    private Cabin cabinPremium;
    private Cabin cabinBasic;

    private List<Cabin> cabinList = new ArrayList<>();


    @Before
    public void config() {

        this.cabinPremium = new Cabin("Premium");
        this.cabinBasic = new Cabin("Basic");

        this.cabinRepository = mock(CabinRepository.class);
        this.cabinService = new CabinService(this.cabinRepository);

        when(this.cabinRepository.save(this.cabinPremium)).thenReturn(this.cabinPremium);
        when(this.cabinRepository.findByName("Premium")).thenReturn(java.util.Optional.of(this.cabinPremium));

        when(this.cabinRepository.save(this.cabinBasic)).thenReturn(null);
        Optional<Cabin> empty = Optional.empty();
        when(this.cabinRepository.findByName("Basic")).thenReturn(empty);

        when(this.cabinRepository.findById((long) 1)).thenReturn(empty);
        when(this.cabinRepository.findById((long) 2)).thenReturn(java.util.Optional.of(this.cabinBasic));

        when(this.cabinRepository.findAll()).thenReturn(this.cabinList);
    }

    @Test
    public void saveBadCabinTest() {
        Cabin res = this.cabinService.saveCabin("Premium");
        verify(this.cabinRepository, times(1)).findByName("Premium");
        verify(this.cabinRepository, times(0)).save(this.cabinPremium);

        Assert.assertEquals(res, null);
    }

    @Test
    public  void saveGoodCabinTest() {
        Cabin res = this.cabinService.saveCabin("Basic");
        verify(this.cabinRepository, times(1)).findByName("Basic");
        verify(this.cabinRepository, times(1)).save(this.cabinBasic);

        Assert.assertEquals(res, null);
    }


    @Test
    public void modifyBadCabinTest(){
        this.cabinService.modifyCabin((long) 1,"Premium");
        verify(this.cabinRepository, times(0)).save(this.cabinPremium);
    }

    @Test
    public void modifyGoodCabinTest(){
        this.cabinService.modifyCabin((long) 2,"Basic");
        verify(this.cabinRepository, times(1)).save(this.cabinBasic);
    }

    @Test
    public void deleteBadCabinTest() {
        this.cabinService.deleteCabin((long) 1);
        verify(this.cabinRepository, times(0)).deleteById((long) 1);
    }

    @Test
    public void deleteGoodCabinTest() {
        this.cabinService.deleteCabin((long) 2);
        verify(this.cabinRepository, times(1)).deleteById((long) 2);
    }

    @Test
    public void getAllCabinTest() {
        List res = this.cabinService.getAllCabins();
        Assert.assertEquals(this.cabinList, res);
    }

    @Test
    public void getByIdTest(){
        Cabin res = this.cabinService.getById(2);
        Assert.assertEquals(res, this.cabinBasic);
    }
}
/*

 */