package utils.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import play.Play;

public class FileReaderUtil {

	public static final Path LOG_FOLDER = Paths.get(Play.configuration
			.getProperty("log.folder"));
	public static final Path LOG_FOLDER_ERROR = Paths.get(Play.configuration
			.getProperty("log.folder.error"));
	public static final Path LOG_FOLDER_OK = Paths.get(Play.configuration
			.getProperty("log.folder.ok"));
	public static final String FILE_TYPE = Play.configuration
			.getProperty("log.type");

	public static DirectoryStream<Path> retornaArquivos() {

		try {
			List<File> listaDeArquivos = new ArrayList<File>();

			DirectoryStream<Path> ds = Files.newDirectoryStream(LOG_FOLDER,
					FILE_TYPE);

			return ds;

		} catch (IOException exception) {
			exception.printStackTrace();
		}
		return null;
	}
}
