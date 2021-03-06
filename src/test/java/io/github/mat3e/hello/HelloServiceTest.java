package io.github.mat3e.hello;

import io.github.mat3e.lang.Lang;
import io.github.mat3e.lang.LangRepository;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class HelloServiceTest {
    private final static String WELCOME = "Hello";
    private final static String FALLBACK_ID_WELCOME = "Hello";

    @Test
    public void test_prepareGreeting_nullName_returnsGreetingWithFallbackName() {
        // given
        var mockRepository = alwaysReturingHelloRepository();
        HelloService SUT = new HelloService(mockRepository); //system under test

        //when
        var result = SUT.prepareGreeting(null, -1);

        //then
        assertEquals(WELCOME + " " + HelloService.FALLBACK_NAME + "?!", result);
    }

    @Test
    public void test_prepareGreeting_name_returnsGreetingWithName() {

        // given
        var mockRepository = alwaysReturingHelloRepository();
        HelloService SUT = new HelloService(mockRepository); //system under test
        var name = "test";

        //when
        var result = SUT.prepareGreeting(name, -1);

        //then
        assertEquals(WELCOME + " " + name + "?!", result);
    }

    @Test
    public void test_prepareGreeting_nonExistingLang_returnsGreetingWithFallbackLang(){
        // given
        var mockRepository = new LangRepository() {
            @Override
            public Optional<Lang> findById(Integer id) {
                return Optional.empty();
            }
        };
        HelloService SUT = new HelloService(mockRepository); //system under test

        //when
        var result = SUT.prepareGreeting(null, -1);

        //then
        assertEquals(HelloService.FALLBACK_LANG.getWelcomeMsg() + " " + HelloService.FALLBACK_NAME + "?!", result);
    }

    @Test
    public void test_prepareGreeting_nullLang_returnsGreetingWithFallbackIdLang() {

        // given
        String fallbackIdWelcom = "Holla";
        var mockRepository = fallbackLangIdRepository();
        HelloService SUT = new HelloService(mockRepository); //system under test

        //when
        var result = SUT.prepareGreeting(null, null);

        //then
        assertEquals(FALLBACK_ID_WELCOME + " " + HelloService.FALLBACK_NAME + "?!", result);
    }

//    @Test
//    public void test_prepareGreeting_textLang_returnsGreetingWithFallbackIdLang() {
//
//        // given
//        String fallbackIdWelcom = "Holla";
//        var mockRepository = fallbackLangIdRepository();
//        HelloService SUT = new HelloService(mockRepository); //system under test
//
//        //when
//        var result = SUT.prepareGreeting(null, "abc");
//
//        //then
//        assertEquals(FALLBACK_ID_WELCOME + " " + HelloService.FALLBACK_NAME + "?!", result);
//    }

    private LangRepository fallbackLangIdRepository (){
        return new LangRepository() {
            @Override
            public Optional<Lang> findById(Integer id) {
                if (id.equals(HelloService.FALLBACK_LANG.getId())){
                    return Optional.of(new Lang(null, FALLBACK_ID_WELCOME, null));
                }
                return Optional.empty();
            }
        };
    }

    private LangRepository alwaysReturingHelloRepository() {
        return new LangRepository() {
            @Override
            public Optional<Lang> findById(Integer id) {
                return Optional.of(new Lang(null, WELCOME, null));
            }
        };
    }
}
