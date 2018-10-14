package p1_unit_tests_mocks;

import ch13.entities.Singer;
import ch13.repositories.SingerRepository;
import ch13.service.SingerService;
import ch13.service.SingerServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.stubbing.Answer;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.ExtendedModelMap;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/*
 * Unit testing the Service, Repository is mocked and injected.
 *
 * thenReturn(): mocks return
 * thenAnswer(): mocks method logic - within an Answer implementation
 */
public class SingerControllerTest {

    private final List<Singer> testSingers = new ArrayList<>();

    @Before
    public void init() {
        final Singer singer = new Singer();
        singer.setFirstName("John");
        singer.setLastName("Mayer");
        testSingers.add(singer);
    }


    @Test
    public void list() {

        // mock SingerRepository
        SingerRepository singerRepositoryMock = mock(SingerRepository.class);
        when(singerRepositoryMock.findAll()).thenReturn(testSingers);

        // inject SingerService mock into SingerController
        final SingerService singerService = new SingerServiceImpl();
        ReflectionTestUtils.setField(singerService, "singerRepository", singerRepositoryMock);

        // ?
        ExtendedModelMap uiModel = new ExtendedModelMap();
        uiModel.addAttribute("singers", singerService.findAll());

        List<Singer> singers = (List<Singer>) uiModel.get("singers");
        assertEquals(1, singers.size());
    }

    /*
     * thenAnswer(): returns an Answer functional interface
     */
    @Test
    public void create() {
        final Singer singer = new Singer();
        singer.setFirstName("BB");
        singer.setLastName("King");

        // mock SingerService
        SingerRepository singerRepositoryMock = mock(SingerRepository.class);
        when(singerRepositoryMock.save(singer)).thenAnswer(
                (Answer<Singer>) invocationOnMock -> {
                    testSingers.add(singer);
                    return singer;
                });

        // inject SingerService mock into SingerController
        final SingerService singerService = new SingerServiceImpl();
        ReflectionTestUtils.setField(singerService, "singerRepository", singerRepositoryMock);

        Singer result = singerService.save(singer);
        assertEquals("BB", result.getFirstName());
        assertEquals(2, testSingers.size());
    }
}
