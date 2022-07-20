package com.estudos.cursomc.services;

import com.estudos.cursomc.domain.Categoria;
import com.estudos.cursomc.dto.CategoriaDTO;
import com.estudos.cursomc.repositories.CategoriaRepository;
import com.estudos.cursomc.services.exceptions.DataIntegrityException;
import com.estudos.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria find(Integer id) {
        Optional<Categoria> obj = categoriaRepository.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
    }

    public Categoria insert(Categoria obj) {
        validateCategoryName(obj);
        obj.setId(null);
        return categoriaRepository.save(obj);
    }

    public Categoria update(Categoria obj) {
        validateCategoryName(obj);
        find(obj.getId());
        return categoriaRepository.save(obj);
    }

    public void deleteById(Integer id) {
        find(id);
        try {
            categoriaRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Nao e possivel excluir uma categoria que possui produtos");
        }
    }

    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return categoriaRepository.findAll(pageRequest);
    }

    public Categoria fromDTO(CategoriaDTO categoriaDTO) {
        return new Categoria(categoriaDTO.getId(), categoriaDTO.getNome());
    }

    public void validateCategoryName(Categoria obj) {
        Assert.hasLength(obj.getNome(), "O nome deve ser preenchido");
        if (obj.getNome().length() < 5 || obj.getNome().length() > 80) {
            throw new IllegalArgumentException("O nome da categoria deve ter de 5 a 80 caracteres");
        }
    }
}
