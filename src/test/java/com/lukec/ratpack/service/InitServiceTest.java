package com.lukec.ratpack.service;

import static org.mockito.Mockito.times;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;

import com.lukec.ratpack.bo.UserBalance;
import com.lukec.ratpack.redis.repository.UserRepository;
import com.lukec.ratpack.service.impl.InitServiceImpl;

@RunWith(JUnit4.class)
public class InitServiceTest {
    // Normally, I'd be using Mockito to model my service tests.
    // I've run out of time to do the same things in the ratpack world
    // but will post updates as soon as possible.
    @Mock
    private UserRepository repository;
    
    @InjectMocks
    @Spy
    private InitServiceImpl initService;

    @Mock
    private Optional<UserBalance> balance;
    
    final String token = "1232";
    
    @Before
    public void init() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException, ClassNotFoundException {
	Mockito.when(repository.getUserBalanceForUser(Mockito.anyString())).thenReturn(balance);
	Mockito.doNothing().when(repository).makeDefaultBalance(Mockito.anyString());
    }
    
    @Test
    public void testWhenBalanceDoesntExist() {
	Mockito.when(balance.isPresent()).thenReturn(false);
	initService.checkUserInitialised(token);
	Mockito.verify(repository, times(1)).makeDefaultBalance(Mockito.any());
    }
    
}
