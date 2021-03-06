package com.softwarepassion.sfs.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MainControllerTest {

    private static final String USER = "kgrajek@gmail.com";
    private static final String PASS = "123456";
    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void accessUnprotected() throws Exception {
        this.mockMvc.perform(get("/index")).andExpect(status().isOk());
    }

    @Test
    public void accessProtectedRedirectsToLogin() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get("/admin/index"))
                .andExpect(status().is3xxRedirection())
                .andReturn();

        assertThat(mvcResult.getResponse().getRedirectedUrl()).endsWith("/login");
    }

    @Test
    public void loginUser() throws Exception {
        this.mockMvc.perform(formLogin().user(USER).password(PASS))
                .andExpect(authenticated());
    }

    @Test
    public void loginInvalidUser() throws Exception {
        this.mockMvc.perform(formLogin().user("invalid").password("invalid"))
                .andExpect(unauthenticated())
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void loginUserAccessProtected() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(formLogin().user(USER).password(PASS))
                .andExpect(authenticated())
                .andReturn();

        MockHttpSession httpSession = MockHttpSession.class.cast(mvcResult.getRequest().getSession(false));

        this.mockMvc.perform(get("/user/index")
                .session(httpSession))
                .andExpect(status().isOk());
    }

    @Test
    public void loginUserValidateLogout() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(formLogin().user(USER).password(PASS))
                .andExpect(authenticated())
                .andReturn();

        MockHttpSession httpSession = MockHttpSession.class.cast(mvcResult.getRequest().getSession(false));

        this.mockMvc.perform(post("/logout").with(csrf())
                .session(httpSession))
                .andExpect(unauthenticated());

        this.mockMvc.perform(get("/user/index")
                .session(httpSession))
                .andExpect(unauthenticated())
                .andExpect(status().is3xxRedirection());
    }
}