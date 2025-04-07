package com.studiozero.projeto.domain.services;

import com.studiozero.projeto.applications.web.dtos.request.ProductCreateRequestDTO;
import com.studiozero.projeto.applications.web.dtos.request.ProductDeleteRequestDTO;
import com.studiozero.projeto.applications.web.dtos.request.ProductUpdateRequestDTO;
import com.studiozero.projeto.applications.web.dtos.response.ProductResponseDTO;

import java.util.List;

public interface ProductService {
    ProductResponseDTO save(ProductCreateRequestDTO productDto);
    List<ProductResponseDTO> list();
    ProductResponseDTO findById(Integer id);
    ProductResponseDTO update(ProductUpdateRequestDTO productDto);
    String delete(ProductDeleteRequestDTO productDto);
}
