package jobs;

import play.jobs.Every;
import play.jobs.Job;
import play.jobs.OnApplicationStart;

@OnApplicationStart
@Every("1M")
public class LogPartidasJob extends Job {
	
	@Override
	public void doJob() {
		
	}

}
