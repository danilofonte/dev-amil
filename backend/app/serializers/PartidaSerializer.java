package serializers;

import play.Play;
import play.Play.Mode;
import flexjson.JSONSerializer;

public class PartidaSerializer {

	public static JSONSerializer partidaSerializer;
	public static JSONSerializer viewSerializer;
	public static JSONSerializer armaViewSerializer;

	static {

		boolean prettyPrint = Play.mode == Mode.DEV;

		partidaSerializer = new JSONSerializer().include("id", "identificadorPartida").exclude("*").prettyPrint(prettyPrint);
		
		viewSerializer = new JSONSerializer().include("nome", "qtd").exclude("*").prettyPrint(prettyPrint);
		
		armaViewSerializer = new JSONSerializer().include("nome", "qtd","nomeJogador").exclude("*").prettyPrint(prettyPrint);

	}

}
