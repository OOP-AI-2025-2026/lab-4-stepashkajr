import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task3Test {
    @Nested
    @DisplayName("Tests for the third task")
    class Test {

        @org.junit.jupiter.api.Test
        @DisplayName("Test constructor 1")
        void test1() {
            ua.opnu.java.inheritance.point.Point3D point = new ua.opnu.java.inheritance.point.Point3D();

            assertEquals(0, point.getX());
            assertEquals(0, point.getY());
            assertEquals(0, point.getZ());
        }

        @org.junit.jupiter.api.Test
        @DisplayName("Test constructor 2")
        void test2() {
            ua.opnu.java.inheritance.point.Point3D point = new ua.opnu.java.inheritance.point.Point3D(0, 0, 0);

            assertEquals(0, point.getX());
            assertEquals(0, point.getY());
            assertEquals(0, point.getZ());
        }

        @org.junit.jupiter.api.Test
        @DisplayName("Test constructor 3")
        void test3() {
            ua.opnu.java.inheritance.point.Point3D point = new ua.opnu.java.inheritance.point.Point3D(1, 1, 1);

            assertEquals(1, point.getX());
            assertEquals(1, point.getY());
            assertEquals(1, point.getZ());
        }

        @org.junit.jupiter.api.Test
        @DisplayName("Test constructor 4")
        void test4() {
            ua.opnu.java.inheritance.point.Point3D point = new ua.opnu.java.inheritance.point.Point3D(-1, -1, -1);

            assertEquals(-1, point.getX());
            assertEquals(-1, point.getY());
            assertEquals(-1, point.getZ());
        }

        @org.junit.jupiter.api.Test
        @DisplayName("Test setLocation(x, y) 1")
        void test5() {
            ua.opnu.java.inheritance.point.Point3D point = new ua.opnu.java.inheritance.point.Point3D();
            point.setLocation(1, -1);

            assertEquals(1, point.getX());
            assertEquals(-1, point.getY());
            assertEquals(0, point.getZ());
        }

        @org.junit.jupiter.api.Test
        @DisplayName("Test setLocation(x, y) 2")
        void test6() {
            ua.opnu.java.inheritance.point.Point3D point = new ua.opnu.java.inheritance.point.Point3D(1, 1, 1);
            point.setLocation(0, -1);

            assertEquals(0, point.getX());
            assertEquals(-1, point.getY());
            assertEquals(0, point.getZ());
        }

        @org.junit.jupiter.api.Test
        @DisplayName("Test setLocation(x, y, z) 1")
        void test7() {
            ua.opnu.java.inheritance.point.Point3D point = new ua.opnu.java.inheritance.point.Point3D(0, 0, 0);
            point.setLocation(1, 0, 0);

            assertEquals(1, point.getX());
            assertEquals(0, point.getY());
            assertEquals(0, point.getZ());
        }

        @org.junit.jupiter.api.Test
        @DisplayName("Test setLocation(x, y, z) 2")
        void test8() {
            ua.opnu.java.inheritance.point.Point3D point = new ua.opnu.java.inheritance.point.Point3D(1, 0, 0);
            point.setLocation(0, -1, 0);

            assertEquals(0, point.getX());
            assertEquals(-1, point.getY());
            assertEquals(0, point.getZ());
        }

        @org.junit.jupiter.api.Test
        @DisplayName("Test setLocation(x, y, z) 3")
        void test9() {
            ua.opnu.java.inheritance.point.Point3D point = new ua.opnu.java.inheritance.point.Point3D(0, -1, 0);
            point.setLocation(0, 0, 3);

            assertEquals(0, point.getX());
            assertEquals(0, point.getY());
            assertEquals(3, point.getZ());
        }

        @org.junit.jupiter.api.Test
        @DisplayName("Test setLocation(x, y, z) 4")
        void test10() {
            ua.opnu.java.inheritance.point.Point3D point = new ua.opnu.java.inheritance.point.Point3D(0, 0, 3);
            point.setLocation(0, 0, 0);

            assertEquals(0, point.getX());
            assertEquals(0, point.getY());
            assertEquals(0, point.getZ());
        }

        @org.junit.jupiter.api.Test
        @DisplayName("Test getXYZ() 1")
        void test11() {
            ua.opnu.java.inheritance.point.Point3D point = new ua.opnu.java.inheritance.point.Point3D(-3, 6, 3);

            assertEquals(-3, point.getX());
            assertEquals(6, point.getY());
            assertEquals(3, point.getZ());
        }

        @org.junit.jupiter.api.Test
        @DisplayName("Test distanceFromOrigin() 1")
        void test12() {
            ua.opnu.java.inheritance.point.Point3D point = new ua.opnu.java.inheritance.point.Point3D(0, 0, 0);

            double expected = 0.0;
            assertEquals(expected, point.distanceFromOrigin());
        }

        @org.junit.jupiter.api.Test
        @DisplayName("Test distanceFromOrigin() 2")
        void test13() {
            ua.opnu.java.inheritance.point.Point3D point = new ua.opnu.java.inheritance.point.Point3D(1, 0, 0);

            double expected = 1.0;
            assertEquals(expected, point.distanceFromOrigin());
        }

        @org.junit.jupiter.api.Test
        @DisplayName("Test distanceFromOrigin() 3")
        void test14() {
            ua.opnu.java.inheritance.point.Point3D point = new ua.opnu.java.inheritance.point.Point3D(1, -1, 0);

            double expected = 1.4142135623730951;
            assertEquals(expected, point.distanceFromOrigin());
        }

        @org.junit.jupiter.api.Test
        @DisplayName("Test distanceFromOrigin() 4")
        void test15() {
            ua.opnu.java.inheritance.point.Point3D point = new ua.opnu.java.inheritance.point.Point3D(-2, 2, 1);

            double expected = 3.0;
            assertEquals(expected, point.distanceFromOrigin());
        }

        @org.junit.jupiter.api.Test
        @DisplayName("Test distanceFromOrigin() 5")
        void test16() {
            ua.opnu.java.inheritance.point.Point3D point = new ua.opnu.java.inheritance.point.Point3D(3, 3, 3);

            double expected = 5.196152422706632;
            assertEquals(expected, point.distanceFromOrigin());
        }
    }
}
