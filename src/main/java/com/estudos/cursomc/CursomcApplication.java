package com.estudos.cursomc;

import com.estudos.cursomc.domain.Categoria;
import com.estudos.cursomc.domain.Cidade;
import com.estudos.cursomc.domain.Estado;
import com.estudos.cursomc.domain.Produto;
import com.estudos.cursomc.repositories.CategoriaRepository;
import com.estudos.cursomc.repositories.CidadeRepository;
import com.estudos.cursomc.repositories.EstadoRepository;
import com.estudos.cursomc.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

    @Autowired
    CategoriaRepository categoriaRepository;

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    CidadeRepository cidadeRepository;

    @Autowired
    EstadoRepository estadoRepository;

    public static void main(String[] args) {
        SpringApplication.run(CursomcApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Categoria cat1 = new Categoria(null, "Informática");
        Categoria cat2 = new Categoria(null, "Escritório");

        Produto p1 = new Produto(null, "Computador", 2000.00);
        Produto p2 = new Produto(null, "Impressora", 800.00);
        Produto p3 = new Produto(null, "Mouse", 80.00);

        cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
        cat2.getProdutos().addAll(Arrays.asList(p2));

        p1.getCategorias().addAll(Arrays.asList(cat1));
        p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
        p3.getCategorias().addAll(Arrays.asList(cat1));

        categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
        produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

        Estado est1 = new Estado(null, "Minas Gerais");
        Estado est2 = new Estado(null, "São Paulo");

        Cidade cit1 = new Cidade(null, "Uberlândia", est1);
        Cidade cit2 = new Cidade(null, "São Paulo", est2);
        Cidade cit3 = new Cidade(null, "Campinas", est2);

        est1.getCidades().addAll(Arrays.asList(cit1));
        est2.getCidades().addAll(Arrays.asList(cit2, cit3));

        estadoRepository.saveAll(Arrays.asList(est1, est2));
        cidadeRepository.saveAll(Arrays.asList(cit1, cit2, cit3));


    }
}
