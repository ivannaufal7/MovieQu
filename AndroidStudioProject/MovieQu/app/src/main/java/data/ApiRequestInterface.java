package data;

import data.dao.MovieResponseDao;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by IAM on 01/12/2017.
 */

public interface ApiRequestInterface {
    @GET("movie/popular")
    Call<MovieResponseDao> getMovieList(@Query("api_key") String api_key);
}
