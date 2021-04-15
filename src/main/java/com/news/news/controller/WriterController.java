package com.news.news.controller;

import com.news.news.converter.WriterToWriterDTOConverter;
import com.news.news.model.Writer;
import com.news.news.model.dto.WriterDTO;
import com.news.news.service.WriterService;

import com.news.news.utils.PostResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/writer")
public class WriterController {

    @Autowired
    private WriterService writerService;

    @Autowired
    private ConversionService conversionService;

    @Autowired
    private WriterToWriterDTOConverter writerToWriterDTOConverter;

    @GetMapping
    @Operation(summary = "List of writers")
    public List<Writer> getWriters() {
        return writerService.getWriters();
    }

    @GetMapping("/writerDTO")
    @Operation(summary = "List of writers DTO (without dni)")
    public List<WriterDTO> getAllWriterDTO() {
        return conversionService.convert(writerService.getWriters(), List.class);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get writer by id")
    public Writer getWriter(@PathVariable Integer id) {
        Writer response = writerService.getWriter(id);
        if (response != null) {
            return response;
        } else {
            return null;
        }
    }

    @GetMapping("/writerDTO/{id}")
    @Operation(summary = "Get writer DTO by id")
    public WriterDTO getWriterDTOByID(@PathVariable Integer id) {
        return conversionService.convert(writerService.getWriter(id), WriterDTO.class);
    }

//    @PostMapping
//    @Operation(summary = "Dar de alta un writer")
//    public String addWriter(@RequestBody Writer writer) {
//        Writer postwriter = writerService.addWriter(writer);
//        return ("Persona creada: " + postwriter);
//    }

    @PostMapping
    @Operation(summary = "Post writer")// notation de Swagger para la documentacion
    @ApiResponses(value = {// Documentacion
            @ApiResponse(responseCode = "201", description = "Writer creado exitosamente",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))})
    })
    public ResponseEntity<?> addWriter(@RequestBody Writer writer) {//con el ? es para no especificar el tipo de objeto q vamos a devolver

        //con final no lo podes redefinir
        final PostResponse postResponse = writerService.addWriter(writer);
        return new ResponseEntity(postResponse.getUrl(), postResponse.getStatus());
    }

    @PutMapping
    @Operation(summary = "Edit writer, send body with ID")
    public String editWriter(@RequestBody Writer writer) {
        Writer putwriter = writerService.editPerson(writer);
        return ("Writer editado " + putwriter);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete writer by id")
    public String deleteWrite(@PathVariable Integer id) {
        writerService.deletewriteByid(id);
        return ("Writer con id " + id + " borrado");
    }

}
