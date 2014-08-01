package serializers;

import play.Play;
import flexjson.transformer.DateTransformer;
import flexjson.transformer.Transformer;

public class DateSerializer {
	
	private static final String DATE_FORMAT = Play.configuration.getProperty("date.format");
	private static final String DATE_FORMAT_TIMETABLE = Play.configuration.getProperty("date.format.timetable");
	
	private static DateTransformer dateTransformer;
	
	public static Transformer getTransformer() {
		
			dateTransformer = new DateTransformer(DATE_FORMAT);
		
		return dateTransformer;
	}
	
	public static Transformer getTransformerWithTimetable() {
		
			dateTransformer = new DateTransformer(DATE_FORMAT_TIMETABLE);
	
		return dateTransformer;
	}

}
