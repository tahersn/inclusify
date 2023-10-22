package tn.esprit.feedservice.security;

import org.springframework.core.convert.converter.*;
//import org.springframework.security.core.*;
//import org.springframework.security.core.authority.*;
//import org.springframework.security.oauth2.jwt.*;

import java.util.*;
import java.util.stream.*;

/**
 * @author Jozef
 */
public class RealmRoleConverter {//implements Converter<Jwt, Collection<GrantedAuthority>> {


//    @Override
//    public Collection<GrantedAuthority> convert(Jwt jwt) {
//        final Map<String, List<String>> realmAccess = (Map<String, List<String>>) jwt.getClaims().get("realm_access");
//        return realmAccess.get("roles").stream().map(roleName -> "ROLE_" + roleName).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
//    }
}
