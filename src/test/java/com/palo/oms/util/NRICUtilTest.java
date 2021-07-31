package com.palo.oms.util;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NRICUtilTest {

    @Test
    void createRandomNRIC() {
    }

    @Test
    void isValidNRIC() {
        List<String> correctNRICcList = new ArrayList<>();
        correctNRICcList.add("G2005620W");
        correctNRICcList.add("T4027680E");
        correctNRICcList.add("S9027680S");
        correctNRICcList.add("F1034111Q");

        correctNRICcList.stream().forEach( x-> assertTrue(NRICUtil.isValidNRIC(x)));

        List<String> incorrectNRICcList = new ArrayList<>();
        correctNRICcList.add("Y2005620W");
        correctNRICcList.add("T402768E");
        correctNRICcList.add("S90276808");
        correctNRICcList.add("F_034111Q");

        incorrectNRICcList.stream().forEach( x-> assertFalse(NRICUtil.isValidNRIC(x)));
    }
}