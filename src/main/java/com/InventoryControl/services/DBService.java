package com.InventoryControl.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.InventoryControl.domain.Categoria;
import com.InventoryControl.domain.Celula;
import com.InventoryControl.domain.Cliente;
import com.InventoryControl.domain.ItemTroca;
import com.InventoryControl.domain.Produto;
import com.InventoryControl.domain.Sites;
import com.InventoryControl.domain.Trocas;
import com.InventoryControl.domain.Usuario;
import com.InventoryControl.enums.Perfil;
import com.InventoryControl.repositories.CategoriaRepository;
import com.InventoryControl.repositories.CelulaRepository;
import com.InventoryControl.repositories.ClienteRepository;
import com.InventoryControl.repositories.ItemTrocaRepository;
import com.InventoryControl.repositories.ProdutoRepository;
import com.InventoryControl.repositories.SiteRepository;
import com.InventoryControl.repositories.TrocasRepository;
import com.InventoryControl.repositories.UsuarioRepository;

@Service
public class DBService {

	@Autowired
	private BCryptPasswordEncoder psw;

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

	@Autowired
	private ClienteRepository repoCliente;

	@Autowired
	private CelulaRepository repoCelula;

	public void instantiateTestDataBase() throws ParseException {

		Categoria cat1 = new Categoria(null, "Headset");
		Categoria cat2 = new Categoria(null, "Periféricos");
		Categoria cat3 = new Categoria(null, "Monitor");
		Categoria cat4 = new Categoria(null, "CPU");
		Categoria cat5 = new Categoria(null, "Hardware");
		Categoria cat6 = new Categoria(null, "Infraestrutura");

		Sites s1 = new Sites(null, "JBT");
		Sites s2 = new Sites(null, "SJO");
		Sites s3 = new Sites(null, "MGC");
		Sites s4 = new Sites(null, "J23");
		Sites s5 = new Sites(null, "STS");
		Sites s6 = new Sites(null, "NVA");
		Sites s7 = new Sites(null, "MFL");
		Sites s8 = new Sites(null, "EUS");
		Sites s9 = new Sites(null, "SJC");
		Sites s10 = new Sites(null, "IPR");
		Sites s11 = new Sites(null, "BRF");

		Produto prod1 = new Produto(null, "Conector Felitron USB - Inferior", 100, "felitron_inferior_usb", s1);
		Produto prod2 = new Produto(null, "Conector Felitron RJ11 - Inferior", 100, "felitron_inferior_rj", s1);
		Produto prod3 = new Produto(null, "Felitron Parte Superior", 100, "felitron_superior", s1);
		Produto prod4 = new Produto(null, "Dell 19", 80, "monitor_dell_19", s1);
		Produto prod5 = new Produto(null, "Dell 17", 10, "monitor_dell_17", s1);
		Produto prod6 = new Produto(null, "HP 19", 50, "monitor_hp_19", s1);
		Produto prod7 = new Produto(null, "Dell 3010", 18, "cpu_dell_3010", s1);
		Produto prod8 = new Produto(null, "Dell 390", 1, "cpu_dell_390", s1);
		Produto prod9 = new Produto(null, "Teclado USB", 200, "teclado_usb", s1);
		Produto prod10 = new Produto(null, "Mouse USB", 150, "mouse_usb", s1);
		Produto prod11 = new Produto(null, "HD 500", 15, "hd", s1);
		Produto prod12 = new Produto(null, "Cabo de Força", 250, "cabo_forca", s1);
		Produto prod13 = new Produto(null, "Memória RAM DDR2 ", 15, "memoria_ram", s1);
		Produto prod14 = new Produto(null, "Aparelho Avaya 1608", 120, "aparelho_avaya", s1);
		Produto prod15 = new Produto(null, "Switch Avaya 4550T-PWR", 2, "switch", s1);
		Produto prod16 = new Produto(null, "Switch Core Avaya 4550T-PWR", 2, "switch_core", s1);
		Produto prod17 = new Produto(null, "Felitron Completo", 100, "felitron_completo", s1);

		cat1.getProdutos().addAll(Arrays.asList(prod1, prod2, prod3, prod17));
		cat2.getProdutos().addAll(Arrays.asList(prod9, prod10));
		cat3.getProdutos().addAll(Arrays.asList(prod4, prod5, prod6));
		cat4.getProdutos().addAll(Arrays.asList(prod7, prod8));
		cat5.getProdutos().addAll(Arrays.asList(prod11, prod13, prod14));
		cat6.getProdutos().addAll(Arrays.asList(prod12, prod15, prod16));

		s1.getProdutos().addAll(Arrays.asList(prod1, prod2, prod3, prod4, prod5, prod6, prod7, prod8, prod9, prod10,
				prod11, prod12, prod13, prod14, prod15, prod16, prod17));

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
		prod13.getCategorias().addAll(Arrays.asList(cat5));
		prod14.getCategorias().addAll(Arrays.asList(cat5));
		prod15.getCategorias().addAll(Arrays.asList(cat6));
		prod16.getCategorias().addAll(Arrays.asList(cat6));
		prod17.getCategorias().addAll(Arrays.asList(cat1));

		repoCategoria.save(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6));
		repoSite.save(Arrays.asList(s1, s2, s10, s3, s4, s5, s6, s7, s8, s9, s11));
		repoProduto.save(Arrays.asList(prod1, prod2, prod3, prod4, prod5, prod6, prod7, prod8, prod9, prod10, prod11,
				prod12, prod13, prod14, prod15, prod16, prod17));

		// *************************************************

		Usuario u0 = new Usuario(null, "000000", "sysadmin", "sysadmin@neobpo.com.br", psw.encode("sysadmin"));
		u0.addPerfil(Perfil.ADMIN);
		Usuario u1 = new Usuario(null, "213787", "Alex Barbosa", "alex.asilva@neobpo.com.br", psw.encode("123456"));
		u1.addPerfil(Perfil.ADMIN);
		Usuario u2 = new Usuario(null, "097207", "Guilherme Teixeira", "guilherme.teixeira@neobpo.com.br",
				psw.encode("123456"));
		Usuario u3 = new Usuario(null, "222230", "Alexandre Santos", "alexandre.santos@neobpo.com.br",
				psw.encode("123456"));
		Usuario u4 = new Usuario(null, "231880", "Lucas Natario Da Silva", "lucas.natario@neobpo.com.br",
				psw.encode("123456"));
		Usuario u5 = new Usuario(null, "225493", "Renan Dos Santos Tampellini", "renan.rtampellini@neobpo.com.br",
				psw.encode("123456"));
		Usuario u6 = new Usuario(null, "294860", "Roberto Coelho", "roberto.coelho@neobpo.com.br",
				psw.encode("123456"));
		Usuario u7 = new Usuario(null, "238564", "Gabriel Silva Goto", "gabriel.goto@neobpo.com.br",
				psw.encode("123456"));
		Usuario u8 = new Usuario(null, "310075", "Marcelo Vicente Rosim", "marcelo.rosim@neobpo.com.br",
				psw.encode("123456"));

		u0.getSite().addAll(Arrays.asList(s1, s2, s10, s3, s4, s5, s6, s7, s8, s9, s11));
		u1.getSite().addAll(Arrays.asList(s1));
		u2.getSite().addAll(Arrays.asList(s2, s10));
		u3.getSite().addAll(Arrays.asList(s3, s4));
		u4.getSite().addAll(Arrays.asList(s5));
		u5.getSite().addAll(Arrays.asList(s6, s7));
		u6.getSite().addAll(Arrays.asList(s8));
		u7.getSite().addAll(Arrays.asList(s11, s9));
		u8.getSite().addAll(Arrays.asList(s1, s2, s10, s3, s4, s5, s6, s7, s8, s9, s11));

		s1.getUsuarios().addAll(Arrays.asList(u1, u8, u0));
		s2.getUsuarios().addAll(Arrays.asList(u2, u8, u0));
		s10.getUsuarios().addAll(Arrays.asList(u2, u8, u0));
		s3.getUsuarios().addAll(Arrays.asList(u3, u8, u0));
		s4.getUsuarios().addAll(Arrays.asList(u3, u8, u0));
		s5.getUsuarios().addAll(Arrays.asList(u4, u8, u0));
		s6.getUsuarios().addAll(Arrays.asList(u5, u8, u0));
		s7.getUsuarios().addAll(Arrays.asList(u5, u8, u0));
		s8.getUsuarios().addAll(Arrays.asList(u6, u8, u0));
		s9.getUsuarios().addAll(Arrays.asList(u7, u8, u0));
		s11.getUsuarios().addAll(Arrays.asList(u7, u8, u0));

		repoUsuario.save(Arrays.asList(u0, u1, u2, u3, u4, u5, u6, u7, u8));
		//repoSite.save(Arrays.asList(s1, s2, s10, s3, s4, s5, s6, s7, s8, s9, s11));
		
		// **********************************************************************
		Cliente cli1 = new Cliente(null, "NET");
		Cliente cli2 = new Cliente(null, "NEOENERGIA");
		Cliente cli3 = new Cliente(null, "NEXTEL");
		Cliente cli4 = new Cliente(null, "CLARO");
		Cliente cli5 = new Cliente(null, "CIELO");
		Cliente cli6 = new Cliente(null, "TIM");
		Cliente cli7 = new Cliente(null, "BRASIL CAP");
		Cliente cli8 = new Cliente(null, "FIRST DATA");

		Celula cel1 = new Celula(null, "CELPE", "00000-00", cli2);
		Celula cel2 = new Celula(null, "COELBA", "00000-00", cli2);
		Celula cel3 = new Celula(null, "COSERN", "00000-00", cli2);
		Celula cel4 = new Celula(null, "CRC", "00000-00", cli1);
		Celula cel5 = new Celula(null, "MULTIFUNCIONAL", "00000-00", cli1);
		Celula cel6 = new Celula(null, "ILHAS TECNICAS", "00000-00", cli1);
		Celula cel7 = new Celula(null, "FILTRO DE VT", "00000-00", cli1);
		Celula cel8 = new Celula(null, "MAR ABERTO", "00000-00", cli5);
		Celula cel9 = new Celula(null, "SALES", "00000-00", cli6);
		Celula cel10 = new Celula(null, "PROPENSOS CHURN", "00000-00", cli1);
		Celula cel11 = new Celula(null, "RECORRÊNCIA", "00000-00", cli1);

		cli1.getCelulas().addAll(Arrays.asList(cel4, cel5, cel6, cel7));
		cli2.getCelulas().addAll(Arrays.asList(cel1, cel2, cel3));
		
		s1.getClientes().addAll(Arrays.asList(cli1, cli2, cli3, cli4, cli5, cli6, cli7, cli8));
		
		repoCliente.save(Arrays.asList(cli1, cli2, cli3, cli4, cli5, cli6, cli7, cli8));
		repoCelula.save(Arrays.asList(cel1, cel2, cel3, cel4, cel5, cel6, cel7, cel8, cel9, cel10, cel11));
		

		// **********************************************************************

		SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Trocas t1 = new Trocas(null, date.parse("28/11/2018 12:00"), u1, cli1);
		Trocas t2 = new Trocas(null, date.parse("28/11/2018 12:00"), u1, cli1);

		u1.getTrocas().addAll(Arrays.asList(t1, t2));
		cli1.getTrocas().addAll(Arrays.asList(t1,t2));

		repoTrocas.save(Arrays.asList(t1, t2));

		// ***********************************************************************
		ItemTroca it1 = new ItemTroca(t1, prod1, 2,"INC00003545621");
		ItemTroca it2 = new ItemTroca(t2, prod11, 5,"INC00003545623");
		

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
		repoSite.save(Arrays.asList(s1, s2, s10, s3, s4, s5, s6, s7, s8, s9, s11));
		
	}

}
