package net.huray.backend.minuting.service

import io.mockk.every
import io.mockk.mockk
import net.huray.backend.http.exception.NotFoundException
import net.huray.backend.minuting.dto.MinutesDto
import net.huray.backend.minuting.entity.MinutesEntity
import net.huray.backend.minuting.repository.MinutesRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.annotation.Transactional
import java.util.*

@DisplayName("Minutes Read Service 테스트")
internal class MinutesReadServiceTest {
    private val minutesRepository: MinutesRepository = mockk(relaxed = true)
    lateinit var minutesReadService: MinutesReadService

    @BeforeEach
    fun setUp() {
        minutesReadService = MinutesReadService(minutesRepository)
    }

    @ActiveProfiles("my")
    @Transactional
    @Nested
    @DisplayName("list 메소드는")
    inner class List {

        @Test
        fun `전체 회의록 목록을 MinutesSimple 로 반환한다`() {
            val expected = MinutesEntity("test", "test contents")

            every { minutesRepository.findAll() } returns mutableListOf(expected)

            val actual = minutesReadService.list()
            assertAll(
                { assertEquals(expected.contents, actual[0].contents) },
                { assertEquals(MinutesDto.MinutesSimple::class, actual[0]::class) }
            )
        }

    }

    @ActiveProfiles("my")
    @Transactional
    @Nested
    @DisplayName("list 메소드는")
    inner class GetDetailById {

        @Test
        fun `만약 id로 조회된 회의록이 없다면, NotFoundException 을 던진다`() {
            every { minutesRepository.findById(allAny()) } returns Optional.empty()

            assertThrows(NotFoundException::class.java) { minutesReadService.getDetailById(0L) }
        }

        @Test
        fun `매개변수 id 에 대응하는 회의록을 MinutesDetail 로 반환한다`() {
            val expected = MinutesEntity("test", "test contents")
                .apply { id = 1L }

            every { minutesRepository.findById(expected.id) } returns Optional.of(expected)

            val actual = minutesReadService.getDetailById(expected.id)
            assertAll(
                { assertEquals(expected.contents, actual.contents) },
                { assertEquals(MinutesDto.MinutesDetail::class, actual::class) }
            )
        }

    }

}