package jobs;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Path;

import play.Logger;
import play.jobs.Every;
import play.jobs.Job;
import play.jobs.OnApplicationStart;
import utils.io.FileReaderUtil;
import utils.partida.LogPartidasUtil;

@OnApplicationStart
@Every("60s")
public class LogPartidasJob extends Job {
	
	@Override
	public void doJob() throws IOException {
	
		Logger.info("job partidas iniciado com sucesso");
		
		jobPrincipal();
		
		Logger.info("job partidas finalizado com sucesso");
		
}
	
	
	private void jobPrincipal() throws IOException {
		
		DirectoryStream<Path> directoryStream = FileReaderUtil.retornaArquivos();	
		
		LogPartidasUtil.lerAquivoGerarLog(directoryStream);
		
	}

}
