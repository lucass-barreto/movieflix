package br.com.movieflix.mapper;

import br.com.movieflix.controller.request.StreamingRequest;
import br.com.movieflix.controller.response.StreamingResponse;
import br.com.movieflix.entity.Streaming;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StreamingMapper {

    Streaming toModel(StreamingRequest streamingRequest);

    StreamingResponse toResponse(Streaming streaming);
}
