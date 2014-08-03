package utils.log;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import models.Arma;
import models.HistoricoPartida;
import models.Jogador;
import models.Partida;
import play.Play;
import utils.DataUtil;
import utils.io.FileReaderUtil;
import enums.TipoAcaoEnum;
import enums.TipoArmaEnum;
import exceptions.ValidationException;

public class LogPartidasUtil {

	private static final Charset CHARSET = Charset.forName(Play.configuration.getProperty("charset.iso"));

	private static Pattern identificadorPartida = Pattern.compile("[0-9]{8}");
	private static Pattern dataHoraHistorico = Pattern.compile("[0-9]{2}/[0-9]{2}/[0-9]{4} [0-9]{2}:[0-9]{2}:[0-9]{2}");

	private static HashMap<String, Jogador> mapJogadores = new HashMap<String, Jogador>();
	private static HashMap<String, Arma> mapArmas = new HashMap<String, Arma>();

	public static void lerAquivoGerarLog(DirectoryStream<Path> directoryStream) throws IOException {

		for (Path path : directoryStream) {

			try {

				gravarLog(path);

				Files.copy(path, Paths.get(FileReaderUtil.LOG_FOLDER_OK.toString(), path.getFileName().toString()));

			} catch (Exception exc) {

				Files.copy(path, Paths.get(FileReaderUtil.LOG_FOLDER_ERROR.toString(), path.getFileName().toString()));

			} finally {

				Files.delete(path);

			}

		}

	}

	private static void gravarLog(Path path) throws IOException, ValidationException {

		List<String> lines = Files.readAllLines(path, CHARSET);

		Partida partida = new Partida();

		List<Jogador> jogadores = new ArrayList<Jogador>();
		List<HistoricoPartida> historico = new ArrayList<HistoricoPartida>();

		for (String line : lines) {

			TipoAcaoEnum tipo = TipoAcaoEnum.getTipoAcaoEnum(line);

			if (tipo.equals(TipoAcaoEnum.STARTED) || tipo.equals(TipoAcaoEnum.ENDED)) {

				if (tipo.equals(TipoAcaoEnum.ENDED)) {

					partida.atualizarHistorico(historico);
					partida.atualizarListaJogadores(jogadores);
				}

				partidaIniciadaFinalizada(line, partida, tipo);
			}

			else if (tipo.equals(TipoAcaoEnum.KILLED)) {
				atualizarPartida(partida, historico, jogadores, line, tipo);
			}

		}

	}

	private static void partidaIniciadaFinalizada(String line, Partida partida, TipoAcaoEnum tipo) {
		Matcher identificadorPartidaMatcher = identificadorPartida.matcher(line);

		if (identificadorPartidaMatcher.find()) {

			String identificadorPartida = identificadorPartidaMatcher.group();

			Matcher dataHoraHistoricoMatcher = dataHoraHistorico.matcher(line);

			if (dataHoraHistoricoMatcher.find()) {

				HistoricoPartida historicoPartida = new HistoricoPartida(DataUtil.criarData(dataHoraHistoricoMatcher.group()), tipo);

				if (tipo.equals(TipoAcaoEnum.STARTED)) {

					partida.identificadorPartida = identificadorPartida;

					partida.iniciarPartida(historicoPartida);
				} else if (tipo.equals(TipoAcaoEnum.ENDED)) {

					historicoPartida.identificadorPartida = identificadorPartida;

					partida.finalizarPartida(historicoPartida);

				}
			}
		}
	}

	private static void atualizarPartida(Partida partida, List<HistoricoPartida> historico, List<Jogador> jogadores, String line, TipoAcaoEnum tipo) {

		atualizarListaHistoricoEJogador(historico, jogadores, line, tipo);
	}

	private static void atualizarListaHistoricoEJogador(List<HistoricoPartida> historico, List<Jogador> jogadores, String line, TipoAcaoEnum tipo) {

		Matcher dataHoraHistoricoMatcher = dataHoraHistorico.matcher(line);

		if (dataHoraHistoricoMatcher.find()) {

			String[] split = line.split("-");

			String[] acao = split[1].trim().split(" ");

			Jogador jogadorExecutouAcao = adicionarJogadorAPartida(acao[0].trim());

			Jogador jogadorRecebeuAcao = adicionarJogadorAPartida(acao[2].trim());

			if (!jogadores.contains(jogadorExecutouAcao))
				jogadores.add(jogadorExecutouAcao);
			if (!jogadores.contains(jogadorRecebeuAcao))
				jogadores.add(jogadorRecebeuAcao);

			Arma arma = adicionarArma(acao[4], jogadorExecutouAcao);

			HistoricoPartida historicoPartida = new HistoricoPartida(jogadorExecutouAcao, jogadorRecebeuAcao, arma,
					DataUtil.criarData(dataHoraHistoricoMatcher.group()), tipo);

			historico.add(historicoPartida);

		}

	}

	private static Arma adicionarArma(String nome, Jogador jogador) {

		Arma arma = mapArmas.get(nome);

		if (arma == null) {

			if (Jogador.isIa(jogador.nome))
				arma = Arma.iniciarArma(nome, null, TipoArmaEnum.WORLD);
			else
				arma = Arma.iniciarArma(nome, null, TipoArmaEnum.LONGADISTANCIA);

			mapArmas.put(nome, arma);
		}

		return arma;

	}

	private static Jogador adicionarJogadorAPartida(String nome) {

		Jogador jogador = mapJogadores.get(nome);

		if (jogador == null) {

			jogador = Jogador.iniciarJogador(nome);
			mapJogadores.put(nome, jogador);

		}

		return jogador;

	}

}
