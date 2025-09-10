package controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.intesi.usermanagement.UserManagementApplication;
import com.intesi.usermanagement.domain.UserDetail;
import com.intesi.usermanagement.repository.UserDetailsRepository;
import com.intesi.usermanagement.service.UserDetailService;
import com.intesi.usermanagement.service.dto.UserDetailDTO;
import com.intesi.usermanagement.service.dto.UserDetailUpdateDTO;

import service.TestUtil;


/**
 * Integration tests for the {@link FloorResource} REST controller.
 */
@SpringBootTest(classes = UserManagementApplication.class)
@AutoConfigureMockMvc
public class UserDetailsControllerTest {
	
	private final String NOME="Pippo";
	private final String COGNOME="Pluto";
	private final String EMAIL="pippo.pluto@gmail.com";
	private final String CF="DNTCRL65S67M126L";
	private final String USR="usertest";
	private final String pathApi="/api/v1/users";
	
	
    
    @MockitoBean
    private UserDetailService userDetailService;
    
    @Autowired
    private UserDetailsRepository userDetailRepository;

    @Autowired
    private MockMvc restMockMvc;


	UserDetail userDetail;
	UserDetailDTO userDetailDTO;
	UserDetailUpdateDTO userDetailUpdateDTO;
	
	@BeforeEach
	public void initTest() {
		userDetailUpdateDTO = new UserDetailUpdateDTO();
		userDetailUpdateDTO.setUsername(USR);
		userDetailUpdateDTO.setCodiceFiscale(CF);
		userDetailUpdateDTO.setNome(NOME);
		userDetailUpdateDTO.setCognome(COGNOME);
		userDetailUpdateDTO.setEmail(EMAIL);
    	
    	userDetailDTO = new UserDetailDTO();
    	userDetailDTO.setUsername(USR);
    	userDetailDTO.setCodiceFiscale(CF);
    	userDetailDTO.setNome(NOME);
    	userDetailDTO.setCognome(COGNOME);
    	userDetailDTO.setEmail(EMAIL);
    	
    	userDetail = new UserDetail();
    	userDetail.setUsername(USR);
    	userDetail.setCodiceFiscale(CF);
    	userDetail.setNome(NOME);
    	userDetail.setCognome(COGNOME);
    	userDetail.setEmail(EMAIL);

	}
	
	
	
	@Test
	@Transactional
	public void whenDeleteAuserDetail_shouldRespondOk() throws Exception {
		userDetailDTO.setId(1L);
	    
	    Mockito
	            .when(userDetailService.findById(Mockito.anyLong()))
	            .thenReturn(Optional.of(userDetailDTO));

	    Mockito.doNothing().when(userDetailService).deleteUser(Mockito.anyLong());
	    
		restMockMvc
		        .perform(delete(pathApi+"/delete/{id}", userDetailDTO.getId())
        				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
    @Test
    @Transactional
    public void whenDeleteAuserDetail_ifIdIsBad_shouldRespondBadRequest() throws Exception {
    	userDetailDTO.setId(0L);
	    
	    Mockito
	            .when(userDetailService.findById(Mockito.anyLong()))
	            .thenReturn(Optional.of(userDetailDTO));

	    Mockito.doNothing().when(userDetailService).deleteUser(Mockito.anyLong());
	    
		restMockMvc
		        .perform(delete(pathApi+"/delete/{id}", userDetailDTO.getId())
        				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());;
    	
    }
    
    @Test
    @Transactional
    public void whenDeleteAuserDetail_ifNotExist_shouldRespondNotFound() throws Exception {
    	userDetailDTO.setId(1L);
	    
	    Mockito
	            .when(userDetailService.findById(Mockito.anyLong()))
	            .thenReturn(Optional.empty());

	    Mockito.doNothing().when(userDetailService).deleteUser(Mockito.anyLong());
	    
	    
		restMockMvc
		        .perform(delete(pathApi+"/delete/{id}", userDetailDTO.getId())
        				.accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void whenDeleteAuserDetail_ifDeleteFails_shouldRespondForbidden() throws Exception {
    	userDetailDTO.setId(1L);
	    
	    Mockito
	            .when(userDetailService.findById(Mockito.anyLong()))
	            .thenReturn(Optional.of(userDetailDTO));
	    
	    Mockito.doThrow(new RuntimeException()).when(userDetailService).deleteUser(Mockito.anyLong());
        
	    restMockMvc
        .perform(delete(pathApi+"/delete/{id}", userDetailDTO.getId())
				.accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

	@Test
	@Transactional
	public void whenCreateAuserDetail_shouldRespondCreated() throws Exception {
	    
	    Mockito
	            .when(userDetailService.saveUser(Mockito.any()))
	            .thenReturn(ResponseEntity.ok().body(Boolean.TRUE));
	    
		restMockMvc
		        .perform(post(pathApi+"/create")
    	                .contentType(MediaType.APPLICATION_JSON)
    	                .content(TestUtil.toJson(userDetailDTO)))
		        .andExpect(status().isOk());
	}
	
    @Test
    @Transactional
    public void whenCreateAuserDetail_ifCreateFails_shouldRespondInternalServerError() throws Exception {
        userDetail.setId(1L);
        
        Mockito
                .when(userDetailService.saveUser(Mockito.any()))
                .thenReturn(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Boolean.FALSE));
        
        restMockMvc
                .perform(post(pathApi+"/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtil.toJson(userDetailDTO)))
                .andExpect(status().isInternalServerError());
    }

	@Test
	@Transactional
	public void whenFindAuserDetailById_shouldRespondOk() throws Exception {
		userDetailDTO.setId(1L);
	    
	    Mockito
	            .when(userDetailService.findById(Mockito.anyLong()))
	            .thenReturn(Optional.of(userDetailDTO));

		restMockMvc
		        .perform(get(pathApi+"/{id}", userDetailDTO.getId())
        				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
    @Test
    @Transactional
    public void whenFindAuserDetailById_ifIdIsBad_shouldRespondBadRequest_1() throws Exception {
    	userDetailDTO.setId(0L);
        
        Mockito
                .when(userDetailService.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(userDetailDTO));

        restMockMvc
                .perform(get(pathApi+"/{id}", userDetailDTO.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
    
    @Test
    @Transactional
    public void whenFindAuserDetailById_ifIdIsBad_shouldRespondBadRequest_2() throws Exception {
    	userDetailDTO.setId(Long.MAX_VALUE + 1);
        
        Mockito
        .when(userDetailService.findById(Mockito.anyLong()))
        .thenReturn(Optional.of(userDetailDTO));

        restMockMvc
        .perform(get(pathApi+"/{id}", userDetailDTO.getId())
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
    }
    
    @Test
    @Transactional
    public void whenFindAuserDetailById_IfNotExist_shouldRespondNotFound() throws Exception {
    	userDetailDTO.setId(1L);
        
        Mockito
                .when(userDetailService.findById(Mockito.anyLong()))
                .thenReturn(Optional.empty());

        restMockMvc
                .perform(get(pathApi+"/{id}", userDetailDTO.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

	@Test
	@Transactional
	public void whenUpdateAuserDetail_shouldRespondOk() throws Exception {
		userDetailUpdateDTO.setId(1L);
	    
	    Mockito
	            .when(userDetailService.findById(Mockito.anyLong()))
	            .thenReturn(Optional.of(userDetailDTO));
	    
	    Mockito.when(userDetailService.updateUser(Mockito.eq(1l), Mockito.any()))
        .thenReturn(userDetailDTO); 
		
		restMockMvc
		        .perform(put(pathApi+"/update/{id}",userDetailUpdateDTO.getId())
                        .contentType(MediaType.APPLICATION_JSON)
        				.content(TestUtil.toJson(userDetailUpdateDTO)))
		        .andExpect(status().isOk());
	}
	
    @Test
    @Transactional
    public void whenUpdateAuserDetail_ifIdIsBad_shouldRespondBadRequest() throws Exception {
    	userDetailUpdateDTO.setId(0L);
    	
    	Mockito
        .when(userDetailService.findById(Mockito.anyLong()))
        .thenReturn(Optional.of(userDetailDTO));

    	Mockito.when(userDetailService.updateUser(Mockito.eq(1l), Mockito.any()))
    	.thenReturn(userDetailDTO); 

    	restMockMvc
        .perform(put(pathApi+"/update/{id}",userDetailUpdateDTO.getId())
                .contentType(MediaType.APPLICATION_JSON)
				.content(TestUtil.toJson(userDetailUpdateDTO)))
                .andExpect(status().isBadRequest());
    }
    
    @Test
    @Transactional
    public void whenUpdateAuserDetail_ifIdIsNull_shouldRespondNotFound() throws Exception {
    	userDetailUpdateDTO.setId(1L);
    	
    	Mockito
    	.when(userDetailService.findById(Mockito.anyLong()))
    	.thenReturn(Optional.empty());
    	
    	Mockito.when(userDetailService.updateUser(Mockito.anyLong(), Mockito.any()))
    	.thenReturn(userDetailDTO); 
    	
    	restMockMvc
    	.perform(put(pathApi+"/update/{id}",userDetailUpdateDTO.getId())
    			.contentType(MediaType.APPLICATION_JSON)
    			.content(TestUtil.toJson(userDetailUpdateDTO)))
    	.andExpect(status().isNotFound());
    }
    
    @Test
    @Transactional
    public void whenUpdateAFloor_ifUpdateFails_shouldRespondForbidden() throws Exception {
    	userDetailUpdateDTO.setId(1L);
    	
    	Mockito
        .when(userDetailService.findById(Mockito.anyLong()))
        .thenReturn(Optional.of(userDetailDTO));

    	Mockito.when(userDetailService.updateUser(Mockito.anyLong(), Mockito.any()))
    	.thenReturn(null); 

    	restMockMvc
        .perform(put(pathApi+"/update/{id}",userDetailUpdateDTO.getId())
                .contentType(MediaType.APPLICATION_JSON)
				.content(TestUtil.toJson(userDetailUpdateDTO)))
                .andExpect(status().isForbidden());
    }

	@Test
	@Transactional
	public void whenFinduserDetails_shouldRespondOk() throws Exception {
	    
	    Mockito
	            .when(userDetailService.list())
	            .thenReturn(Arrays.asList(userDetailDTO));
	    
		restMockMvc
		        .perform(get(pathApi)
        				.accept(MediaType.APPLICATION_JSON))
		        .andExpect(status().isOk());
	}
}
