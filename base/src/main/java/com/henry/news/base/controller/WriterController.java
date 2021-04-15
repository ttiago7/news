package com.henry.news.base.controller;

import com.henry.news.base.model.Writer;

import com.henry.news.base.service.WriterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/writer")
public class WriterController {

    @Autowired
    private WriterService writerService;

    @GetMapping
    public List<Writer> getWriters() {
        return writerService.getWriters();
    }

    @GetMapping("/{id}")
    public Writer getWriter(@PathVariable Integer id) {
        return writerService.getWriter(id);
    }

    @PostMapping
    public String addWriter(@RequestBody Writer writer) {
        Writer postwriter = writerService.addWriter(writer);
        return ("Persona creada: " + postwriter);
    }

    @DeleteMapping("/{id}")
    public String deleteWrite(@PathVariable Integer id) {
        writerService.deletewriteByid(id);
        return ("Persona Borrada con id: " + id);
    }

    @PutMapping
    public String editWriter(@RequestBody Writer writer) {
        Writer putwriter = writerService.editPerson(writer);
        return ("Persona Editada " + putwriter);
    }


}
