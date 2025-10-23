package br.com.movieflix.mapper;

import br.com.movieflix.controller.request.CategoryRequest;
import br.com.movieflix.controller.response.CategoryResponse;
import br.com.movieflix.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toModel(CategoryRequest categoryRequest);

    CategoryResponse toResponse(Category category);

}
