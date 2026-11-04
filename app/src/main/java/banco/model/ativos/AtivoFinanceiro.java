package banco.model.ativos;

import java.math.BigDecimal;
import java.util.Random;

public class AtivoFinanceiro {

    /*------------------------------------------------- */
    /*adicionar as propriedades matematicas de cada CDB 
    e integrar com o sistemas de contas */
    /*------------------------------------------------- */

    public enum TipoAtivoFinanceiro {
        CDB,
        LCI,
        LCA,
        TESOURO,
        ACOES
    }

    public enum TipoRentabilidade {
        PREFIXADO,
        POS_FIXADO,
        HIBRIDO
    }

    //gerar um banco aleatorio para servidor como "quem fez o cdb" 
    private static final String[] EMISSORES_PADRAO = {
        "Banco do Brasil",
        "Itaú",
        "Bradesco",
        "Santander",
        "Nubank",
        "Sofisa Direto"
    };

    private static final Random RANDOM = new Random();

    private TipoAtivoFinanceiro tipoAtivo;
    private String emissorAutorizado;
    private TipoRentabilidade tipoRentabilidade;
    private BigDecimal taxaPrefixadaAnual;

    
    public AtivoFinanceiro() {
    }

    
    public AtivoFinanceiro(TipoRentabilidade rentabilidade, BigDecimal taxaPrefixada) {
        cadastrarCdb(rentabilidade);
        if (rentabilidade == TipoRentabilidade.PREFIXADO) {
            configurarPrefixado(taxaPrefixada);
        }
    }

    
    public void cadastrarCdb(TipoRentabilidade tipoRentabilidade) {
        if (tipoRentabilidade == null) {
            throw new IllegalArgumentException("O tipo de rentabilidade deve ser informado.");
        }

        this.tipoAtivo = TipoAtivoFinanceiro.CDB;
        this.emissorAutorizado = sortearEmissor();
        this.tipoRentabilidade = tipoRentabilidade;
    }

    public void configurarPrefixado(BigDecimal taxaAnual) {
        if (taxaAnual == null || taxaAnual.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("A taxa prefixada deve ser maior que zero.");
        }
        this.tipoRentabilidade = TipoRentabilidade.PREFIXADO;
        this.taxaPrefixadaAnual = taxaAnual;
    }

    
    private String sortearEmissor() {
        int indice = RANDOM.nextInt(EMISSORES_PADRAO.length);
        return EMISSORES_PADRAO[indice];
    }

    /*------------------------------------------------- */
    /*adicionar as propriedades matematicas de cada CDB 
    e integrar com o sistemas de contas */
    /*------------------------------------------------- */

    //getters
    public TipoAtivoFinanceiro getTipoAtivo() { return tipoAtivo; }
    public String getEmissorAutorizado() { return emissorAutorizado; }
    public TipoRentabilidade getTipoRentabilidade() { return tipoRentabilidade; }
    public BigDecimal getTaxaPrefixadaAnual() { return taxaPrefixadaAnual; }
}