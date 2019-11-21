package com.akvone.mobiletechnologies.configuration

import com.akvone.mobiletechnologies.repositorie.UserRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class MongoUserDetailsService(val userRepository: UserRepository) : UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        val user = userRepository.findByUsername(username!!) ?: throw UsernameNotFoundException("User not found")
        val authorities: List<SimpleGrantedAuthority> = listOf(SimpleGrantedAuthority("USER"))

        return User(user.username, user.password, authorities);
    }

}