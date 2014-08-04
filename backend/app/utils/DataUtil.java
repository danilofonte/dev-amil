package utils;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DataUtil {

	public static Date criarData(String dataHora) {
		Calendar calendar = Calendar.getInstance();

		String[] dataHoraQuebrada = dataHora.split(" ");

		String[] data = dataHoraQuebrada[0].split("/");
		String[] tempo = dataHoraQuebrada[1].split(":");

		int ano = Integer.parseInt(data[2]);
		int mes = MapUtil.retornaMesMap().get(data[1]);
		int dia = Integer.parseInt(data[0]);

		int hora = Integer.parseInt(tempo[0]);
		int minutos = Integer.parseInt(tempo[1]);
		int segundos = Integer.parseInt(tempo[2]);

		calendar.set(ano, mes, dia, hora, minutos, segundos);

		return calendar.getTime();
	}

	public static Long diferencaEmMinutos(Date dataInicio, Date dataFim) {
		
		Calendar calendarInicio = Calendar.getInstance();
		calendarInicio.setTime(dataInicio);

		long inicio = calendarInicio.getTimeInMillis();

		Calendar calendarFim = Calendar.getInstance();
		calendarFim.setTime(dataFim);
		
		long fim = calendarFim.getTimeInMillis();

		return TimeUnit.MILLISECONDS.toMinutes(fim - inicio);

	}

}
