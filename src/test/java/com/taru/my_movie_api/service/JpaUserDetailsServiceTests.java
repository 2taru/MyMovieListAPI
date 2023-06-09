package com.taru.my_movie_api.service;

import com.taru.my_movie_api.models.User;
import com.taru.my_movie_api.repository.UserRepository;
import com.taru.my_movie_api.service.impl.JpaUserDetailsServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class JpaUserDetailsServiceTests {

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private JpaUserDetailsServiceImpl jpaUserDetailsService;

    @Test
    public void JpaUserDetailsService_LoadUserByUsername_ReturnsUserDetails() {

        User user = User.builder().username("test").email("test@mail.com").build();

        when(userRepository.findByUsername("test")).thenReturn(Optional.ofNullable(user));

        UserDetails userDetails = jpaUserDetailsService.loadUserByUsername("test");

        assertThat(userDetails).isNotNull();
        assertThat(userDetails.getUsername()).isEqualTo("test");
    }
}
