package com.example.rm.config.auth;

import com.example.rm.entity.Member;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Getter
public class PrincipalDetail implements UserDetails {

    private Member member; // 컴포지션

    public PrincipalDetail(Member member) {
        this.member = member;
    }

    @Override
    public String getPassword() { //
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getUserName();
    }

    // 계정이 만료되지 않았는지 리턴한다. (true:만료안됨)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정이 잠겨있지 않았는지 리턴한다. (true:잠기지 않음)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 계정이 비밀번호가 만료되지 않았는지 리턴한다. (true:만료안됨)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정이 활성화 되어 있는지 리턴한다. (true:활성화)
    @Override
    public boolean isEnabled() {
        return true;
    }

    // 계정의 권한 목록을 리턴한다. (true:활성화)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collectors = new ArrayList<>();
        collectors.add(() -> {
            return "ROLE_" + member.getRole();
        });
        return collectors;
    }
}

