package com.dai.config

/**
 * Created by dai on 2018/1/30.
 */
//@Configuration
//class WebSecurityConfig : WebSecurityConfigurerAdapter() {
//    @Bean
//    internal fun customUserService(): UserDetailsService {
//        return CustomUserService()
//    }
//
//    @Throws(Exception::class)
//    protected fun configure(auth: AuthenticationManagerBuilder) {
//        auth.userDetailsService(customUserService())
//    }
//
//    @Throws(Exception::class)
//    protected fun configure(http: HttpSecurity) {
//        http.authorizeRequests()
//                .anyRequest().authenticated()
//                .and().formLogin().loginPage("/login").failureUrl("/login?error").permitAll().and()
//                .logout().permitAll()
//    }
//}