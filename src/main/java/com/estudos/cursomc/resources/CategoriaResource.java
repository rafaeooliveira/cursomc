package com.estudos.cursomc.resources;

import com.estudos.cursomc.domain.Categoria;
import com.estudos.cursomc.dto.CategoriaDTO;
import com.estudos.cursomc.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService categoriaService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Categoria> find(@PathVariable Integer id) {
        Categoria obj = categoriaService.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@RequestBody Categoria obj) {
        obj = categoriaService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@RequestBody Categoria obj, @PathVariable Integer id) {
        obj.setId(id);
        obj = categoriaService.update(obj);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        categoriaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CategoriaDTO>> findAll() {
        List<Categoria> categoriaList = categoriaService.findAll();
        List<CategoriaDTO> categoriaDTOList = categoriaList.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(categoriaDTOList);
    }

}
