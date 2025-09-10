package service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import com.intesi.usermanagement.UserManagementApplication;
import com.intesi.usermanagement.domain.UserDetail;
import com.intesi.usermanagement.repository.UserDetailsRepository;
import com.intesi.usermanagement.repository.UserRoleRepository;
import com.intesi.usermanagement.service.UserDetailService;
import com.intesi.usermanagement.service.dto.UserDetailDTO;
import com.intesi.usermanagement.service.dto.UserDetailUpdateDTO;




@SpringBootTest(classes = UserManagementApplication.class)
class UserDetailsServiceTest {
	
	private final String NOME="Pippo";
	private final String COGNOME="Pluto";
	private final String EMAIL="pippo.pluto@gmail.com";
	private final String CF="DNTCRL65S67M126L";
	private final String USR="usertest";
	
	
	@Autowired
    private UserDetailsRepository userRepository;

	@Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired 
    private UserDetailService service;

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
    void updateuserDetail_ok_whenUserExists() {
	    userRepository.save(userDetail);
	    
	    UserDetailDTO cus = service.updateUser(this.userDetail.getId(), userDetailUpdateDTO);
	    
	    assertThat(cus.getEmail()).isEqualTo(userDetailUpdateDTO.getEmail());
    }
    
    @Test
    @Transactional
    void updateuserDetail_notFound_whenMissing() {
        userRepository.save(userDetail);
	    
	    UserDetailDTO cus = service.updateUser(99l, userDetailUpdateDTO);
	    
	    assertThat(cus).isNull();
    }
    

//    @Test
//    @Transactional
//    void deleteuserDetail_ok_whenExists() throws Exception {
//    	userRepository.save(userDetail);
// 	    
// 	    service.deleteUser(this.userDetail.getId());
// 	    
// 	    assertThat(isDeleted).isTrue();
//    }
    
	@Test
	@Transactional
	public void whenFindAuserDetailById_shouldReturnOk() {
		userRepository.save(userDetail);
	    
	    Optional<UserDetailDTO> cus = service.findById(this.userDetail.getId());
	    
	    assertThat(cus.isPresent()).isTrue();
	}
	
	@Test
	@Transactional
	public void whenFindAuserDetailById_isMissing() {
		userRepository.save(userDetail);
		
		Optional<UserDetailDTO> cus = service.findById(99l);
		
		assertThat(cus.isPresent()).isFalse();
	}

	@Test
	@Transactional
	public void whenCreateAuserDetail_shouldReturnOK() {
		ResponseEntity<Boolean> cus = service.saveUser(userDetailDTO);
	    
		assertThat(cus.getBody()).isTrue();
	}
}