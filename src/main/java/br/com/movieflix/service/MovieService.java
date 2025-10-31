package br.com.movieflix.service;

import br.com.movieflix.controller.request.MovieRequest;
import br.com.movieflix.controller.response.MovieResponse;
import br.com.movieflix.entity.Category;
import br.com.movieflix.entity.Movie;
import br.com.movieflix.entity.Streaming;
import br.com.movieflix.mapper.MovieMapper;
import br.com.movieflix.repository.CategoryRepository;
import br.com.movieflix.repository.MovieRepository;
import br.com.movieflix.repository.StreamingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final CategoryRepository categoryRepository;
    private final StreamingRepository streamingRepository;

    public MovieService(MovieRepository movieRepository, CategoryRepository categoryRepository, StreamingRepository streamingRepository) {
        this.movieRepository = movieRepository;
        this.categoryRepository = categoryRepository;
        this.streamingRepository = streamingRepository;
    }

    public MovieResponse saveMovie(MovieRequest request) {
        Movie movieModel = MovieMapper.toModel(request);
        movieModel.setCategories(this.findCategories(movieModel.getCategories()));
        movieModel.setStreamings(this.findStreaming(movieModel.getStreamings()));
        Movie movieSaved = movieRepository.save(movieModel);
        return MovieMapper.toMovieResponse(movieSaved);
    }

    public List<MovieResponse> findAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        return movies.stream()
                .map(MovieMapper::toMovieResponse)
                .toList();
    }

    public MovieResponse findById(Long id) {
        Optional<Movie> movie = movieRepository.findById(id);
        return movie.map(MovieMapper::toMovieResponse)
                .orElse(null);
    }

    public MovieResponse updateMovie(Long id, MovieRequest request) {
        Optional<Movie> movie = movieRepository.findById(id);
        Movie movieResquestModel = MovieMapper.toModel(request);

        if (movie.isPresent()) {
            Movie movieUpdate = movie.get();

            List<Category> categories = this.findCategories(movieResquestModel.getCategories());
            List<Streaming> streamings = this.findStreaming(movieResquestModel.getStreamings());

            movieUpdate.setTitle(request.title());
            movieUpdate.setDescription(request.description());
            movieUpdate.setRating(request.rating());
            movieUpdate.setReleaseDate(request.releaseDate());
            movieUpdate.setId(id);

            movieUpdate.getCategories().clear();
            movieUpdate.getCategories().addAll(categories);

            movieUpdate.getStreamings().clear();
            movieUpdate.getStreamings().addAll(streamings);

            return MovieMapper.toMovieResponse(movieRepository.save(movieUpdate));

        } else {
            return null;
        }
    }

    public boolean deleteById(Long id) {
        if (movieRepository.existsById(id)) {
            movieRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<MovieResponse> findMovieByCategory(Long id) {
        List<Movie> moviesFound = movieRepository.findByCategoriesId(id);

        if (!moviesFound.isEmpty()) {
            return moviesFound.stream()
                    .map(MovieMapper::toMovieResponse)
                    .toList();
        }
        return List.of();
    }

    private List<Category> findCategories(List<Category> categories) {
        List<Long> categoriesIds = categories.stream()
                .map(Category::getId)
                .toList();
        return categoryRepository.findAllById(categoriesIds);
    }

    private List<Streaming> findStreaming(List<Streaming> streamings) {
        List<Long> streamingsIds = streamings.stream()
                .map(Streaming::getId)
                .toList();
        return streamingRepository.findAllById(streamingsIds);
    }
}

