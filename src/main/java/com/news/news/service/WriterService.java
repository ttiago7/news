package com.news.news.service;

import com.news.news.repository.WriterRepository;
import com.news.news.model.Writer;
import com.news.news.utils.EntityURLBuilder;
import com.news.news.utils.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Service
public class WriterService {
    private static final String PERSON_PATH = "person";//es la url q ponelmemos en postman

    @Autowired
    private WriterRepository writerRepository;

    public List<Writer> getWriters() {
        return writerRepository.findAll();
    }

//    public Writer addWriter(Writer writer) {
//        return writerRepository.save(writer);
//    }
    public PostResponse addWriter(Writer writer) {
        final Writer writerSaved = writerRepository.save(writer);
        return PostResponse.builder()
                .status(HttpStatus.CREATED)
                .url(EntityURLBuilder.buildURL(PERSON_PATH, writerSaved.getId().toString()))
                .build();
    }

    public Writer getWriter(Integer id) {
        return writerRepository.findById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }

    public void deletewriteByid(Integer id) {
        writerRepository.deleteById(id);
    }

    public Writer editPerson(Writer write) {
        Writer writer = writerRepository.findById(write.getId()).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
        Writer editwriter = new Writer();
        editwriter.setId(writer.getId());
        if (write.getNombre() != null) {
            editwriter.setNombre(write.getNombre());
        } else {
            editwriter.setNombre(writer.getNombre());
        }
        if (write.getApellido() != null) {
            editwriter.setApellido(write.getApellido());
        } else {
            editwriter.setApellido(writer.getApellido());
        }
        if (write.getDni() != null) {
            editwriter.setDni(write.getDni());
        } else {
            editwriter.setDni(writer.getDni());
        }
        return writerRepository.save(editwriter);
    }
}
