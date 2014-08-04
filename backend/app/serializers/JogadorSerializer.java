package serializers;

import play.Play;
import play.Play.Mode;
import flexjson.JSONSerializer;

public class JogadorSerializer {
	
	public static JSONSerializer jogadorSerializer;
	public static JSONSerializer dadosJogadorSerializer;

	static {

		boolean prettyPrint = Play.mode == Mode.DEV;

		jogadorSerializer = new JSONSerializer().include("id", "nome").exclude("*").prettyPrint(prettyPrint);
		
		dadosJogadorSerializer = new JSONSerializer().include("nome","streak","awards").exclude("*").prettyPrint(prettyPrint);
		

	}

}
