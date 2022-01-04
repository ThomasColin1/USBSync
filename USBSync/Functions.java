import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Files;
import java.nio.file.Path;
import static java.nio.file.StandardCopyOption.*;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.*;
import javax.swing.filechooser.*;

public class Functions {


    public static void effacer(File fichier){
        if (fichier.isDirectory()) {
            File[] subs = fichier.listFiles();
            if (subs != null) {
                for (File sub : subs) {
                    effacer(sub);
                }
            }
        }
        fichier.delete();
    }


    public static void copier(File origine, File direction) throws IOException{
        if ((direction.exists())&&(origine.lastModified()>direction.lastModified())) Files.copy(origine.toPath(), direction.toPath(), REPLACE_EXISTING);
        if (!direction.exists()) Files.copy(origine.toPath(), direction.toPath(), REPLACE_EXISTING);

    }



    public static void utilCopier(File fichier, File destination)throws IOException{

        if (fichier.isDirectory()) {
            if(!destination.exists()) Files.createDirectory(destination.toPath());
            File[] subs = fichier.listFiles();
            if (subs != null) {
                for (File sub : subs) {
                    File destination2=new File(destination.getPath()+"//"+sub.getName());
                    utilCopier(sub, destination2);
                }
            }
        }
        else copier(fichier, destination);
    }

    public static String[] USB(){
        //System.out.println("File system roots returned by   FileSystemView.getFileSystemView():");
        FileSystemView fsv = FileSystemView.getFileSystemView();
        File[] roots = fsv.getRoots();

        for (int i = 0; i < roots.length; i++)
        {
            //System.out.println("Root: " + roots[i]);
        }

        /*System.out.println("Home directory: " + fsv.getHomeDirectory());

        System.out.println("File system roots returned by File.listRoots():");*/

        File[] f = File.listRoots();
        String[] USB = new String[f.length];
        for (int i = 0; i < f.length; i++)
        {
            /*System.out.println("Drive: " + f[i]);
            System.out.println("Display name: " + fsv.getSystemDisplayName(f[i]));
            System.out.println("Is drive: " + fsv.isDrive(f[i]));
            System.out.println("Is floppy: " + fsv.isFloppyDrive(f[i]));
            System.out.println("Readable: " + f[i].canRead());
            System.out.println("Writable: " + f[i].canWrite());*/
            USB[i]=fsv.getSystemDisplayName(f[i]);

        }


        return USB;
    }
}
