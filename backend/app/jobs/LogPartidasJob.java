package jobs;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Path;

import play.jobs.Every;
import play.jobs.Job;
import play.jobs.OnApplicationStart;
import utils.io.FileReaderUtil;
import utils.log.LogPartidasUtil;

@OnApplicationStart
@Every("1h")
public class LogPartidasJob extends Job {
	
	@Override
	public void doJob() throws IOException {
		
		jobPrincipal();
		
	}
	
	
	private void jobPrincipal() throws IOException {
		
		DirectoryStream<Path> directoryStream = FileReaderUtil.retornaArquivos();	
		
		LogPartidasUtil.lerAquivoGerarLog(directoryStream);
		
	}

}
