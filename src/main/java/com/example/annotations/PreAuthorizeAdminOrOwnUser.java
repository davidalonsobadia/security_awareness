package com.example.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.security.access.prepost.PreAuthorize;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
//@PreAuthorize("hasRole('ADMIN') or "
//		+ "(hasRole('USER') and principal.username == #user.email)")
@PreAuthorize("hasRole('ADMIN') or principal.username == #user.email")
public @interface PreAuthorizeAdminOrOwnUser {

}
