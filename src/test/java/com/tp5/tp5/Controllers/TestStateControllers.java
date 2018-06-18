package com.tp5.tp5.Controllers;

import com.models.Models.State;
import com.models.payload.request.StateRequest;
import com.tp5.tp5.Services.StateServices;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;

@SpringBootTest
public class TestStateControllers {

    private StateControllers stateControllers;
    private StateServices stateServices;
    private State state;
    private StateRequest stateRequest;

    private List<State> stateList;

    @Before
    public void cofig(){

        this.stateServices = mock(StateServices.class);
        this.stateControllers = new StateControllers(this.stateServices);
        this.state = new State();
        this.stateRequest = new StateRequest();

        this.stateList = new ArrayList<>();
    }

    @Test
    public void deleteStateTest () {
        this.stateControllers.deleteState((long) 1);
        verify(this.stateServices, times(1)).deleteState((long) 1);
    }

    @Test
    public void addStateTest () {
        this.stateControllers.addState(this.stateRequest);
        verify(this.stateServices, times(1)).saveState(this.stateRequest.getIata_code(),
                this.stateRequest.getName(), this.stateRequest.getCountryCode());
    }

    @Test
    public void modifyStateTest() {
        this.stateControllers.modifyState(this.stateRequest);
        verify(this.stateServices, times(1)).modifyState(this.stateRequest.getIata_code(),
                this.stateRequest.getName(), this.stateRequest.getCountryCode());
    }

    @Test
    public void getAllTest() {
        List rtn = this.stateControllers.getAll();
        Assert.assertEquals(this.stateList, rtn);
        verify(this.stateServices, times(1)).getAllStates();

    }
}
