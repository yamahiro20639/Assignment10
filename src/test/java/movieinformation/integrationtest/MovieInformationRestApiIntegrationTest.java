package movieinformation.integrationtest;

import com.github.database.rider.core.api.dataset.DataSet;
import movieinformation.entity.Movie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

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
                .andExpect(new Movie());
    }

}
