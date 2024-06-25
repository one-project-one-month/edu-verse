/*
 * @Author : Alvin
 * @Date : 6/9/2024
 * @Time : 10:00 PM
 * @Project_Name : eduverse
 */
package dev.backend.eduverse.util.response_template;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DtoUtil {

    public static <E, D> List<D> mapList(List<E> entityList, Class<D> dtoClass, ModelMapper modelMapper) {
        return entityList.stream()
                .map(entity -> modelMapper.map(entity, dtoClass))
                .collect(Collectors.toList());
    }

    public static <E, D> D map(E source, Class<D> destinationClass, ModelMapper modelMapper) {
        return modelMapper.map(source, destinationClass);
    }
}
