package mb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import util.FacesContextUtil;
import entity.Cliente;
import entity.Cliente;

@ManagedBean(name="cliMB")
public class ClienteMb {
	private List<Cliente> clientes;
	private EntityManager entityManager;
	private Cliente cliente;


	@PostConstruct
	private void init() {
		cliente = new Cliente();
		entityManager = FacesContextUtil.getEntityManager();
	}
	public List<Cliente> getclientes() {
		if (clientes == null) {
			Query query = entityManager.createQuery(
					"SELECT c FROM Cliente c", Cliente.class);
			clientes = query.getResultList();
		}
		return clientes;
	}
	public void setclientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
	
	public Cliente getcliente() {
		return cliente;
	}

	public void setcliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public String salvar(){
		entityManager.merge(cliente);
		
		return "listarclientes";
	}
	

	public String editar(Long id){
		cliente = entityManager.find(Cliente.class, id);
		
		return "clientesform";
	}

	public String excluir(Long id){
	Cliente cliente = entityManager.find(Cliente.class, id);
		entityManager.remove(cliente);
		clientes = null;
		return "listarclientes";
		
		
		
		
		
	}

	public Cliente buscarclientePorId(Long id) {
		
		return entityManager.find(Cliente.class, id);
	}
}



