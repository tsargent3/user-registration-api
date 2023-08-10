package com.example.userregistrationapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.userregistrationapi.model.UserRegistrationRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class UserRegistrationApiApplicationTests {

    @Autowired
    private MockMvc mockMvc;

	@Test
	public void testValidUserRegistration() throws Exception {
		performValidUserRegistration("tsargent", "StrongP#ss123", "100.42.20.0"); // Toronto IP found on web
	}

	@Test
	public void testWeakPassword() throws Exception {
		performInvalidUserRegistration("tsargent", "weakpass", "100.42.20.0");
	}

	@Test
	public void testNonCanadianIP() throws Exception {
		performInvalidUserRegistration("tsargent", "StrongP#ss123", "1.2.3.4");
	}

	@Test
	public void testBlankUsername() throws Exception {
		performInvalidUserRegistration("", "StrongP#ss123", "100.42.20.0");
	}

	@Test
	public void testBlankPassword() throws Exception {
		performInvalidUserRegistration("tsargent", "", "100.42.20.0");
	}

	@Test
	public void testBlankIp() throws Exception {
		performInvalidUserRegistration("tsargent", "StrongP#ss123", "");
	}

	private ResultActions performValidUserRegistration(String username, String password, String ipAddress) throws Exception {
		// Create a valid registration payload
		UserRegistrationRequest request = new UserRegistrationRequest();
		request.setUsername(username);
		request.setPassword(password);
		request.setIpAddress(ipAddress);

		// Perform the registration and return ResultActions
		return mockMvc.perform(MockMvcRequestBuilders.post("/api/register")
			.contentType(MediaType.APPLICATION_JSON)
			.content(new ObjectMapper().writeValueAsString(request)))
			.andExpect(status().isOk());
	}

	private ResultActions performInvalidUserRegistration(String username, String password, String ipAddress) throws Exception {
		// Create an invalid registration payload
		UserRegistrationRequest request = new UserRegistrationRequest();
		request.setUsername(username);
		request.setPassword(password);
		request.setIpAddress(ipAddress);

		// Perform the registration and return ResultActions
		return mockMvc.perform(MockMvcRequestBuilders.post("/api/register")
			.contentType(MediaType.APPLICATION_JSON)
			.content(new ObjectMapper().writeValueAsString(request)))
			.andExpect(status().isBadRequest());
	}

}
