package Lab1;

import java.io.File;
import java.io.IOException;

import javax.swing.filechooser.FileSystemView;

public class HomeDirectory extends FileSystemView {
	File root;
	File[] roots = new File[1];

	public HomeDirectory(File path)
	{
		super();

		try
		{
			root = path.getCanonicalFile();
			roots[0] = root;
		}
		catch(IOException e)
		{
			throw new IllegalArgumentException( e );
		}

		if ( !root.isDirectory() )
		{
			String message = root + " is not a directory";
			throw new IllegalArgumentException( message );
		}
	}

	@Override
	public File createNewFolder(File containingDir) throws IOException {
		// TODO Auto-generated method stub
		File folder = new File(containingDir, "New Folder");
		folder.mkdir();
		return folder;
	}
	
	@Override
	public File getDefaultDirectory()
	{
		return root;
	}

	@Override
	public File getHomeDirectory()
	{
		return root;
	}

	@Override
	public File[] getRoots()
	{
		return roots;
	}
}

