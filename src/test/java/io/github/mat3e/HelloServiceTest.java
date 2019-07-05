package io.github.mat3e;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HelloServiceTest {
    private HelloService SUT = new HelloService(); //system under test

    @Test
    public void test_prepareGreeting_null_returnsGreetingWithFallback() {

        // given
        String name = null;

        //when
        var result = SUT.prepareGreeting(null);

        //then
        assertEquals("hello " + HelloService.FALLBACK_NAME + "?!", result);
    }

    @Test
    public void test_prepareGreeting_name_returnsGreetingWithName() {

        // given
        var name = "test";

        //when
        var result = SUT.prepareGreeting(name);

        //then
        assertEquals("hello " + name + "!", result);
    }
}
