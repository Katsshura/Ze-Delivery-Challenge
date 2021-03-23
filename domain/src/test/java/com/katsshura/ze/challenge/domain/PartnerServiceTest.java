package com.katsshura.ze.challenge.domain;

import com.katsshura.ze.challenge.domain.interfaces.PartnerDataManagement;
import com.katsshura.ze.challenge.domain.mock.PartnerDataManagementMock;
import com.katsshura.ze.challenge.domain.models.geographical.Coordinate;
import com.katsshura.ze.challenge.domain.services.PartnerService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class PartnerServiceTest {

    private static PartnerDataManagement partnerDataManagement;

    @BeforeAll
    static void setUp() {
        partnerDataManagement = new PartnerDataManagementMock();
    }


    @DisplayName("Should assert True for all coordinates")
    @Test
    void testTrueIsCoordinateInsidePolygon() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        var service = new PartnerService(partnerDataManagement);
        var privateMethod = service.getClass().getDeclaredMethod("isCoordinateInsidePolygon", int.class, double[].class, double[].class, Coordinate.class);
        privateMethod.setAccessible(true);
        var totalVertices = 4;
        var xCord = new double[] { 1, 1, 2, 2 };
        var yCord = new double[] { 1, 2, 2, 1 };
        var cord = new Coordinate(1.5, 1.5);
        var cord2 = new Coordinate(1.2, 1.9);
        var cord4 = new Coordinate(1.5, 2);
        var cord7 = new Coordinate(1.5, 2);

        var res = (boolean)privateMethod.invoke(service, totalVertices, xCord, yCord, cord);
        var res2 = (boolean)privateMethod.invoke(service, totalVertices, xCord, yCord, cord2);
        var res4 = (boolean)privateMethod.invoke(service, totalVertices, xCord, yCord, cord4);
        var res7 = (boolean)privateMethod.invoke(service, totalVertices, xCord, yCord, cord7);

        assertAll(
                () -> assertTrue(res),
                () -> assertTrue(res2),
                () -> assertTrue(res4),
                () -> assertTrue(res7)
        );

    }

    @DisplayName("Should assert False for all coordinates")
    @Test
    void testFalseIsCoordinateInsidePolygon() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        var service = new PartnerService(partnerDataManagement);
        var privateMethod = service.getClass().getDeclaredMethod("isCoordinateInsidePolygon", int.class, double[].class, double[].class, Coordinate.class);
        privateMethod.setAccessible(true);
        var totalVertices = 4;
        var xCord = new double[] { 1, 1, 2, 2 };
        var yCord = new double[] { 1, 2, 2, 1 };
        var cord3 = new Coordinate(0, 1.9);
        var cord5 = new Coordinate(1.5, 2.2);
        var cord6 = new Coordinate(3, 5);

        var res3 = (boolean)privateMethod.invoke(service, totalVertices, xCord, yCord, cord3);
        var res5 = (boolean)privateMethod.invoke(service, totalVertices, xCord, yCord, cord5);
        var res6 = (boolean)privateMethod.invoke(service, totalVertices, xCord, yCord, cord6);

        assertAll(
                () -> assertFalse(res3),
                () -> assertFalse(res5),
                () -> assertFalse(res6)
        );

    }

    @DisplayName("Given a Set of coordinates should return a double array only with X elements")
    @Test
    void testXCoords() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        var service = new PartnerService(partnerDataManagement);
        var privateMethod = service.getClass().getDeclaredMethod("getXCoordinates", Set.class);
        privateMethod.setAccessible(true);

        var coordinates = Arrays.asList(
                new Coordinate(30, 20),
                new Coordinate(45, 40),
                new Coordinate(10, 45)
        );

        var setCoords = new HashSet<>(coordinates);

        var res = (double[])privateMethod.invoke(service, setCoords);

        var resultHashSet = Arrays.stream(res).boxed().collect(Collectors.toCollection(HashSet::new));

        assertAll(
                () -> assertTrue(resultHashSet.contains(30d)),
                () -> assertTrue(resultHashSet.contains(45d)),
                () -> assertTrue(resultHashSet.contains(10d))
        );
    }

    @DisplayName("Given a Set of coordinates should return a double array only with Y elements")
    @Test
    void testYCoords() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        var service = new PartnerService(partnerDataManagement);
        var privateMethod = service.getClass().getDeclaredMethod("getYCoordinates", Set.class);
        privateMethod.setAccessible(true);

        var coordinates = Arrays.asList(
                new Coordinate(30, 20),
                new Coordinate(45, 40),
                new Coordinate(10, 45)
        );

        var setCoords = new HashSet<>(coordinates);

        var res = (double[])privateMethod.invoke(service, setCoords);
        var resultHashSet = Arrays.stream(res).boxed().collect(Collectors.toCollection(HashSet::new));

        assertAll(
                () -> assertTrue(resultHashSet.contains(20d)),
                () -> assertTrue(resultHashSet.contains(40d)),
                () -> assertTrue(resultHashSet.contains(45d))
        );
    }

    @DisplayName("Should return the exact distance for the given coordinates")
    @Test
    void testCalculationOfDistanceBetweenCoordinates() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        var service = new PartnerService(partnerDataManagement);
        var privateMethod = service.getClass().getDeclaredMethod("calculateDistanceBetweenCoordinates", Coordinate.class, Coordinate.class);
        privateMethod.setAccessible(true);

        var coordinate1 = new Coordinate(20,40);
        var coordinate2 = new Coordinate(40,40);
        var coordinate3 = new Coordinate(10,30);
        var coordinate4 = new Coordinate(100,68);

        var expectedResult1 = 20d;
        var expectedResult2 = 97.6933979345585;

        var res = (double)privateMethod.invoke(service, coordinate1, coordinate2);
        var res2 = (double)privateMethod.invoke(service, coordinate3, coordinate4);

        assertAll(
                () -> assertEquals(expectedResult1, res),
                () -> assertEquals(expectedResult2, res2)
        );

    }

    @DisplayName("Given Coordinates Should Return Partner With Id 1")
    @Test
    void testNearestPartnerGivenCoordinateP1() {
        var service = new PartnerService(partnerDataManagement);

        var coordinate1 = new Coordinate(15, 44);
        var coordinate2 = new Coordinate(20, 29);
        var coordinate3 = new Coordinate(30, 20);

        var expectedResult = partnerDataManagement.find("1");

        var result1 = service.getNearestPartnerBasedOnLocation(coordinate1);
        var result2 = service.getNearestPartnerBasedOnLocation(coordinate2);
        var result3 = service.getNearestPartnerBasedOnLocation(coordinate3);

        assertAll(
                () -> assertEquals(expectedResult, result1),
                () -> assertEquals(expectedResult, result2),
                () -> assertEquals(expectedResult, result3)
        );
    }

    @DisplayName("Given Coordinates Should Return Partner With Id 2")
    @Test
    void testNearestPartnerGivenCoordinateP2() {
        var service = new PartnerService(partnerDataManagement);

        var coordinate1 = new Coordinate(51, 3);
        var coordinate2 = new Coordinate(17, -8);

        var expectedResult = partnerDataManagement.find("2");

        var result1 = service.getNearestPartnerBasedOnLocation(coordinate1);
        var result2 = service.getNearestPartnerBasedOnLocation(coordinate2);

        assertAll(
                () -> assertEquals(expectedResult, result1),
                () -> assertEquals(expectedResult, result2)
        );
    }

    @DisplayName("Given Coordinate Should Return Null Partner")
    @Test
    void testNearestPartnerGivenCoordinateNull() {
        var service = new PartnerService(partnerDataManagement);
        var coordinate = new Coordinate(-81, 50);
        var result = service.getNearestPartnerBasedOnLocation(coordinate);
        assertNull(result);
    }

}
