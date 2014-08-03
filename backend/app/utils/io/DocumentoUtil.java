package utils.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.swing.filechooser.FileSystemView;

import play.Play;

public class DocumentoUtil {

	public static final String LOG_FOLDER = Play.configuration.getProperty("log.folder");
	public static final String LOG_FOLDER_ERROR = Play.configuration.getProperty("log.folder.error");
	public static final String LOG_FOLDER_OK = Play.configuration.getProperty("log.folder.ok");

	public static File getFile(String pathArquivo) {

		File file = new File(pathArquivo);

		if (file.exists())
			return file;

		return null;
	}

	public static void copyFile(File origem, File destino) throws IOException {

		InputStream in = new FileInputStream(origem);

		OutputStream out = new FileOutputStream(destino);

		byte[] buf = new byte[1024];
		int len;
		while ((len = in.read(buf)) > 0) {
			out.write(buf, 0, len);
		}
		in.close();
		out.close();
	}

	public static String getFolder(String pathFolder) throws IOException {

		File folder = new File(pathFolder);

		createFolderIfNotExists(folder);

		return pathFolder;
	}

	public static void createFolderIfNotExists(File folder) throws IOException {

		if (!folder.exists()) {

			if (!folder.mkdir()) {
				throw new IOException(
						"Não foi possível criar a pasta definitiva.");
			}
		}

	}

	public static boolean deleteFile(String path) {

		File file = new File(path);

		return file.delete();
	}

}
