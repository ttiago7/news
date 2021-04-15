package com.news.news.converter;

import com.news.news.model.Writer;
import com.news.news.model.dto.WriterDTO;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class WriterToWriterDTOConverter implements Converter<Writer, WriterDTO> {//implementamos Converter de org.springframework.core.convert.converter.Converter;

    private final ModelMapper modelMapper;//dependencia en el pom.xml - al poner final devemos pasarle un constructor q es el siguiente

    public WriterToWriterDTOConverter(final ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public  WriterDTO convert(Writer source) {
        return modelMapper.map(source, WriterDTO.class);// source sera el nuestro objeto, WriterDTO.class es hacia q lo queremos convertir
    }
}