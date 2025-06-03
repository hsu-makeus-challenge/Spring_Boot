package umc.spring.service;


import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.exception.ErrorStatus;
import umc.spring.apiPayload.exception.GeneralException;
import umc.spring.apiPayload.exception.handler.UserHandler;
import umc.spring.config.security.jwt.JwtTokenProvider;
import umc.spring.converter.UserConverter;
import umc.spring.converter.UserPreferConverter;
import umc.spring.domain.Category;
import umc.spring.domain.Preference;
import umc.spring.domain.Region;
import umc.spring.domain.User;
import umc.spring.repository.CategoryRepository;
import umc.spring.repository.RegionRepository;
import umc.spring.repository.UserRepository;
import umc.spring.web.dto.UserRequestDTO;
import umc.spring.web.dto.UserResponseDTO;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final RegionRepository regionRepository;
    private final PasswordEncoder passwordEncoder;

    private final JwtTokenProvider jwtTokenProvider;
    @Transactional
    @Override
    public User joinUser(UserRequestDTO.JoinDto request) {

        Region region = regionRepository.findByRegionName(request.getAddress())
                .orElseThrow(() -> new GeneralException(ErrorStatus.REGION_NOT_FOUND));
        List<Category> CategoryList = request.getPreferCategory().stream()
                .map(categoryId -> {
                    return categoryRepository.findById(categoryId).orElseThrow(() -> new GeneralException(ErrorStatus.CATEGORY_NOT_FOUND));
                }).collect(Collectors.toList());
        List<Preference> userPreferList = UserPreferConverter.toUserPreferList(CategoryList);

        User newUser = UserConverter.toNewUser(request, region, request.getSpecAddress(), userPreferList);

        newUser.encodePassword(passwordEncoder.encode(request.getPassword()));

//        List<Preference> userPreferList = UserPreferConverter.toUserPreferList(CategoryList);
//
//        //userPreferList.forEach(preference -> {preference.setUser(newUser);});

        return userRepository.save(newUser);
    }
    @Override
    public UserResponseDTO.LoginResultDTO loginUser(UserRequestDTO.LoginRequestDTO request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(()-> new UserHandler(ErrorStatus.MEMBER_NOT_FOUND));

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new UserHandler(ErrorStatus.INVALID_PASSWORD);
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                user.getEmail(), null,
                Collections.singleton(() -> user.getRole().name())
        );

        String accessToken = jwtTokenProvider.generateToken(authentication);

        return UserConverter.toLoginResultDTO(
                user.getId(),
                accessToken
        );
    }
}
