package com.team6.atm.atm.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CustomSecurityTest {
    private static String PORT = "8080";
    private static String URL_BASE = "http://localhost:" + PORT;
    private static TestRestTemplate restTemplateAdmin
            = new TestRestTemplate("admin", "1");
    private static TestRestTemplate restTemplateUser
            = new TestRestTemplate("user", "1");
    private static TestRestTemplate restTemplateEmpty
            = new TestRestTemplate();

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void whenLoggedAnyUserRequestsHomePage_ThenSuccess()
            throws IllegalStateException {
        ResponseEntity<String> response
                = restTemplateEmpty.getForEntity(URL_BASE, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void whenLoggedUserRequestsAccountTransfer_ThenSuccess()
            throws IllegalStateException {
        ResponseEntity<String> response
                = restTemplateUser.getForEntity(URL_BASE + "/accounts/transfer", String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void whenLoggedAdminRequestsGetAllAccounts_ThenSuccess()
            throws IllegalStateException {
        ResponseEntity<String> response
                = restTemplateAdmin.getForEntity(URL_BASE + "/accounts/getallaccounts", String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void whenLoggedAdminRequestsAtmAdd_ThenSuccess()
            throws IllegalStateException {
        ResponseEntity<String> response
                = restTemplateAdmin.getForEntity(URL_BASE + "/atms/add", String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void whenLoggedUserRequestsAtmDeposit_ThenSuccess()
            throws IllegalStateException {
        ResponseEntity<String> response
                = restTemplateUser.getForEntity(URL_BASE + "/atms/deposit", String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void whenLoggedAdminRequestsUserAll_ThenSuccess()
            throws IllegalStateException {
        ResponseEntity<String> response
                = restTemplateAdmin.getForEntity(URL_BASE + "/users/all", String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void whenLoggedUserRequestsUserAll_ThenForbidden() throws Exception {
        mockMvc.perform(post(URL_BASE + "/users/all")
                .accept("application/json")
                .contentType("application/json")
                .with(csrf())
                .with(user("user").password("1")))
                .andExpect(status().isForbidden());
    }

    @Test
    public void whenAnyUserRequestsUserAdd_ThenForbidden() throws Exception {
        mockMvc.perform(post(URL_BASE + "/users/add")
                .accept("application/json")
                .contentType("application/json")
                .with(csrf())
                .with(user("noUser").password("wrongPassword")))
                .andExpect(status().isForbidden());
    }

    @Test
    public void whenAnyUserRequestsAccountAdd_ThenForbidden() throws Exception {
        mockMvc.perform(post(URL_BASE + "/accounts/add")
                .accept("application/json")
                .contentType("application/json")
                .with(csrf())
                .with(user("noUser").password("wrongPassword")))
                .andExpect(status().isForbidden());
    }

    @Test
    public void whenAnyUserRequestsAtmAdd_ThenForbidden() throws Exception {
        mockMvc.perform(post(URL_BASE + "/atms/add")
                .accept("application/json")
                .contentType("application/json")
                .with(csrf())
                .with(user("noUser").password("wrongPassword")))
                .andExpect(status().isForbidden());
    }

    @Test
    public void whenLoggedUserRequestsAccountAll_ThenForbidden() throws Exception {
        mockMvc.perform(post(URL_BASE + "/accounts/all")
                .accept("application/json")
                .contentType("application/json")
                .with(csrf())
                .with(user("user").password("1")))
                .andExpect(status().isForbidden());
    }

    @Test
    public void whenLoggedUserRequestsAtmAdd_ThenForbidden() throws Exception {
        mockMvc.perform(post(URL_BASE + "/atms/add")
                .accept("application/json")
                .contentType("application/json")
                .with(csrf())
                .with(user("user").password("1")))
                .andExpect(status().isForbidden());
    }
}
