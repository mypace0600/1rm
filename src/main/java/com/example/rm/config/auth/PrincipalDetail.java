package com.example.rm.config.auth;

import com.example.rm.entity.Member;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

@Getter
public class PrincipalDetail implements UserDetails {

    private Member member;

    public PrincipalDetail(Member member) {
        this.member = member;
    }

    @Override
    public String getPassword() { //
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getUsername();
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
        /**
         *
         *
        LocalDateTime today = LocalDateTime.now();
        int gap = today.compareTo(member.getLastLoginDate().toLocalDateTime());
        if(gap>365){
            return false;
        }
         */

        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collectors = new ArrayList<>();
        collectors.add(() -> {
            return member.getRole().name();
        });
        return collectors;
    }
}

