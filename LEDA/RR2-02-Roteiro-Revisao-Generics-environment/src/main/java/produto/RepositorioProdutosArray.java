package produto;

public class RepositorioProdutosArray <T extends Produto> implements RepositorioProdutos<T> {

    private T[] produtos;
    private int index;

    public RepositorioProdutosArray(int size) {
		this.produtos = (T[]) new Object[size];
        this.index = -1;
	}

    protected int procurarIndice(int codigo) {
		int i = 0;
		int resp = -1;

		while ((i <= index)) {
			if (produtos[i].getCodigo() == codigo) {
				resp = i;
				break;
			}
			i = i + 1;
		}
		return resp;
	}

    @Override
    public boolean existe(int codigo) {
        int i = this.procurarIndice(codigo);
		return (i == -1);
    }

    @Override
    public void inserir(T produto) {
        this.index++;
        produtos[index] = produto;
    }

    @Override
    public void atualizar(T produto) {

        int i = procurarIndice(produto.getCodigo());

        if (i == -1) {
            throw new RuntimeException("Produto nao encontrado");
        }

        produtos[i] = produto;
    }

    @Override
    public void remover(int codigo) {
        int i = this.procurarIndice(codigo);

        if (i == -1) {
            throw new RuntimeException("Produto nao encontrado");
        }

        produtos[i] = produtos[index];
		produtos[index] = null;
		index--;
    }

    @Override
    public T procurar(int codigo) {
        int i = this.procurarIndice(codigo);

        if (i == -1) {
            throw new RuntimeException("Produto nao encontrado");
        }

        return produtos[i];
    }
    
}
