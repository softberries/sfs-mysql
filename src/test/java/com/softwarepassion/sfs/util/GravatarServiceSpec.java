package com.softwarepassion.sfs.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GravatarServiceSpec {

    @Autowired
    private GravatarService gravatarService;

    private static final String EMAIL = "krzysztof.grajek@googlemail.com";

    @Test
    public void shouldGenerateURLForGivenEmail() {
        //when
        String gravatarURL = gravatarService.getGravatarURL(EMAIL, Optional.empty());
        //then
        assert !gravatarURL.isEmpty();
    }

    @Test
    public void shouldGenerateURLForGivenEmailAndSize() {
        //when
        String gravatarURL = gravatarService.getGravatarURL(EMAIL, Optional.of(200));
        //then
        assert !gravatarURL.isEmpty();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionOnEmptyEmail() {
        //given
        String email = "";
        //when
        gravatarService.getGravatarURL(email, Optional.empty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionOnNullEmail() {
        //given
        String email = null;
        //when
        gravatarService.getGravatarURL(email, Optional.empty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionOnSizeZero() {
        //when
        gravatarService.getGravatarURL(EMAIL, Optional.of(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionOnSizeLessThanZero() {
        //when
        gravatarService.getGravatarURL(EMAIL, Optional.of(-10));
    }

}