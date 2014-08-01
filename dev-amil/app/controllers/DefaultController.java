package controllers;

import java.util.Collection;

import com.google.gson.Gson;

import flexjson.JSONSerializer;
import play.mvc.Controller;

public class DefaultController extends Controller {
	
	protected static Gson gson;
	
	/**
	 * Renderiza um JSON utilizando o serializador informado.
	 */
    protected static void renderJSON(Object model, JSONSerializer js) {
    	String json = js.serialize(model);
		renderJSON(json);
    }
    
    protected static void renderJSON(Object model) {
		renderJSON(gson.toJson(model));
    }

    /*
	 * Renderiza um JSON utilizando o serializador informado.
	 */
    protected static void renderJSON(Collection<Object> models, JSONSerializer js) {
    	String json = js.serialize(models);
    	renderJSON(json);
    }

}
