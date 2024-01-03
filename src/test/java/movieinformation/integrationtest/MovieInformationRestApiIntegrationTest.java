package movieinformation.integrationtest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.spring.api.DBRider;
import movieinformation.entity.Movie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

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
    public void 映画情報をステータスコード200で全件取得すること() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.get("/movies"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
        JSONAssert.assertEquals("""
                [
                 {
                   "id": 1,
                   "name": "Episode I – The Phantom Menace",
                   "releaseDate": "1999-07-10",
                   "directorName": "George Walton Lucas Jr.",
                   "boxOffice": 1027082707
                 },
                 {
                   "id": 2,
                   "name": "Episode II – Attack of the Clones",
                   "releaseDate": "2002-05-16",
                   "directorName": "George Walton Lucas Jr.",
                   "boxOffice": 653779970
                 },
                 {
                   "id": 3,
                   "name": "Episode III – Revenge of the Sith",
                   "releaseDate": "2005-07-09",
                   "directorName": "George Walton Lucas Jr.",
                   "boxOffice": 868390560
                 }
                ]
                 """, response, JSONCompareMode.STRICT);
    }

    @Test
    @DataSet(value = "datasets/movieData.yml")
    @Transactional
    public void 存在する映画をID指定して情報とステータスコード200を取得すること() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.get("/movies/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
        JSONAssert.assertEquals("""
           
                 {
                   "id": 1,
                   "name": "Episode I – The Phantom Menace",
                   "releaseDate": "1999-07-10",
                   "directorName": "George Walton Lucas Jr.",
                   "boxOffice": 1027082707
                 }
                
                 """, response, JSONCompareMode.STRICT);
    }
    @Test
    @DataSet(value = "datasets/movieData.yml")
    @Transactional
    public void 存在しないIDを指定するとステータスコード404とエラーメッセージを取得すること()throws Exception{
   mockMvc.perform(MockMvcRequestBuilders.get("/movies/100"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn().getResponse().getErrorMessage();
    }

    //Create機能のIntegrationTest
    @Test
    @DataSet(value ="datasets/movieData.yml")
    @ExpectedDataSet(value ="datasets/insert_movieData.yml", ignoreCols = "id")
    @Transactional
    public void 新規の映画がDBに登録されるとステータスコード201が返ってくる事()throws Exception{
        Movie movie = new Movie("Episode VII – The Force Awakens", LocalDate.of(2015,12,18),"Jeffrey Jacob Abrams",2071310218);
        ObjectMapper mapper= new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        String jason = mapper.writeValueAsString(movie);
        mockMvc.perform(MockMvcRequestBuilders.post("/movies").contentType(MediaType.APPLICATION_JSON).content(jason))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    @DataSet(value ="datasets/movieData.yml")
    @Transactional
    public void 重複登録の場合はステータスコード400とエラーメッセージが返ってくる()throws Exception{
        Movie movie = new Movie("Episode I – The Phantom Menace", LocalDate.of(1999,7,10),"George Walton Lucas Jr.",1027082707);
        ObjectMapper mapper= new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        String jason = mapper.writeValueAsString(movie);
        mockMvc.perform(MockMvcRequestBuilders.post("/movies").contentType(MediaType.APPLICATION_JSON).content(jason))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn().getResponse().getContentAsString().contains("Already registered data");
    }

    //Update機能のIntegrationTest
    @Test
    @DataSet(value = "datasets/movieData.yml")
    @ExpectedDataSet(value ="datasets/update_movieData.yml", ignoreCols = "id")
    @Transactional
    public void 存在する映画情報を更新するとステータスコード200と指定のメッセージを取得することや正しく更新されているか確認すること()throws Exception{
        Assertions.assertTrue(mockMvc.perform(MockMvcRequestBuilders.patch("/movies/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
            {
              "name": "Rogue One: A Star Wars Story",
              "releaseDate": "2016-12-16",
              "directorName": "Gareth Edwards",
              "boxOffice": 1056057273
            }
            """))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString().contains("Movie updated"));
        String response = mockMvc.perform(MockMvcRequestBuilders.get("/movies/1"))
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
        JSONAssert.assertEquals("""
        
         {
           "id": 1,
           "name": "Rogue One: A Star Wars Story",
           "releaseDate": "2016-12-16",
           "directorName": "Gareth Edwards",
           "boxOffice": 1056057273
         }
         """, response, JSONCompareMode.STRICT);
    }

    @Test
    @DataSet(value ="datasets/movieData.yml")
    @Transactional
    public void 存在しない映画情報を更新処理するとステータスコード404とエラーメッセージを取得すること()throws Exception{
        Assertions.assertTrue(mockMvc.perform(MockMvcRequestBuilders.patch("/movies/100")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
            {
              "name": "Rogue One: A Star Wars Story",
              "releaseDate": "2016-12-16",
              "directorName": "Gareth Edwards",
              "boxOffice": 1056057273
            }
            """))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn().getResponse().getContentAsString().contains("Movie not found"));
    }
    //Delete機能のIntegrationTest
    @Test
    @DataSet(value = "datasets/movieData.yml")
    @ExpectedDataSet(value ="datasets/delete_movieData.yml")
    @Transactional
    public void 存在する映画情報を削除するとステータスコード200と指定のメッセージを取得することや正しく削除されているか確認すること()throws Exception{
        Assertions.assertTrue(mockMvc.perform(MockMvcRequestBuilders.delete("/movies/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString().contains("Movie deleted"));
        String response = mockMvc.perform(MockMvcRequestBuilders.get("/movies"))
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
        JSONAssert.assertEquals("""
         [
         {
           "id": 2,
           "name": "Episode II – Attack of the Clones",
           "releaseDate": "2002-05-16",
           "directorName": "George Walton Lucas Jr.",
           "boxOffice": 653779970
         },
         {
           "id": 3,
           "name": "Episode III – Revenge of the Sith",
           "releaseDate": "2005-07-09",
           "directorName": "George Walton Lucas Jr.",
           "boxOffice": 868390560
         }
         ]
         """, response, JSONCompareMode.STRICT);
    }

    @Test
    @DataSet(value ="datasets/movieData.yml")
    @Transactional
    public void 存在しない映画情報を削除処理するとステータスコード404とエラーメッセージを取得と削除されていないと確認すること()throws Exception {
        Assertions.assertTrue(mockMvc.perform(MockMvcRequestBuilders.delete("/movies/100"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn().getResponse().getContentAsString().contains("Movie not found"));
        String response = mockMvc.perform(MockMvcRequestBuilders.get("/movies"))
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
        JSONAssert.assertEquals("""
                [
                 {
                   "id": 1,
                   "name": "Episode I – The Phantom Menace",
                   "releaseDate": "1999-07-10",
                   "directorName": "George Walton Lucas Jr.",
                   "boxOffice": 1027082707
                 },
                 {
                   "id": 2,
                   "name": "Episode II – Attack of the Clones",
                   "releaseDate": "2002-05-16",
                   "directorName": "George Walton Lucas Jr.",
                   "boxOffice": 653779970
                 },
                 {
                   "id": 3,
                   "name": "Episode III – Revenge of the Sith",
                   "releaseDate": "2005-07-09",
                   "directorName": "George Walton Lucas Jr.",
                   "boxOffice": 868390560
                 }
                ]
                 """, response, JSONCompareMode.STRICT);
    }

}
