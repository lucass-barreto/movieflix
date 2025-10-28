package br.com.movieflix.service;

import br.com.movieflix.controller.request.StreamingRequest;
import br.com.movieflix.controller.response.StreamingResponse;
import br.com.movieflix.entity.Streaming;
import br.com.movieflix.mapper.StreamingMapper;
import br.com.movieflix.repository.StreamingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StreamingService {

    private final StreamingRepository streamingRepository;
    private final StreamingMapper streamingMapper;

    public StreamingService(StreamingRepository streamingRepository, StreamingMapper streamingMapper) {
        this.streamingRepository = streamingRepository;
        this.streamingMapper = streamingMapper;
    }

    public List<StreamingResponse> findAll() {
        List<Streaming> streamings = streamingRepository.findAll();
        return streamings.stream()
                .map(streamingMapper::toResponse)
                .toList();
    }

    public StreamingResponse saveStreaming(StreamingRequest request) {
        Streaming streaming = streamingMapper.toModel(request);
        Streaming streamingSaved = streamingRepository.save(streaming);
        return streamingMapper.toResponse(streamingSaved);
    }

    public Optional<StreamingResponse> findById(Long id) {
        return streamingRepository.findById(id)
                .map(streamingMapper::toResponse);
    }

    public boolean deleteById(Long id) {
        if (streamingRepository.existsById(id)) {
            streamingRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
