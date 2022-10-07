package com.api.board.mapper.controller;
 
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.api.board.domain.BoardForm;
import com.api.board.domain.UploadFiles;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebAppConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardControllerExceptionTest {
 
    Logger logger = LoggerFactory.getLogger(BoardControllerExceptionTest.class);
 
    private MockMvc mockMvc;
 
    @Autowired
    WebApplicationContext webApplicationContext;

	public String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}

	public <T> T mapFromJson(String json, Class<T> clazz) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, clazz);
	}
  
    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
        						 .addFilters(new CharacterEncodingFilter("UTF-8", true))
        						 .alwaysDo(print())
        						 .build();
    }
 
    int boardSeq = 0;
    
    /** 게시글 상세 조회 시 응답 값이 404이면 테스트 통과 */
    @Test
    public void getBoardDetailJSON(){
 
    	try {
    		
    		if (boardSeq != 0) {
    			
    			MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/boards/" + boardSeq)
						 .accept(MediaType.APPLICATION_XML))
						 .andReturn();

    			assertEquals(404, mvcResult.getResponse().getStatus());	
    		}
    		
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
 
    /** 게시글 상세 조회 시 응답 값이 404이면 테스트 통과 */
    @Test
    public void getBoardDetailXML() throws Exception {
        
    	try {
    		
    		if (boardSeq != 0) {
    			
    			MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/boards/" + boardSeq)
						 .accept(MediaType.APPLICATION_JSON))
						 .andReturn();

    			assertEquals(404, mvcResult.getResponse().getStatus());	
    		}
    		
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }

	@Test
	public void testInsertSize() throws Exception {

		String boardWriter = "";
		String boardSubject = "";
		String boardContent = "";
		for (int i = 0; i < 1001; i++) {
			if (i < 11) {
				boardWriter += "a";
			}
			if (i < 76) {
				boardSubject += "a";
			}
			if (i < 1001) {
				boardContent += "a";
			}

		}

		if (true) {
			BoardForm boardForm = new BoardForm();
			boardForm.setBoardWriter(boardWriter);
			boardForm.setBoardSubject("a");
			boardForm.setBoardContent("a");
			List<UploadFiles> uploadFilesList = new ArrayList<>();
			UploadFiles uploadFiles = new UploadFiles("test", "Test", "Test");
			uploadFilesList.add(uploadFiles);

			boardForm.setUploadFilesList(uploadFilesList);

			MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/boards/test")
							.contentType(MediaType.APPLICATION_JSON_VALUE)
							.content(this.mapToJson(boardForm)))
					.andReturn();

			assertEquals(400, mvcResult.getResponse().getStatus());
		}

		if (true) {
			BoardForm boardForm = new BoardForm();
			boardForm.setBoardWriter("a");
			boardForm.setBoardSubject(boardSubject);
			boardForm.setBoardContent("a");
			List<UploadFiles> uploadFilesList = new ArrayList<>();
			UploadFiles uploadFiles = new UploadFiles("test", "Test", "Test");
			uploadFilesList.add(uploadFiles);

			boardForm.setUploadFilesList(uploadFilesList);

			MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/boards/test")
							.contentType(MediaType.APPLICATION_JSON_VALUE)
							.content(this.mapToJson(boardForm)))
					.andReturn();

			assertEquals(400, mvcResult.getResponse().getStatus());
		}

		if (true) {
			BoardForm boardForm = new BoardForm();
			boardForm.setBoardWriter("a");
			boardForm.setBoardSubject("a");
			boardForm.setBoardContent(boardContent);
			List<UploadFiles> uploadFilesList = new ArrayList<>();
			UploadFiles uploadFiles = new UploadFiles("test", "Test", "Test");
			uploadFilesList.add(uploadFiles);

			boardForm.setUploadFilesList(uploadFilesList);

			MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/boards/test")
							.contentType(MediaType.APPLICATION_JSON_VALUE)
							.content(this.mapToJson(boardForm)))
					.andReturn();

			assertEquals(400, mvcResult.getResponse().getStatus());
		}


	}
}
