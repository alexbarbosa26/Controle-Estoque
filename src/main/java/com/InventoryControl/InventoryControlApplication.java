package com.InventoryControl;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.InventoryControl.domain.Categoria;
import com.InventoryControl.domain.ItemTroca;
import com.InventoryControl.domain.Produto;
import com.InventoryControl.domain.Sites;
import com.InventoryControl.domain.Trocas;
import com.InventoryControl.domain.Usuario;
import com.InventoryControl.repositories.CategoriaRepository;
import com.InventoryControl.repositories.ItemTrocaRepository;
import com.InventoryControl.repositories.ProdutoRepository;
import com.InventoryControl.repositories.SiteRepository;
import com.InventoryControl.repositories.TrocasRepository;
import com.InventoryControl.repositories.UsuarioRepository;

@SpringBootApplication
public class InventoryControlApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository repoCategoria;

	@Autowired
	private ProdutoRepository repoProduto;

	@Autowired
	private SiteRepository repoSite;

	@Autowired
	private UsuarioRepository repoUsuario;

	@Autowired
	private TrocasRepository repoTrocas;

	@Autowired
	private ItemTrocaRepository repoItem;

	public static void main(String[] args) {
		SpringApplication.run(InventoryControlApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = new Categoria(null, "Headset");
		Categoria cat2 = new Categoria(null, "Periféricos");
		Categoria cat3 = new Categoria(null, "Monitor");
		Categoria cat4 = new Categoria(null, "CPU");
		Categoria cat5 = new Categoria(null, "Hardware");
		Categoria cat6 = new Categoria(null, "Infraestrutura");

		Produto prod1 = new Produto(null, "Conector Felitron USB - Inferior", 100);
		Produto prod2 = new Produto(null, "Conector Felitron RJ11 - Inferior", 100);
		Produto prod3 = new Produto(null, "Felitron Parte Superior", 100);
		Produto prod4 = new Produto(null, "Dell 19", 80);
		Produto prod5 = new Produto(null, "Dell 17", 10);
		Produto prod6 = new Produto(null, "HP 19", 50);
		Produto prod7 = new Produto(null, "Dell 3010", 18);
		Produto prod8 = new Produto(null, "Dell 390", 1);
		Produto prod9 = new Produto(null, "Teclado USB", 200);
		Produto prod10 = new Produto(null, "Mouse USB", 150);
		Produto prod11 = new Produto(null, "HD 500", 15);
		Produto prod12 = new Produto(null, "Cabo de Força", 250);

		cat1.getProdutos().addAll(Arrays.asList(prod1, prod2, prod3));
		cat2.getProdutos().addAll(Arrays.asList(prod9, prod10));
		cat3.getProdutos().addAll(Arrays.asList(prod4, prod5, prod6));
		cat4.getProdutos().addAll(Arrays.asList(prod7, prod8));
		cat5.getProdutos().addAll(Arrays.asList(prod11));
		cat6.getProdutos().addAll(Arrays.asList(prod12));

		prod1.getCategorias().addAll(Arrays.asList(cat1));
		prod2.getCategorias().addAll(Arrays.asList(cat1));
		prod3.getCategorias().addAll(Arrays.asList(cat1));
		prod4.getCategorias().addAll(Arrays.asList(cat3));
		prod5.getCategorias().addAll(Arrays.asList(cat3));
		prod6.getCategorias().addAll(Arrays.asList(cat3));
		prod7.getCategorias().addAll(Arrays.asList(cat4));
		prod8.getCategorias().addAll(Arrays.asList(cat4));
		prod9.getCategorias().addAll(Arrays.asList(cat2));
		prod10.getCategorias().addAll(Arrays.asList(cat2));
		prod11.getCategorias().addAll(Arrays.asList(cat5));
		prod12.getCategorias().addAll(Arrays.asList(cat6));

		repoCategoria.save(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6));
		repoProduto.save(
				Arrays.asList(prod1, prod2, prod3, prod4, prod5, prod6, prod7, prod8, prod9, prod10, prod11, prod12));

		// *************************************************

		Usuario u1 = new Usuario(null, "213787", "Alex Barbosa", "alex.asilva@neobpo.com.br", "123456");
		Usuario u2 = new Usuario(null, "097207", "Guilherme Teixeira", "guilherme.teixeira@neobpo.com.br", "123456");
		Usuario u3 = new Usuario(null, "222230", "Diego Pimentel Belo Lopes", "diego.belo@neobpo.com.br", "123456");
		Usuario u4 = new Usuario(null, "231880", "Lucas Natario Da Silva", "lucas.natario@neobpo.com.br", "123456");
		Usuario u5 = new Usuario(null, "225493", "Renan Dos Santos Tampellini", "renan.rtampellini@neobpo.com.br",
				"123456");
		Usuario u6 = new Usuario(null, "294860", "Roberto Coelho", "roberto.coelho@neobpo.com.br", "123456");
		Usuario u7 = new Usuario(null, "238564", "Gabriel Silva Goto", "gabriel.goto@neobpo.com.br", "123456");
		Usuario u8 = new Usuario(null, "310075", "Marcelo Vicente Rosim", "marcelo.rosim@neobpo.com.br", "123456");

		Sites s1 = new Sites(null, "JBT");
		Sites s2 = new Sites(null, "SJO");
		Sites s10 = new Sites(null, "IPR");
		Sites s3 = new Sites(null, "MGC");
		Sites s4 = new Sites(null, "J23");
		Sites s5 = new Sites(null, "STS");
		Sites s6 = new Sites(null, "NVA");
		Sites s7 = new Sites(null, "MFL");
		Sites s8 = new Sites(null, "EUS");
		Sites s9 = new Sites(null, "SJC");
		Sites s11 = new Sites(null, "BRF");

		u1.getSite().addAll(Arrays.asList(s1));
		u2.getSite().addAll(Arrays.asList(s2, s10));
		u3.getSite().addAll(Arrays.asList(s3, s4));
		u4.getSite().addAll(Arrays.asList(s5));
		u5.getSite().addAll(Arrays.asList(s6, s7));
		u6.getSite().addAll(Arrays.asList(s8));
		u7.getSite().addAll(Arrays.asList(s11, s9));
		u8.getSite().addAll(Arrays.asList(s1, s2, s10, s3, s4, s5, s6, s7, s8, s9, s11));

		s1.getUsuarios().addAll(Arrays.asList(u1, u8));
		s2.getUsuarios().addAll(Arrays.asList(u2, u8));
		s10.getUsuarios().addAll(Arrays.asList(u2, u8));
		s3.getUsuarios().addAll(Arrays.asList(u3, u8));
		s4.getUsuarios().addAll(Arrays.asList(u3, u8));
		s5.getUsuarios().addAll(Arrays.asList(u4, u8));
		s6.getUsuarios().addAll(Arrays.asList(u5, u8));
		s7.getUsuarios().addAll(Arrays.asList(u5, u8));
		s8.getUsuarios().addAll(Arrays.asList(u6, u8));
		s9.getUsuarios().addAll(Arrays.asList(u7, u8));
		s11.getUsuarios().addAll(Arrays.asList(u7, u8));

		repoUsuario.save(Arrays.asList(u1, u2, u3, u4, u5, u6, u7, u8));
		repoSite.save(Arrays.asList(s1, s2, s10, s3, s4, s5, s6, s7, s8, s9, s11));

		// **********************************************************************

		SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Trocas t1 = new Trocas(null, date.parse("28/11/2018 19:35"), u1);
		Trocas t2 = new Trocas(null, date.parse("28/11/2018 19:40"), u1);

		u1.getTrocas().addAll(Arrays.asList(t1, t2));

		repoTrocas.save(Arrays.asList(t1, t2));

		// ***********************************************************************
		ItemTroca it1 = new ItemTroca(t1, prod1, 2);
		ItemTroca it2 = new ItemTroca(t2, prod11, 5);

		t1.getItens().addAll(Arrays.asList(it1));
		t2.getItens().addAll(Arrays.asList(it2));

		prod1.getItens().addAll(Arrays.asList(it1));
		prod11.getItens().addAll(Arrays.asList(it2));

		repoItem.save(Arrays.asList(it1, it2));

		if (prod1.getQuantidade() != 0 && it1.getQuantidadeTroca() < prod1.getQuantidade()) {

			Integer valor;
			valor = prod1.getQuantidade() - it1.getQuantidadeTroca();
			prod1.setQuantidade(valor);

			repoProduto.save(Arrays.asList(prod1));			


		} else {

			System.out.print("Estoque Vazio ");

		}
		// ***********************************************************************

	}
}
