package movieinformation.integrationtest;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.spring.api.DBRider;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;

@SpringBootTest
@AutoConfigureMockMvc
@DBRider
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
 class MovieInformationRestApiIntegrationTest {
    @Autowired
    MockMvc mockMvc;

    //Read機能のIntegrationTest
    @Test
    @DataSet(value = "datasets/movieData.yml")
    @Transactional
    public void 映画情報をステータスコード200で全件取得すること() throws Exception{
        String response = mockMvc.perform(MockMvcRequestBuilders.get("/movies"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
        JSONAssert.assertEquals("""
                [
                 {
                   "id": 1,
                   "name": "Episode I – The Phantom Menace",
                   "release_date": "1999-07-10",
                   "director_name": "George Walton Lucas Jr",
                   "box_office": 1027082707
                 },
                 {
                   "id": 2,
                   "name": "Episode II – Attack of the Clones",
                   "release_date": "2002-05-16",
                   "director_name": "George Walton Lucas Jr",
                   "box_office": 653779970
                 },
                 {
                   "id": 3,
                   "name": "Episode III – Revenge of the Sith",
                   "release_date": "2005-07-09",
                   "director_name": "George Walton Lucas Jr",
                   "box_office": 868390560
                 }
                ]
                 """,response, JSONCompareMode.STRICT);
    }

}
