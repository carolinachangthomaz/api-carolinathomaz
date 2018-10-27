package com.carolinathomaz.apicarolinathomaz.services;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carolinathomaz.apicarolinathomaz.enums.StatusEncomenda;
import com.carolinathomaz.apicarolinathomaz.enums.TipoServico;
import com.carolinathomaz.apicarolinathomaz.model.CentroDistribuicao;
import com.carolinathomaz.apicarolinathomaz.model.Cidade;
import com.carolinathomaz.apicarolinathomaz.model.Cliente;
import com.carolinathomaz.apicarolinathomaz.model.Embalagem;
import com.carolinathomaz.apicarolinathomaz.model.Encomenda;
import com.carolinathomaz.apicarolinathomaz.model.EnderecoCentroDistribuicao;
import com.carolinathomaz.apicarolinathomaz.model.Estado;
import com.carolinathomaz.apicarolinathomaz.model.ItemEncomenda;
import com.carolinathomaz.apicarolinathomaz.model.StatusAlteracao;
import com.carolinathomaz.apicarolinathomaz.repositories.CentroDistribuicaoRepository;
import com.carolinathomaz.apicarolinathomaz.repositories.CidadeRepository;
import com.carolinathomaz.apicarolinathomaz.repositories.ClienteRepository;
import com.carolinathomaz.apicarolinathomaz.repositories.EmbalagemRepository;
import com.carolinathomaz.apicarolinathomaz.repositories.EnderecoCentroDistribuicaoRepository;
import com.carolinathomaz.apicarolinathomaz.repositories.EstadoRepository;

//Service criado para popular o banco de dados

@Service
public class DBService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EmbalagemRepository embalagemRepository;
	
	@Autowired
	private CentroDistribuicaoRepository centroDistribuicaoRepository;
	
	@Autowired
	private EnderecoCentroDistribuicaoRepository enderecoCentroDistribuicaoRepository;
	
	@Autowired
	private EncomendaService encomendaService;
	
	@Autowired
	private StatusAlteracaoService statusAlteracaoService;
	
	
	public void instantiateTestDatabase() throws ParseException {
		
		Cliente cliente1 = new Cliente(null, "Banco do Brasil", "contato@bb.com.br");
		Cliente cliente2 = new Cliente(null, "Itaú", "contato@itau.com.br");
		
		// registrando cliente no BD
		clienteRepository.saveAll(Arrays.asList(cliente1,cliente2));
		
		Estado estado1 = new Estado(null, "Minas Gerais");
		Estado estado2 = new Estado(null, "São Paulo");
		
		Cidade cidade1 = new Cidade(null, "Uberlândia", estado1);
		Cidade cidade2 = new Cidade(null, "São Paulo", estado2);
		Cidade cidade3 = new Cidade(null, "Campinas", estado2);

		estado1.getCidades().addAll(Arrays.asList(cidade1));
		estado2.getCidades().addAll(Arrays.asList(cidade2, cidade3));
		
		// registrando cidade e estado que será usado no endereço
		estadoRepository.saveAll(Arrays.asList(estado1, estado2));
		cidadeRepository.saveAll(Arrays.asList(cidade1, cidade2, cidade3));
		
		Embalagem embalagem1 = new Embalagem(null, "Caixa", new BigDecimal("10"));
		Embalagem embalagem2 = new Embalagem(null, "Envelope", new BigDecimal("2"));
		
		// registrando embalagens disponiveis para envio de encomendas
		embalagemRepository.saveAll(Arrays.asList(embalagem1,embalagem2));
		
		CentroDistribuicao centroDistribuicao1 = new CentroDistribuicao(null, "CD 1");
		CentroDistribuicao centroDistribuicao2 = new CentroDistribuicao(null, "CD 2");
		
		// registrando centro de distribuição 
		centroDistribuicaoRepository.saveAll(Arrays.asList(centroDistribuicao1,centroDistribuicao2));
		
		EnderecoCentroDistribuicao ecd1 = new EnderecoCentroDistribuicao(null, "Rua JoãoFranco Oliveira", "91", "comercial", "campininha", "04678100", centroDistribuicao1, cidade1);
		EnderecoCentroDistribuicao ecd2 = new EnderecoCentroDistribuicao(null, "Rua Pinheiros", "100", "comercial", "sto Amaro", "45564565", centroDistribuicao1, cidade2);
		EnderecoCentroDistribuicao ecd3 = new EnderecoCentroDistribuicao(null, "AV Faria Lima", "91", "comercial", "sto Amaro", "45564565", centroDistribuicao2, cidade2);
		
		// registrando endereços para os centros de distribuições
		enderecoCentroDistribuicaoRepository.saveAll(Arrays.asList(ecd1,ecd2,ecd3));
		
		centroDistribuicao1.getEnderecos().addAll(Arrays.asList(ecd1,ecd2));
		centroDistribuicao2.getEnderecos().addAll(Arrays.asList(ecd3));
		
		
		centroDistribuicaoRepository.saveAll(Arrays.asList(centroDistribuicao1,centroDistribuicao2));
		
		
		/* Encomenda 1 */
		
		
		Encomenda encomenda = new Encomenda(null, TipoServico.SEDEX, centroDistribuicao1,"Endereço do cliente",  cliente1);
			
		ItemEncomenda item1 = new ItemEncomenda(null, encomenda, 1, embalagem1);
		ItemEncomenda item2 = new ItemEncomenda(null, encomenda, 2, embalagem2);
		
		encomenda.getItens().addAll(Arrays.asList(item1, item2));
		
		encomendaService.registrarEncomenda(encomenda);
		
		StatusAlteracao postado = new StatusAlteracao(null, new Date(), StatusEncomenda.POSTADO, encomenda);
		StatusAlteracao emtransito = new StatusAlteracao(null, new Date(), StatusEncomenda.EMTRANSITO, encomenda);
		StatusAlteracao emtransitocliente = new StatusAlteracao(null, new Date(), StatusEncomenda.EMTRANSITOPARACLIENTE, encomenda);
		StatusAlteracao entregue = new StatusAlteracao(null, new Date(), StatusEncomenda.ENTREGUE, encomenda);
		
		statusAlteracaoService.salvarStatus(Arrays.asList(postado,emtransito,emtransitocliente,entregue));
		
		
		
		/* Encomenda 2 */
		
		Encomenda encomenda2 = new Encomenda(null, TipoServico.PAC, centroDistribuicao1,"Endereço do cliente",  cliente2);
		
		ItemEncomenda eitem1 = new ItemEncomenda(null, encomenda, 100, embalagem1);
		ItemEncomenda eitem2 = new ItemEncomenda(null, encomenda, 20, embalagem2);
		
		encomenda2.getItens().addAll(Arrays.asList(eitem1, eitem2));
		
		encomendaService.registrarEncomenda(encomenda2);
		
		StatusAlteracao postado2 = new StatusAlteracao(null, new Date(), StatusEncomenda.POSTADO, encomenda2);
		StatusAlteracao emtransito2 = new StatusAlteracao(null, new Date(), StatusEncomenda.EMTRANSITO, encomenda2);
		
		
		statusAlteracaoService.salvarStatus(Arrays.asList(postado2,emtransito2));
		
		//List<StatusAlteracao> list = statusAlteracaoService.findByEmcomendaId(encomenda.getId());
		//list.forEach(item -> System.out.println("POSTADO" +item.toString()));
		
		
	}

}
