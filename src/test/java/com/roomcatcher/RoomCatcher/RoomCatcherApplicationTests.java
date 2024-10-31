package com.roomcatcher.RoomCatcher;

import com.roomcatcher.RoomCatcher.domain.User;
import com.roomcatcher.RoomCatcher.domain.UserType;
import com.roomcatcher.RoomCatcher.repository.UserRepository;
import com.roomcatcher.RoomCatcher.repository.UserTypeRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserTypeRepository userTypeRepository;

	@Test
	void testCreateUser() {

		UserType userType = UserType.builder().build();
		userTypeRepository.save(userType);

		// When: User 객체 생성 후 저장
		User user = User.builder()
				.userName("5hseok")
				.userBirth("2003-10-01")
				.email("test@example.com")
				.password("password123")
				.userSex("3")
				.userImage("testProfile.jpg")
				.userlocation("서울특별시 중구")
				.userType(userType)
				.build();

		User savedUser = userRepository.save(user);

		assertThat(savedUser.getId()).isNotNull(); // ID가 자동 생성되어야 함
		assertThat(savedUser.getUserName()).isEqualTo("5hseok");
		assertThat(savedUser.getEmail()).isEqualTo("test@example.com");
		assertThat(savedUser.getUserType()).isEqualTo(userType); // 연관된 UserType 검증
	}

}
