package com.news.news.controller;

import com.news.news.model.Noticia;
import com.news.news.model.NoticiaEnum;
import com.news.news.model.PaginationResponse;
import com.news.news.service.NoticiaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // le decimos q es un controller
@RequestMapping("/noticias") //
public class NoticiasController {

    @Autowired // ic - i de dependencia
    private NoticiaService noticiaService;

    @PostMapping
    @Operation(summary = "Post a New")// notation de Swagger para la documentacion
    @ApiResponses(value = {// Documentacion
            @ApiResponse(responseCode = "201", description = "Noticia creada exitosamente",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))})
    })
    public String addNoticia(@RequestBody Noticia noticia) { //RequestBody parsea el json a un objeto de java
        Noticia postnoticia = noticiaService.addNoticia(noticia);
        return ("Noticia Creada exitosamente: " + postnoticia);
    }

    @PutMapping("/{id}/writer/{writerID}")
    @Operation(summary = "Add writer (id writer path variable) to News")
    private String addWriter(@PathVariable Integer id, @PathVariable Integer writerID) {
        noticiaService.addWriter(id, writerID);
        return ("se agrego el writer con id " + writerID + " a la noticia con id " + id);
    }

    @GetMapping
    @Operation(summary = "Paged list of News")
    public PaginationResponse<Noticia> getAll(@RequestParam(value = "size", defaultValue = "20") Integer size,
                                              @RequestParam(value = "page", defaultValue = "0") Integer page) {
        return noticiaService.getAll(page, size);
    }

    @GetMapping("/all")
    @Operation(summary = "Complete list of News")
    public List<Noticia> getAll() {//get all armara los hijos segun el tipo de cada uno d los objetos
        return noticiaService.getAll();
    }

    @GetMapping("/type")
    @Operation(summary = "List of News by type")
    public List<Noticia> getByType(@RequestParam(value = "noticiasEnum", required = false, defaultValue = "TEXTO") NoticiaEnum noticiaEnum) {//get all armara los hijos segun el tipo de cada uno d los objetos
        //si es ALL devolver todas las noticias
        //sino filtrar por el tipo
        return noticiaService.getAll();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a New by id")
    public String deleteNoticia(@PathVariable Integer id) {
        noticiaService.deletewriteByid(id);
        return ("Noticia con id: " + id + "Borrada exitosamente");
    }


}
