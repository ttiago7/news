package com.news.news.service;

import com.news.news.model.Noticia;
import com.news.news.model.PaginationResponse;
import com.news.news.model.Writer;
import com.news.news.repository.NoticiaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Objects;

@Service
public class NoticiaService {

    @Autowired
    private NoticiaRepository noticiaRepository;
    private WriterService writerService;

    public NoticiaService(NoticiaRepository noticiaRepository, WriterService writerService) {
        this.noticiaRepository = noticiaRepository;
        this.writerService = writerService;
    }

    public Noticia getNoticiaById(Integer id) {
        return noticiaRepository.findById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }

    public Noticia addNoticia(Noticia noticia) {
        return noticiaRepository.save(noticia);
    }

    public List<Noticia> getAll() {
        return noticiaRepository.findAll();
    }

    public PaginationResponse<Noticia> getAll(Integer page, Integer size) {
        if (!Objects.isNull(page) && !Objects.isNull(size)) {
            Pageable pageable = PageRequest.of(page, size);
            Page<Noticia> noticiasPage = noticiaRepository.findAll(pageable);
            return new PaginationResponse<>(noticiasPage.getContent(), noticiasPage.getTotalPages(), noticiasPage.getTotalElements());
        }
        List<Noticia> noticiasList = noticiaRepository.findAll();
        return new PaginationResponse<>(noticiasList, 1, (long) noticiasList.size());
    }

    public void addWriter(Integer id, Integer writerID) {
        Noticia noticias = getNoticiaById(id);
        Writer writer = writerService.getWriter(writerID);
        noticias.setOwner(writer);
        noticiaRepository.save(noticias);

    }

    public void deletewriteByid(Integer id) {
        noticiaRepository.deleteById(id);
    }

}