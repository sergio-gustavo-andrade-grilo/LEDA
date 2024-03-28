package produto;

public class RepositorioProdutosArray implements RepositorioProdutos {

	private Produto[] produtos;
	private int indice = -1;

	public RepositorioProdutosArray(int tamanho) {
        this.produtos = new Produto[tamanho];
	}

	protected int procurarIndice(int codigo) {
		int indiceProduto = -1;
		boolean produtoNoArray = false;

        int i = 0;
		while ((i <= indice) && !produtoNoArray) {
			if (produtos[i].getCodigo() == codigo) {
				indiceProduto = i;
				produtoNoArray = true;
			}
			i++;
		}
		return indiceProduto;

	}

	@Override
	public boolean existe(int codigo) {
		int i = this.procurarIndice(codigo);
		return !(i == -1);

	}

	@Override
	public void inserir(Produto produto) {
        indice++;
		produtos[indice] = produto;
	}

	@Override
	public void atualizar(Produto produto) {
		int i = procurarIndice(produto.getCodigo());
		if (i != -1) {
			produtos[i] = produto;
		} else {
			throw new RuntimeException("Produto não encontrado");
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see solucao.produto.RepositorioProdutos#remover(int)
	 */
	@Override
	public void remover(int codigo) {
		int i = this.procurarIndice(codigo);
		if (i != -1) {
			produtos[i] = produtos[indice];
			produtos[indice] = null;
			indice--;
		} else {
			throw new RuntimeException("Produto não encontrado");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see solucao.produto.RepositorioProdutos#procurar(int)
	 */
	@Override
	public Produto procurar(int codigo) {
		Produto resp = null;
		int i = this.procurarIndice(codigo);
		if (i != -1) {
			resp = produtos[i];
		} else {
			throw new RuntimeException("Produto não encontrado");
		}

		return resp;
	}
}
