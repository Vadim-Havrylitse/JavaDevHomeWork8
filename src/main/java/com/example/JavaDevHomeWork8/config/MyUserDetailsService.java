package com.example.JavaDevHomeWork8.config;

import com.example.JavaDevHomeWork8.user.entity.Role;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final NamedParameterJdbcTemplate jdbcTemplate;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserData userData = getUserData(username);
        if (userData == null){
            throw new UsernameNotFoundException(username);
        }
        return new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return Collections.singleton(userData.getRole());
            }

            @Override
            public String getPassword() {
                return userData.getPassword();
            }

            @Override
            public String getUsername() {
                return username;
            }

            @Override
            public boolean isAccountNonExpired() {
                return true;
            }

            @Override
            public boolean isAccountNonLocked() {
                return true;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return true;
            }

            @Override
            public boolean isEnabled() {
                return true;
            }
        };
    }

    private UserData getUserData(String username){
        String sql = "Select password, role From user_table Where username = :username";
        return jdbcTemplate.queryForObject(sql,
                Map.of("username", username),
                new UserDataMapper());
    }

    @Data
    @Builder
    private static class UserData{
        private String password;
        private Role role;
    }

    private static class UserDataMapper implements RowMapper<UserData>{

        @Override
        public UserData mapRow(ResultSet rs, int rowNum) throws SQLException {
            return UserData.builder()
                    .password(rs.getString("password"))
                    .role(Role.valueOf(rs.getString("role")))
                    .build();
        }
    }
}