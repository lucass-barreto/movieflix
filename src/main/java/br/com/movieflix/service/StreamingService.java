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

    public StreamingService(StreamingRepository streamingRepository) {
        this.streamingRepository = streamingRepository;
    }

    public List<StreamingResponse> findAll() {
        List<Streaming> streamings = streamingRepository.findAll();
        return streamings.stream()
                .map(StreamingMapper::toStreamingResponse)
                .toList();
    }

    public StreamingResponse saveStreaming(StreamingRequest request) {
        Streaming streamingSaved = streamingRepository.save(StreamingMapper.toModel(request));
        return StreamingMapper.toStreamingResponse(streamingSaved);
    }

    public Optional<StreamingResponse> findById(Long id) {
        return streamingRepository.findById(id)
                .map(StreamingMapper::toStreamingResponse);
    }

    public boolean deleteById(Long id) {
        if (streamingRepository.existsById(id)) {
            streamingRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
