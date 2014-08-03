package enums;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public enum TipoAcaoEnum {

	KILLED("killed"), ENDED("ended"), STARTED("started");

	private String tipo;

	private TipoAcaoEnum(String tipo) {
		this.tipo = tipo;
	}

	public static TipoAcaoEnum getTipoAcaoEnum(String line) {

		List<String> enums = new ArrayList<String>();

		for (TipoAcaoEnum tipoEnum : TipoAcaoEnum.values()) {

			enums.add(tipoEnum.tipo);

		}

		Pattern tipoPattern = Pattern.compile(StringUtils.join(enums, "|"));

		Matcher matcher = tipoPattern.matcher(line);

		matcher.find();

		return getTipoAcaoEnumPeloTipo(matcher.group());

	}

	public static TipoAcaoEnum getTipoAcaoEnumPeloTipo(String tipo) {
		for (TipoAcaoEnum tipoEnum : TipoAcaoEnum.values()) {
			if (tipoEnum.tipo.equals(tipo))
				return tipoEnum;

		}
		return null;

	}

	public String getTipo() {
		return tipo;
	}

}
