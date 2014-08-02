package builder;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import play.db.jpa.JPA;
import play.db.jpa.Model;

public abstract class DefaultModelBuilder<T extends Model> {

	private static Map<Class, Long> idsPorModel = new HashMap<Class, Long>();
	
	protected T model;
	
	private Class<T> modelClass = getModelClass();
	
	public abstract DefaultModelBuilder<T> padrao();

	public DefaultModelBuilder(T model) {
		this.model = model;
	}
	
	// Método que simula uma sequence para ser utilizado em tabelas cuja sequence não foi mapeada na model (Usuario por ex.).
	protected Long gerarId() {
		
		if (!idsPorModel.containsKey(this.modelClass)) { 
			
			configurarPrimeiroId();
		}
		
		Long id = idsPorModel.get(this.modelClass);
		id++;
		idsPorModel.put(this.modelClass, id);
		
		return id;
	}	
	
	public T build() {
		
		return model;
	}
	
	public T save() {
		
		T obj = build();
		
		if (obj == null)
			throw new RuntimeException("Para utilizar este método deve-se implementar o método 'build' na factory.");
		
		obj._save();
		
		return obj;
	}
	
	private void configurarPrimeiroId() {
		
		String jpql = "select m.id from " + getModelClass().getName() + " m order by id desc";
		System.out.println(jpql);
		
		List<Long> ids = JPA.em().createQuery(jpql).setMaxResults(1).getResultList();
		
		if (ids == null || ids.isEmpty() || ids.get(0) == null) {
			idsPorModel.put(modelClass, 0l);
		} else {
			idsPorModel.put(modelClass, ids.get(0));
		}
	}
	
	private Class<T> getModelClass() {
		
		Type genericSuperclass = this.getClass().getGenericSuperclass();
	    
		if (genericSuperclass instanceof ParameterizedType) {
			
			ParameterizedType pt = (ParameterizedType) genericSuperclass;
			Type type = pt.getActualTypeArguments()[0];
			
			return (Class<T>)type;
		}
		
		throw new IllegalStateException("O builder deve extender a class GenericModel<T> passando a classe T, que é o tipo da model.");
	}
	
}
