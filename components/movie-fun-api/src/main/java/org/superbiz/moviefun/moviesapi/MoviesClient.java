package org.superbiz.moviefun.moviesapi;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static org.springframework.http.HttpMethod.GET;

public class MoviesClient {
    private static ParameterizedTypeReference<List<MovieInfo>> movieListType = new ParameterizedTypeReference<List<MovieInfo>>() {
    };
    private final String moviesUrl;
    private final RestOperations restOperations;

    public MoviesClient(String moviesUrl, RestOperations restOperations) {
        this.moviesUrl = moviesUrl;
        this.restOperations = restOperations;
    }

    public MovieInfo find(Long id) {
        return null;
    }

    public void addMovie(MovieInfo movieinfo) {
        restOperations.postForEntity(moviesUrl, movieinfo, MovieInfo.class);
    }

    public void updateMovie(MovieInfo movieinfo) {
    }

    public void deleteMovie(MovieInfo movieinfo) {
    }

    public void deleteMovieId(long movieId) {
        restOperations.delete(moviesUrl + "/" + movieId);
    }

    public List<MovieInfo> getMovies() {
        return restOperations
                .exchange(moviesUrl, GET, null, movieListType)
                .getBody();
    }

    public List<MovieInfo> findAll(int start, int pageSize) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(moviesUrl)
                .queryParam("start", start)
                .queryParam("pageSize", pageSize);

        return restOperations
                .exchange(builder.toUriString(), GET, null, movieListType)
                .getBody();
    }

    public int countAll() {
        return restOperations.getForObject(moviesUrl + "/count", Integer.class);
    }

    public int count(String field, String searchTerm) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(moviesUrl + "/count")
                .queryParam("field", field)
                .queryParam("key", searchTerm);

        return restOperations.getForObject(builder.toUriString(), Integer.class);
    }

    public List<MovieInfo> findRange(String field, String key, int start, int pageSize) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(moviesUrl)
                .queryParam("field", field)
                .queryParam("key", key)
                .queryParam("start", start)
                .queryParam("pageSize", pageSize);

        return restOperations
                .exchange(builder.toUriString(), GET, null, movieListType)
                .getBody();
    }
}
