package com.bvb.erp.ServiceImplementation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bvb.erp.dao.UserRepository;
import com.bvb.erp.dto.UserDto;
import com.bvb.erp.exception.ErpException;
import com.bvb.erp.model.User;
import com.bvb.erp.service.UserService;
import com.bvb.erp.util.UserUtil;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImplementation implements UserService {

	@Autowired
	private UserRepository userRepository;

	public List<UserDto> getUsers() throws ErpException {
		try {
			List<UserDto> listOfDtos = userRepository.findAll().stream().map(UserUtil::convertUserEntityToDto)
					.collect(Collectors.toList());
			
			if(listOfDtos == null) {
				throw new ErpException("Empty list");
			}
			
			return listOfDtos;
			
		} catch (ErpException exception) {
			throw new ErpException("User doesnt exist");

		}
	}

	public String addNewUser(User user) throws ErpException {
		
		try {
			Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
			
			if (existingUser.isPresent()) {
				throw new ErpException("user already exists");
			}
			
			userRepository.save(user);
			
		} catch (Exception exception) {
			throw new ErpException("Internal server error" + exception.getMessage());
		}
		
		return "User Created Sucessfully";

	}

	@Transactional
	public String updateUser(Integer userId, UserDto dto) throws ErpException {
		
		try {
			User existingUser = userRepository.findById(userId).get();
			User user = UserUtil.convertUserDtoToEntity(dto);
			
			if (user == null || existingUser == null) {
				throw new ErpException("No data found");
			}

			if (dto.getEmail() != null && dto.getEmail() != existingUser.getEmail()) {
				existingUser.setEmail(dto.getEmail());
			}
			if (dto.getPhoneNumber() != null && dto.getPhoneNumber() != existingUser.getPhoneNumber()) {
				existingUser.setPhoneNumber(dto.getPhoneNumber());
			}

			return "User '" + existingUser.getFullName() + "' has been successfully updated";
		} catch (Exception exception) {
			throw new ErpException( "Internal server error " + exception.getMessage());
		}

	}

	public String deleteUser(Integer userId) throws ErpException {

		try {
			User existingUser = userRepository.findById(userId).get();
			
			if (existingUser == null) {
				throw new ErpException("No data found");
			}
			
			userRepository.deleteById(userId);

		} catch (Exception exception) {
			throw new ErpException( "Internal server error" + exception.getMessage());
		}
		return "User Deleted Successfully";

	}

}
