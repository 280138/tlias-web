package com.th3angrycalf;

import org.junit.jupiter.api.Test;

import java.util.UUID;

public class UUIDTest {
    @Test
    public void testUUID(){
        for (int i = 0; i < 1000; i++) {
            //System.out.println(UUID.randomUUID());
            System.out.println(UUID.randomUUID().toString());
        }
    }
}
