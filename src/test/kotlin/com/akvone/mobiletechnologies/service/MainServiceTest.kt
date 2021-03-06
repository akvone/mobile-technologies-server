package com.akvone.mobiletechnologies.service

import com.akvone.mobiletechnologies.plain_object.ProfileDTO
import com.akvone.mobiletechnologies.plain_object.UserRequestDTO
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.test.context.support.WithMockUser

@SpringBootTest
internal class MainServiceTest {

    @Autowired
    lateinit var mainService: MainService

    @BeforeEach
    fun before() {
        val userRequestDTO = UserRequestDTO("test", "test")
        mainService.createUser(userRequestDTO)
    }

    @Test
    @WithMockUser("test")
    fun createUserAndUpdateHisProfile() {
        val defaultName = mainService.getProfile().name
        assertThat(defaultName).isEqualTo("test")

        val expectedName = "meaningful name"
        val expectedAge = 81
        val expectedHeight = 123
        val expectedWeight = 987.0

        val profileDTO = ProfileDTO(expectedName, expectedAge, expectedHeight, expectedWeight)
        mainService.updateProfile(profileDTO)
        val (name, age, height, weight) = mainService.getProfile()

        assertThat(name).isEqualTo(expectedName)
        assertThat(age).isEqualTo(expectedAge)
        assertThat(height).isEqualTo(expectedHeight)
        assertThat(weight).isEqualTo(expectedWeight)
    }
}