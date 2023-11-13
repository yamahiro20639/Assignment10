package movieinformation.integrationtest;

import com.github.database.rider.core.api.dataset.DataSet;
import movieinformation.entity.Movie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@SpringBootTest
@AutoConfigureMockMvc
public class MovieInformationRestApiIntegrationTest {
    @Autowired
    MockMvc mockMvc;

    //Read機能のIntegrationTest
    @Test
    @DataSet(value = "datasets/movieData.yml")
    @Transactional
    void 映画情報を全件取得すること() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/movies"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}

//new Movie(1,"Episode I – The Phantom Menace", LocalDate.of(1999,7,10),"George Walton Lucas Jr.",1027082707),
        //new Movie(2,"Episode II – Attack of the Clones", LocalDate.of(2002,5,16),"George Walton Lucas Jr.",653779970),
        //new Movie(3,"Episode III – Revenge of the Sith", LocalDate.of(2005,7,9),"George Walton Lucas Jr.",868390560)
